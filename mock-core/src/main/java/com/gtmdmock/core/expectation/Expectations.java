package com.gtmdmock.core.expectation;

import com.gtmdmock.core.client.ServerClient;
import org.mockserver.mock.Expectation;

import java.util.ArrayList;
import java.util.List;

public class Expectations {

    private String projectId;
    private String expectationsId;
    private ServerClient server;
    private List<Expectation> expectationList;
    private boolean isOpen;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getExpectationsId() {
        return expectationsId;
    }

    public void setExpectationsId(String expectationsId) {
        this.expectationsId = expectationsId;
    }

    public ServerClient getServer() {
        return server;
    }

    public void setServer(ServerClient server) {
        this.server = server;
    }

    public List<Expectation> getExpectationList() {
        return expectationList;
    }

    public void setExpectationList(List<Expectation> expectationList) {
        this.expectationList = expectationList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen() {
        isOpen = true;
        this.initClient();
    }

    public void serClose(){
        isOpen = false;
        this.clearClient();

    }

    public void initClient() {
        if (this.isOpen()){
            //这里不用担心，重复增加期望，框架本身做了处理
            for (Expectation expectation: this.expectationList) {
                this.server.upsert(expectation);
            }

        }
    }

    public void clearClient(){
        if (!this.isOpen()){
            for (Expectation expectation: this.expectationList){
                this.server.clear(expectation.getHttpRequest());
            }
        }
    }

    //拼装expectations，可以写在构造器里，也可以写在单独方法里，被构造器调用
    public void splitExpectations(){}

    //添加或修改一个expectation
    public boolean updateExpectation(Expectation expectation){

        try{

            if (this.expectationList == null || this.expectationList.isEmpty()){
                this.setExpectationList(new ArrayList<Expectation>());
                this.updateExpectation(expectation);
                this.server.upsert(expectation);
            }else {
                this.expectationList.add(expectation);
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //在expectations中删除一个期望
    public boolean deleteExpectation(Expectation expectation){
        try {
            this.expectationList.remove(expectation);
            this.server.clear(expectation.getHttpRequest());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


}

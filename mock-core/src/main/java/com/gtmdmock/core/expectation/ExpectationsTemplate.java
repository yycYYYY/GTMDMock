package com.gtmdmock.core.expectation;

import com.gtmdmock.core.client.ServerClient;
import org.mockserver.mock.Expectation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpectationsTemplate {

    private Integer projectId;
    private Integer expectationsId;
    private ServerClient server;
    private List<Expectation> expectationList;
    private boolean isOpen;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getExpectationsId() {
        return expectationsId;
    }

    public void setExpectationsId(Integer expectationsId) {
        this.expectationsId = expectationsId;
    }

    public ServerClient getServer() {
        return server;
    }

    public void setServer(ServerClient server) {
        this.server = server;
    }

    //这种解决bug的方式，是强行打补丁，优化的时候，需要看下，怎么把问题，在对象实例化阶段解决掉
    public List<Expectation> getExpectationList() {
        if (this.expectationList == null){
            this.setExpectationList(new ArrayList<>());
        }
        return expectationList;
    }

    public void setExpectationList(List<Expectation> expectationList) {
        this.expectationList = expectationList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    //这里的命名也有问题，这个方法，有些问题
    public void setOpen() {
        isOpen = true;
        this.initClient();
    }

    public void setClose(){
        isOpen = false;
        this.clearClient();

    }

    public void initClient() {
        if (this.isOpen() && expectationList != null  && !expectationList.isEmpty()){
            //这里不用担心，重复增加期望，框架本身做了处理
            for (Expectation expectation: this.expectationList) {
                this.server.upsert(expectation);
            }

        }
    }

    public void clearClient(){
        if (!this.isOpen()&& expectationList != null  && !expectationList.isEmpty()){
            for (Expectation expectation: this.expectationList){
                this.server.clear(expectation.getHttpRequest());
            }
        }
    }

    //添加或修改一个expectation
    public boolean updateExpectation(Expectation expectation){
        try{

            if (this.expectationList == null || this.expectationList.isEmpty()){
                this.setExpectationList(new ArrayList<>());
                this.updateExpectation(expectation);
                this.server.upsert(expectation);
            }else {
                this.expectationList.add(expectation);
                this.server.upsert(expectation);
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
            this.server.clear(expectation.getHttpRequest());
            //TODO:这里删除期望的代码可能有点问题，后续需要优化，这种实际上在内存中应该没有删掉旧的期望实例，可能造成内存泄露
            this.expectationList.remove(expectation);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ExpectationsTemplate buildExpectations(){
        ExpectationsTemplate template = new ExpectationsTemplate();
        template.setOpen();
        template.setProjectId(this.projectId);
        template.setExpectationsId(this.expectationsId);
        return template;
    }


}

package com.gtmdmock.core.expectation;

import com.gtmdmock.core.client.ServerClient;
import org.mockserver.mock.Expectation;

import java.util.List;

public class Expectations {

    private String projectId;
    private String expectationsId;
    private ServerClient server;
    private List<Expectation> expectationList;

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


}

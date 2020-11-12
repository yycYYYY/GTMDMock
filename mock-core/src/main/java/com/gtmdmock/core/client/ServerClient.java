package com.gtmdmock.core.client;

import org.mockserver.integration.ClientAndServer;

public class ServerClient extends ClientAndServer {
    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ServerClient(Integer... ports) {
        super(ports);
    }

    public ServerClient(String projectId, Integer... ports) {
        super(ports);
        this.projectId = projectId;
    }

    public ServerClient(String remoteHost, Integer remotePort, String projectId, Integer... ports) {
        super(remoteHost, remotePort, ports);
        this.projectId = projectId;
    }
}

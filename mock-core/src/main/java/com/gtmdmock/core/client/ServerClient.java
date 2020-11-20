package com.gtmdmock.core.client;

import org.mockserver.integration.ClientAndServer;

public class ServerClient extends ClientAndServer {
    private Integer projectId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public ServerClient(int port) {
        super(port);
    }

    public ServerClient(Integer... ports) {
        super(ports);
    }

    public ServerClient(Integer projectId, Integer... ports) {
        super(ports);
        this.projectId = projectId;
    }

    public ServerClient(String remoteHost, Integer remotePort, Integer projectId, Integer... ports) {
        super(remoteHost, remotePort, ports);
        this.projectId = projectId;
    }
}

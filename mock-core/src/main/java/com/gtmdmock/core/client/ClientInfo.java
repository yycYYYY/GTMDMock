package com.gtmdmock.core.client;

import org.mockserver.model.HttpForward;

public class ClientInfo {

    private String projectId;
    private String projectName;
    private int port;
    private String proxyAddress;
    private HttpForward.Scheme scheme;
    private boolean isOpen;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProxyAddress() {
        return proxyAddress;
    }

    public void setProxyAddress(String proxyAddress) {
        this.proxyAddress = proxyAddress;
    }

    public HttpForward.Scheme getScheme() {
        return scheme;
    }

    public void setScheme(HttpForward.Scheme scheme) {
        this.scheme = scheme;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public ClientInfo() {
    }

    public ClientInfo(String test) {
        this.projectId = "1";
        this.projectName = "test_project";
        this.port = 1081;
        this.proxyAddress = "baidu.com";
        this.scheme = HttpForward.Scheme.HTTPS;
        this.isOpen = true;
    }


}

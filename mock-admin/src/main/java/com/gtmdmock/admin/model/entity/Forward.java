package com.gtmdmock.admin.model.entity;

public class Forward {
    private Integer id;

    private Integer requestId;

    private String host;

    private Integer port;

    private Integer isSecure;

    private Integer delay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getIsSecure() {
        return isSecure;
    }

    public void setIsSecure(Integer isSecure) {
        this.isSecure = isSecure;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }
}
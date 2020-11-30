package com.gtmdmock.admin.model.entity;

public class Error {
    private Integer id;

    private Integer requestId;

    private String response;

    private Integer isDropConnection;

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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response == null ? null : response.trim();
    }

    public Integer getIsDropConnection() {
        return isDropConnection;
    }

    public void setIsDropConnection(Integer isDropConnection) {
        this.isDropConnection = isDropConnection;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }
}
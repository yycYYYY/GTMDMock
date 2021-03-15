package com.gtmdmock.admin.model.entity;

public class Project {
    private Integer id;

    private String projectName;

    private String proxyAddress;

    private Integer port;

    private Integer isSecure;

    private Integer isOpen;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProxyAddress() {
        return proxyAddress;
    }

    public void setProxyAddress(String proxyAddress) {
        this.proxyAddress = proxyAddress == null ? null : proxyAddress.trim();
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

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
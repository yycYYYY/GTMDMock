package com.gtmdmock.admin.model.entity;

public class Expectations {
    private Integer id;

    private Integer projectId;

    private String expectationsName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getExpectationsName() {
        return expectationsName;
    }

    public void setExpectationsName(String expectationsName) {
        this.expectationsName = expectationsName == null ? null : expectationsName.trim();
    }
}
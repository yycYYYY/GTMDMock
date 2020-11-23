package com.gtmdmock.admin.model.entity;

public class Expectations {
    private Integer id;

    private Integer projectId;

    private Integer expectationsId;

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

    public Integer getExpectationsId() {
        return expectationsId;
    }

    public void setExpectationsId(Integer expectationsId) {
        this.expectationsId = expectationsId;
    }

    public String getExpectationsName() {
        return expectationsName;
    }

    public void setExpectationsName(String expectationsName) {
        this.expectationsName = expectationsName == null ? null : expectationsName.trim();
    }
}
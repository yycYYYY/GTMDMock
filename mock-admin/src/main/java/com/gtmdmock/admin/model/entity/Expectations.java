package com.gtmdmock.admin.model.entity;

public class Expectations {
    private Integer id;

    private Integer projectId;

    //@TODO 需要表内新增这个字段
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

    public Integer getExpectationsId() {
        return expectationsId;
    }

    public void setExpectationsId(Integer expectationsId) {
        this.expectationsId = expectationsId;
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
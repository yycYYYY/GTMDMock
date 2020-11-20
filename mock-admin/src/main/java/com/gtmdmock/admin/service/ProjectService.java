package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.core.client.ClientInfo;

import java.util.List;

public interface ProjectService {

    Project getProjectByName();

    Project getProjectById();

    void updateProject(Project project);

    void deleteProjectById(Integer id);

    void deleteProjectByName(String projectName);

    List<Project> getAllProjects();

    List<ClientInfo> getAllClientInfos();
}

package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.core.client.ClientInfo;

import java.util.List;

public interface ProjectService {

    Project getProjectByName(String name);

    Project getProjectById(Integer id);

    void inertProject(Project project);

    void updateProject(Project project);

    void deleteProjectById(Integer id);

    void deleteProjectByName(String projectName);

    void insertProjectToCore(Project project);

    void updateProjectOfCore(Project project);

    void deleteProjectOfCore(Integer id);

    List<Project> getAllProjects();

    ClientInfo getClientInfo(Project project);

    List<ClientInfo> getAllClientInfos();
}

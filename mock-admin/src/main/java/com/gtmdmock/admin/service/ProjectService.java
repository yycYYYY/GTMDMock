package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.core.client.ClientInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProjectService {

    Project getProjectByName(String name);

    Project getProjectById(Integer id);

    void insertProject(Project project);

    void updateProject(Project project);

    void deleteProjectById(Integer id);

    void deleteProjectByName(String projectName);

    void insertProjectToCore(Project project);

    void updateProjectOfCore(Project project);

    void deleteProjectOfCore(Integer id);

    List<Project> getAllProjects();

    List<Project> getAllProjects(Integer pageNumber, Integer pageSize);

    ClientInfo getClientInfo(Project project);

    List<ClientInfo> getAllOpenClientInfos();

    void replay(Integer projectId,String path, Integer save);

    void switchProject(Integer projectId, Integer isOpen);
}

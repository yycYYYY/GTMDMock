package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.admin.model.entity.ProjectExample;
import com.gtmdmock.admin.model.mapper.ProjectMapper;
import com.gtmdmock.admin.service.ProjectService;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.client.ClientAction;
import com.gtmdmock.core.client.ClientInfo;
import org.mockserver.model.HttpForward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final Bootstrap bootstrap = Bootstrap.getInstance();

    @Autowired
    ProjectMapper projectMapper;

    @Override
    public Project getProjectByName(String name) {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andProjectNameEqualTo(name);
        return projectMapper.selectByExample(example).get(0);
    }

    @Override
    public Project getProjectById(Integer id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void inertProject(Project project) {
        projectMapper.insert(project);
    }

    @Override
    public void updateProject(Project project) {
        projectMapper.updateByPrimaryKey(project);
    }

    @Override
    public void deleteProjectById(Integer id) {
        projectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteProjectByName(String projectName) {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andProjectNameEqualTo(projectName);
        projectMapper.deleteByExample(example);
    }

    @Override
    public void insertProjectToCore(Project project) {
        ClientAction clientAction = bootstrap.getClientAction();
        clientAction.newClient(getClientInfo(project));
    }

    @Override
    public void updateProjectOfCore(Project project) {
//        todo:更新project of core
    }

    @Override
    public void deleteProjectOfCore(Integer id) {
//        todo:删除project of core
    }

    @Override
    public List<Project> getAllProjects() {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andIdIsNotNull();
        return projectMapper.selectByExample(example);
    }

    @Override
    public ClientInfo getClientInfo(Project project) {
        ClientInfo info = new ClientInfo();
        info.setProjectName(project.getProjectName());
        info.setProjectId(project.getId());
        Optional.ofNullable(project.getProxyAddress()).ifPresent(info::setProxyAddress);
        Optional.ofNullable(project.getPort()).ifPresent(info::setPort);
        if (project.getIsSecure() != null){
            info.setScheme(project.getIsSecure() == 1? HttpForward.Scheme.HTTPS: HttpForward.Scheme.HTTP);
        }
        return info;
    }

    @Override
    public List<ClientInfo> getAllClientInfos() {
        List<Project> configs = getAllProjects();
        List<ClientInfo> infos = new ArrayList<>();
        for (Project config:configs){
            ClientInfo info = getClientInfo(config);
            infos.add(info);
        }
        return infos;
    }
}

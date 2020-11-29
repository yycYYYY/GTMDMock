package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.admin.model.entity.ProjectExample;
import com.gtmdmock.admin.model.mapper.ProjectMapper;
import com.gtmdmock.admin.service.ProjectService;
import com.gtmdmock.core.client.ClientInfo;
import org.mockserver.model.HttpForward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectMapper mapper;

    @Override
    public Project getProjectByName(String name) {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andProjectNameEqualTo(name);
        return mapper.selectByExample(example).get(0);
    }

    @Override
    public Project getProjectById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void inertProject(Project project) {
        mapper.insert(project);
    }

    @Override
    public void updateProject(Project project) {
        mapper.updateByPrimaryKey(project);
    }

    @Override
    public void deleteProjectById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteProjectByName(String projectName) {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andProjectNameEqualTo(projectName);
        mapper.deleteByExample(example);
    }

    @Override
    public List<Project> getAllProjects() {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andIdIsNotNull();
        return mapper.selectByExample(example);
    }

    @Override
    public List<ClientInfo> getAllClientInfos() {
        List<Project> configs = getAllProjects();
        List<ClientInfo> infos = new ArrayList<>();
        for (Project config:configs){
            ClientInfo info = new ClientInfo();
            info.setProjectName(config.getProjectName());
            info.setProjectId(config.getId());
            Optional.ofNullable(config.getProxyAddress()).ifPresent(info::setProxyAddress);
            Optional.ofNullable(config.getPort()).ifPresent(info::setPort);
            if (config.getIsSecure() != null){
                info.setScheme(config.getIsSecure() == 1? HttpForward.Scheme.HTTPS: HttpForward.Scheme.HTTP);
            }
            infos.add(info);
        }
        return infos;
    }
}

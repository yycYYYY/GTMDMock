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
    public Project getProjectByName() {
        return null;
    }

    @Override
    public Project getProjectById() {
        return null;
    }

    @Override
    public void updateProject(Project project) {

    }

    @Override
    public void deleteProjectById(Integer id) {

    }

    @Override
    public void deleteProjectByName(String projectName) {

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

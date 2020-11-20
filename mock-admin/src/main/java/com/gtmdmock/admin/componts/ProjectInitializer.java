package com.gtmdmock.admin.componts;

import com.gtmdmock.admin.service.ProjectService;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.client.ClientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectInitializer implements ApplicationRunner {

    @Autowired
    ProjectService projectService;

    Bootstrap bootstrap = Bootstrap.getInstance();


    public void initProject(){

        List<ClientInfo> infos = projectService.getAllClientInfos();
        bootstrap.initClients(infos);
    }

    public void initExpectations(){

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initProject();
    }
}

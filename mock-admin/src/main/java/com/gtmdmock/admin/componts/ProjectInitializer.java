package com.gtmdmock.admin.componts;

import com.gtmdmock.admin.service.ExpectationsService;
import com.gtmdmock.admin.service.ProjectService;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.client.ClientInfo;
import com.gtmdmock.core.expectation.ExpectationsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectInitializer implements ApplicationRunner {

    @Autowired
    ProjectService projectService;

    @Autowired
    ExpectationsService expectationsService;

    Bootstrap bootstrap = Bootstrap.getInstance();


    public void initProject(){

        List<ClientInfo> infos = projectService.getAllClientInfos();
        bootstrap.initClients(infos);
    }

    public void initExpectations(){
        List<ExpectationsTemplate> expectations = expectationsService.getAllExpectationsOfCore();
        bootstrap.initExpectations(expectations);
    }

    @Override
    public void run(ApplicationArguments args){
        initProject();
        initExpectations();
    }
}

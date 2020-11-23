package com.gtmdmock.admin.componts;

import com.gtmdmock.admin.model.mapper.RequestMapper;
import com.gtmdmock.admin.service.*;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.client.ClientInfo;
import com.gtmdmock.core.expectation.ExpectationsTemplate;
import com.gtmdmock.core.request.RequestMatcher;
import org.mockserver.mock.Expectation;
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

    @Autowired
    RequestService requestService;

    @Autowired
    ExpectationService expectationService;

    private Bootstrap bootstrap = Bootstrap.getInstance();


    private void initProject(){

        List<ClientInfo> infos = projectService.getAllClientInfos();
        bootstrap.initClients(infos);
    }

    private void initExpectations(){
        List<ExpectationsTemplate> expectations = expectationsService.getAllExpectationsOfCore();
        bootstrap.initExpectations(expectations);

        for (ExpectationsTemplate expectationsTemplate: bootstrap.getExpectations()){
            List<RequestMatcher> requestMatchers = requestService
                    .getRequestsOfCore(requestService
                            .getRequestByExpectationsId(expectationsTemplate.getExpectationsId()));

            for (RequestMatcher requestMatcher:requestMatchers){
                Expectation expectation = expectationService.getExpectation(requestMatcher);
                expectationsTemplate.getExpectationList().add(expectation);
            }

            expectationsTemplate.setOpen();
        }


    }

    @Override
    public void run(ApplicationArguments args){
        initProject();
        initExpectations();
    }
}

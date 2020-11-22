package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.mapper.ResponseMapper;
import com.gtmdmock.admin.service.ExpectationService;
import com.gtmdmock.admin.service.ForwardService;
import com.gtmdmock.admin.service.OverrideForwardService;
import com.gtmdmock.admin.service.ResponseService;
import com.gtmdmock.core.expectation.ExpectationAction;
import com.gtmdmock.core.request.RequestMatcher;
import com.gtmdmock.core.response.ResponseTemplate;
import org.mockserver.mock.Expectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpectationServiceImpl implements ExpectationService {

    @Autowired
    ResponseService responseService;

    @Autowired
    ForwardService forwardService;

    @Autowired
    OverrideForwardService overrideForwardService;

    ExpectationAction action = new ExpectationAction();



    @Override
    public Expectation getExpectation(RequestMatcher requestMatcher) {
        Expectation expectation;
        switch (requestMatcher.getMatcherType()){
            case "response":
                ResponseTemplate responseTemplate = responseService
                        .getResponseOfCore(responseService
                                .getResponsesByRequestId(requestMatcher.getRequestId()));
                return action.genExpectation(requestMatcher.buildRequest(),responseTemplate.buildResponse());
//TODO：这里需要补充完剩下三种类型
            case "forward":
                return null;

            case "error":
                return null;

            case "overrideForward":
                return null;
        }

        return null;
    }
}

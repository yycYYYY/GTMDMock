package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.mapper.ResponseMapper;
import com.gtmdmock.admin.service.*;
import com.gtmdmock.core.expectation.ExpectationAction;
import com.gtmdmock.core.forward.ForwardTemplate;
import com.gtmdmock.core.forward.OverrideForwardTemplate;
import com.gtmdmock.core.httperror.ErrorTemplate;
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

    @Autowired
    ErrorService errorService;

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

            case "forward":
                ForwardTemplate forwardTemplate = forwardService
                        .getForwardOfCore(forwardService
                                .getForwardByRequestId(requestMatcher.getRequestId()));
                return action.genExpectation(requestMatcher.buildRequest(),forwardTemplate.buildForward());

            case "error":
                ErrorTemplate errorTemplate = errorService
                        .getErrorOfCore(errorService
                        .getErrorByRequestId(requestMatcher.getRequestId()));
                return action.genExpectation(requestMatcher.buildRequest(),errorTemplate.buildError());

            case "overrideForward":
                OverrideForwardTemplate overrideForwardTemplate = overrideForwardService
                        .getOverrideForwardOfCore(overrideForwardService
                        .getOverrideForwardByRequestId(requestMatcher.getRequestId()));
                return action.genExpectation(requestMatcher.buildRequest(),overrideForwardTemplate.buildOverrideForward());
        }

        return null;
    }
}

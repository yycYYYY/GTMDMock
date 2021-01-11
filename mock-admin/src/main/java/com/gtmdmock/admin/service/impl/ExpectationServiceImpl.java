package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.constants.ResponseTypeConstants;
import com.gtmdmock.admin.model.entity.Expectations;
import com.gtmdmock.admin.model.entity.ExpectationsExample;
import com.gtmdmock.admin.model.mapper.ExpectationsMapper;
import com.gtmdmock.admin.service.*;
import com.gtmdmock.core.expectation.ExpectationGenerator;
import com.gtmdmock.core.forward.ForwardTemplate;
import com.gtmdmock.core.forward.OverrideForwardTemplate;
import com.gtmdmock.core.httperror.ErrorTemplate;
import com.gtmdmock.core.request.RequestMatcher;
import com.gtmdmock.core.response.ResponseTemplate;
import org.mockserver.mock.Expectation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpectationServiceImpl implements ExpectationService {

    private final Logger logger = LoggerFactory.getLogger(ExpectationServiceImpl.class);

    @Autowired
    ExpectationsMapper expectationsMapper;

    @Autowired
    ResponseService responseService;

    @Autowired
    ForwardService forwardService;

    @Autowired
    OverrideForwardService overrideForwardService;

    @Autowired
    ErrorService errorService;

    ExpectationGenerator action = new ExpectationGenerator();



    @Override
    public Expectation getExpectation(RequestMatcher requestMatcher) {

        switch (requestMatcher.getMatcherType()){
            case ResponseTypeConstants.RESPONSE:
                ResponseTemplate responseTemplate = responseService
                        .getResponseOfCore(responseService
                                .getResponsesByRequestId(requestMatcher.getRequestId()));
                return action.genExpectation(requestMatcher.buildRequest(),responseTemplate.buildResponse());

            case ResponseTypeConstants.FORWARD:
                ForwardTemplate forwardTemplate = forwardService
                        .getForwardOfCore(forwardService
                                .getForwardByRequestId(requestMatcher.getRequestId()));
                return action.genExpectation(requestMatcher.buildRequest(),forwardTemplate.buildForward());

            case ResponseTypeConstants.ERROR:
                ErrorTemplate errorTemplate = errorService
                        .getErrorOfCore(errorService
                        .getErrorByRequestId(requestMatcher.getRequestId()));
                return action.genExpectation(requestMatcher.buildRequest(),errorTemplate.buildError());

            case ResponseTypeConstants.OVERRIDE_FORWARD:
                OverrideForwardTemplate overrideForwardTemplate = overrideForwardService
                        .getOverrideForwardOfCore(overrideForwardService
                        .getOverrideForwardByRequestId(requestMatcher.getRequestId()));
                return action.genExpectation(requestMatcher.buildRequest(),overrideForwardTemplate.buildOverrideForward());

            case ResponseTypeConstants.NONE:
                return action.genExpectation(requestMatcher.buildRequest());
        }

        return null;
    }

    @Override
    public List<Expectations> getExpectationsByProjectId(Integer projectId) {
        ExpectationsExample expectationsExample = new ExpectationsExample();
        expectationsExample.createCriteria().andProjectIdEqualTo(projectId);
        return expectationsMapper.selectByExample(expectationsExample);
    }
}

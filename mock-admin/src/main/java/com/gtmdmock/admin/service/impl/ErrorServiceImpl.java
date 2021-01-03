package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.Error;
import com.gtmdmock.admin.model.entity.ErrorExample;
import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.admin.model.mapper.ErrorMapper;
import com.gtmdmock.admin.service.ErrorService;
import com.gtmdmock.admin.service.RequestService;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.expectation.ExpectationAction;
import com.gtmdmock.core.expectation.ExpectationsAction;
import com.gtmdmock.core.expectation.ExpectationsTemplate;
import com.gtmdmock.core.httperror.ErrorTemplate;
import com.gtmdmock.core.request.RequestMatcher;
import org.mockserver.mock.Expectation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ErrorServiceImpl implements ErrorService {

    private final Logger logger = LoggerFactory.getLogger(ErrorServiceImpl.class);

    @Autowired
    ErrorMapper errorMapper;

    @Autowired
    RequestService requestService;

    private final Bootstrap bootstrap = Bootstrap.getInstance();

    private final ExpectationsAction expectationsAction = bootstrap.getExpectationsAction();

    private final ExpectationAction expectationUtils = new ExpectationAction();

    @Override
    public void insertError(Error error) {
        errorMapper.insert(error);
    }

    @Override
    public void updateError(Error error) {
        errorMapper.updateByPrimaryKey(error);
    }

    @Override
    public void deleteErrorById(Integer id) {
        errorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteErrorByRequestId(Integer requestId) {
        ErrorExample example = new ErrorExample();
        example.createCriteria().andRequestIdEqualTo(requestId);
        errorMapper.deleteByExample(example);
    }

    @Override
    public void insertErrorToCore(Error error) {
        this.insertError(error);
        Request request = requestService.getRequestById(error.getRequestId());
        RequestMatcher requestMatcher = requestService.getRequestOfCore(request);

        Expectation expectation = expectationUtils.genExpectation(requestMatcher.buildRequest(),getErrorOfCore(error).buildError());
        ExpectationsTemplate template = expectationsAction.getExpectationTemplate(request.getExpectationsId());
        logger.info("在{}期望集下requestId为{}的error",request.getExpectationsId(),request.getId());

        template.updateExpectation(expectation);
    }

    @Override
    public void updateErrorOfCore(Error error) {
        this.updateError(error);

        Request request = requestService.getRequestById(error.getRequestId());
        RequestMatcher requestMatcher = requestService.getRequestOfCore(request);

        Expectation expectation = expectationUtils.genExpectation(requestMatcher.buildRequest(),getErrorOfCore(error).buildError());
        ExpectationsTemplate template = expectationsAction.getExpectationTemplate(request.getExpectationsId());
        logger.info("在{}期望集下requestId为{}的error",request.getExpectationsId(),request.getId());

        template.updateExpectation(expectation);
    }

    @Override
    public void deleteErrorOfCore(Integer requestId) {
        this.deleteErrorByRequestId(requestId);
        Request request = requestService.getRequestById(requestId);

        request.setResponseType("none");
        requestService.updateRequest(request);

        Expectation expectation = expectationUtils.genExpectation(requestService.getRequestOfCore(request).buildRequest());
        ExpectationsTemplate template = expectationsAction.getExpectationTemplate(request.getExpectationsId());

        template.updateExpectation(expectation);
    }

    @Override
    public Error getErrorByRequestId(Integer requestId) {
        ErrorExample example = new ErrorExample();
        example.createCriteria().andRequestIdEqualTo(requestId);
        return errorMapper.selectByExample(example).get(0);
    }

    @Override
    public ErrorTemplate getErrorOfCore(Error error) {
        ErrorTemplate template = new ErrorTemplate();
        Optional.ofNullable(error.getRequestId()).ifPresent(template:: setRequestId);
        Optional.of(error.getDelay()).ifPresent(template:: setDelay);
        Optional.ofNullable(error.getResponse()).ifPresent(template::setResponse);
        if (Optional.ofNullable(error.getIsDropConnection()).isPresent()){
            template.setDropConnection(error.getIsDropConnection() == 1);
        }
        return template;
    }

    @Override
    public List<ErrorTemplate> getErrorsOfCore(List<Error> errors) {
        List<ErrorTemplate> templates = new ArrayList<>();
        for (Error error: errors){
            templates.add(getErrorOfCore(error));
        }
        return templates;
    }
}

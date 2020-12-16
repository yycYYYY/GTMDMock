package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.admin.model.entity.RequestExample;
import com.gtmdmock.admin.model.mapper.RequestMapper;
import com.gtmdmock.admin.service.RequestService;
import com.gtmdmock.admin.utils.JsonUtils;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.expectation.ExpectationAction;
import com.gtmdmock.core.expectation.ExpectationsAction;
import com.gtmdmock.core.expectation.ExpectationsTemplate;
import com.gtmdmock.core.request.RequestMatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    private final Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

    private final Bootstrap bootstrap = Bootstrap.getInstance();

    private final ExpectationsAction expectationsAction = bootstrap.getExpectationsAction();

    private final ExpectationAction expectationUtils = new ExpectationAction();

    @Autowired
    RequestMapper requestMapper;

    @Override
    public void insertRequest(Request request) {
        requestMapper.insert(request);
        logger.info("{}",request.getId());
    }

    @Override
    public void updateRequest(Request request) {
        requestMapper.updateByPrimaryKey(request);
    }

    @Override
    public void deleteRequest(Integer id) {
        requestMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertRequestToCore(Request request) {
        this.insertRequest(request);
        ExpectationsTemplate template = expectationsAction.getExpectationTemplate(request.getExpectationsId());
        template.updateExpectation(expectationUtils.genExpectation(getRequestOfCore(request).buildRequest()));

    }

    @Override
    public void updateRequestOfCore(Request request) {
        this.updateRequest(request);
        ExpectationsTemplate template = expectationsAction.getExpectationTemplate(request.getExpectationsId());
        template.updateExpectation(expectationUtils.genExpectation(getRequestOfCore(request).buildRequest()));

    }

    @Override
    public void deleteRequestOfCore(Integer id) {
        this.deleteRequest(id);
        Request request = getRequestById(id);
        ExpectationsTemplate template = expectationsAction.getExpectationTemplate(request.getExpectationsId());
        template.deleteExpectation(expectationUtils.genExpectation(getRequestOfCore(request).buildRequest()));
    }

    @Override
    public List<Request> getAllRequests() {
        RequestExample example = new RequestExample();
        example.createCriteria().andIdIsNotNull();
        return requestMapper.selectByExample(example);
    }

    @Override
    public Request getRequestById(Integer id) {

        return requestMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Request> getRequestByExpectationsId(Integer expectationsId) {
        RequestExample example = new RequestExample();
        example.createCriteria().andExpectationsIdEqualTo(expectationsId);
        return requestMapper.selectByExample(example);
    }

    @Override
    public RequestMatcher getRequestOfCore(Request request) {
        RequestMatcher requestMatcher = new RequestMatcher();
        Optional.ofNullable(request.getPath()).ifPresent(requestMatcher::setPath);
        Optional.ofNullable(request.getBody()).ifPresent(requestMatcher::setBody);
        Optional.ofNullable(request.getExpectationsId()).ifPresent(requestMatcher::setExpectationId);
        Optional.ofNullable(request.getMethod()).ifPresent(requestMatcher::setMethod);
        Optional.ofNullable(request.getId()).ifPresent(requestMatcher::setRequestId);
        Optional.ofNullable(request.getMatcherType()).ifPresent(requestMatcher::setMatcherType);

        if (request.getHeaders() != null && !request.getHeaders().equals("")){
            requestMatcher.setHeaders(JsonUtils.StringToMap(request.getHeaders()));
        }

        if (request.getCookies() != null && !request.getCookies().equals("")){
            requestMatcher.setCookies(JsonUtils.StringToMap(request.getCookies()));
        }

        if (request.getPathParams() != null && !request.getPathParams().equals("")){
            requestMatcher.setPathParams(JsonUtils.StringToMap(request.getPathParams()));
        }

        if (request.getQueryParams() != null && !request.getQueryParams().equals("")){
            requestMatcher.setQueryParams(JsonUtils.StringToMap(request.getQueryParams()));
        }

        if (request.getIsKeepAlive() != null){
            requestMatcher.setKeepAlive(request.getIsKeepAlive() == 1);
        }

        if (request.getIsSecure() != null){
            requestMatcher.setSecure(request.getIsSecure() == 1);
        }

        return requestMatcher;
    }

    @Override
    public List<RequestMatcher> getRequestsOfCore(List<Request> requests) {
        List<RequestMatcher> httpRequests = new ArrayList<>();
        for (Request request: requests){
            httpRequests.add(getRequestOfCore(request));
        }
        return httpRequests;
    }
}

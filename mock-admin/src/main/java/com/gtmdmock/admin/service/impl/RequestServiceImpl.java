package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.admin.model.entity.RequestExample;
import com.gtmdmock.admin.model.mapper.RequestMapper;
import com.gtmdmock.admin.service.RequestService;
import com.gtmdmock.admin.utils.JsonUtils;
import com.gtmdmock.core.request.RequestMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestMapper mapper;

    @Override
    public List<Request> getAllRequests() {
        RequestExample example = new RequestExample();
        example.createCriteria().andIdIsNotNull();
        return mapper.selectByExample(example);
    }

    @Override
    public Request getRequestById(Integer id) {

        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Request> getRequestByExpectationsId(Integer expectationsId) {
        RequestExample example = new RequestExample();
        example.createCriteria().andExpectationsIdEqualTo(expectationsId);
        return mapper.selectByExample(example);
    }

    @Override
    public RequestMatcher getRequestOfCore(Request request) {
        RequestMatcher requestMatcher = new RequestMatcher();
        Optional.ofNullable(request.getPath()).ifPresent(requestMatcher::setPath);
        Optional.ofNullable(request.getBody()).ifPresent(requestMatcher::setBody);
        Optional.ofNullable(request.getExpectationsId()).ifPresent(requestMatcher::setExpectationId);
        Optional.ofNullable(request.getMethod()).ifPresent(requestMatcher::setMethod);
        Optional.ofNullable(request.getRequestId()).ifPresent(requestMatcher::setRequestId);
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

//        if (request.getIsecure() != null){
//            requestMatcher.setSecure(request.getIsecure() == 1);
//        }

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

package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.core.request.RequestMatcher;

import java.util.List;

public interface RequestService {

    List<Request> getAllRequests();

    Request getRequestById(Integer id);

    List<Request> getRequestByExpectationsId(Integer expectationsId);

    RequestMatcher getRequestOfCore(Request request);

    List<RequestMatcher> getRequestsOfCore(List<Request> requests);
}

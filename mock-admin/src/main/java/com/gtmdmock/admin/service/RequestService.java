package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.core.request.RequestMatcher;

import java.util.List;

public interface RequestService {

    void insertRequest(Request request);

    void updateRequest(Request request);

    void deleteRequest(Integer id);

    void insertRequestToCore(Request request);

    void updateRequestOfCore(Request request);

    void  deleteRequestOfCore(Integer id);

    List<Request> getAllRequests();

    Request getRequestById(Integer id);

    List<Request> getRequestByExpectationsId(Integer expectationsId);

    RequestMatcher getRequestOfCore(Request request);

    List<RequestMatcher> getRequestsOfCore(List<Request> requests);
}

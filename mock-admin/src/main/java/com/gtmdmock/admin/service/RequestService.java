package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.admin.model.entity.Response;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.util.List;

public interface RequestService {

    List<Request> getAllRequests();

    Request getRequestById(Integer id);

    List<Request> getRequestByExpectationId(Integer expectationId);

    HttpRequest getRequestOfCore(Request request);

    List<HttpRequest> getRequestsOfCore(List<Request> requests);
}

package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Response;
import org.mockserver.model.HttpResponse;

import java.util.List;

public interface ResponseService {

    List<Response> getResponsesByRequestId(Integer requestId);

    HttpResponse getResponseOfCore(Response response);

    List<HttpResponse> getResponsesOfCore(List<Response> responses);
}

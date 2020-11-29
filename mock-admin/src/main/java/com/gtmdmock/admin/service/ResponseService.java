package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Response;
import com.gtmdmock.core.response.ResponseTemplate;
import org.mockserver.model.HttpResponse;

import java.util.List;

public interface ResponseService {

    void insertResponse(Response response);

    void updateResponse(Response response);

    void deleteResponseById(Integer id);

    void deleteResponseByRequestId(Integer requestId);

    void deleteResponseOfCore(Integer requestId);

    Response getResponsesByRequestId(Integer requestId);

    ResponseTemplate getResponseOfCore(Response response);

    List<ResponseTemplate> getResponsesOfCore(List<Response> responses);
}

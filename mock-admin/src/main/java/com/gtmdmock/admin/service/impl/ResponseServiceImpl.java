package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.RequestExample;
import com.gtmdmock.admin.model.entity.Response;
import com.gtmdmock.admin.model.entity.ResponseExample;
import com.gtmdmock.admin.model.mapper.ResponseMapper;
import com.gtmdmock.admin.service.ResponseService;
import com.gtmdmock.admin.utils.JsonUtils;
import com.gtmdmock.core.response.ResponseTemplate;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    ResponseMapper mapper;

    @Override
    public List<Response> getResponsesByRequestId(Integer requestId) {

        ResponseExample example = new ResponseExample();
        example.createCriteria().andRequestIdEqualTo(requestId);
        return mapper.selectByExample(example);
    }

    @Override
    public HttpResponse getResponseOfCore(Response response) {
        ResponseTemplate template = new ResponseTemplate();
        Optional.ofNullable(response.getStatusCode()).ifPresent(template::setStatusCode);
        Optional.ofNullable(response.getBody()).ifPresent(template::setBody);
        Optional.ofNullable(response.getDelay()).ifPresent(template::setDelay);

        //注意，此处的处理可能有问题，MediaType.parse的源码太长了，没有完全阅读，但大致应该是这意思
        if (response.getContentType() != null || !response.getContentType().equals("")){
            template.setContentType(MediaType.parse(response.getContentType()));
        }

        if (response.getHeaders() != null && !response.getHeaders().equals("")){
            template.setHeaders(JsonUtils.StringToMap(response.getHeaders()));
        }

        if (response.getCookies() != null && !response.getCookies().equals("")){
            template.setCookies(JsonUtils.StringToMap(response.getCookies()));
        }

        return template.buildResponse();
    }

    @Override
    public List<HttpResponse> getResponsesOfCore(List<Response> responses) {
        List<HttpResponse> httpResponses = new ArrayList<>();

        for (Response response: responses){
            httpResponses.add(getResponseOfCore(response));
        }

        return httpResponses;
    }

}

package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.RequestExample;
import com.gtmdmock.admin.model.entity.Response;
import com.gtmdmock.admin.model.entity.ResponseExample;
import com.gtmdmock.admin.model.mapper.RequestMapper;
import com.gtmdmock.admin.model.mapper.ResponseMapper;
import com.gtmdmock.admin.service.RequestService;
import com.gtmdmock.admin.service.ResponseService;
import com.gtmdmock.admin.utils.JsonUtils;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.expectation.ExpectationsAction;
import com.gtmdmock.core.expectation.ExpectationsTemplate;
import com.gtmdmock.core.request.RequestMatcher;
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
    RequestService requestService;

    @Autowired
    ResponseMapper responseMapper;

    private final Bootstrap bootstrap = Bootstrap.getInstance();

    private final ExpectationsAction expectationsAction = bootstrap.getExpectationsAction();

    @Override
    public void insertResponse(Response response) {
        responseMapper.insert(response);
    }

    @Override
    public void updateResponse(Response response) {
        responseMapper.updateByPrimaryKey(response);
    }

    @Override
    public void deleteResponseById(Integer id) {
        responseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteResponseByRequestId(Integer requestId) {
        ResponseExample example = new ResponseExample();
        example.createCriteria().andRequestIdEqualTo(requestId);
        responseMapper.deleteByExample(example);
    }

    @Override
    public void insertResponseToCOre(Response response) {
        this.insertResponse(response);
        //TODO:增加core中的response，完了战线拉的太长，有点想不起来之前的代码逻辑了，周末看下
        RequestMatcher request = requestService.getRequestOfCore(requestService.getRequestById(response.getRequestId()));
    }

    @Override
    public void updateResponseOfCore(Response response) {
        this.updateResponse(response);
        //TODO:更新core中的response
    }

    @Override
    public void deleteResponseOfCore(Integer requestId) {
        this.deleteResponseById(requestId);
        //TODO:删除core中的response
    }

    @Override
    public Response getResponsesByRequestId(Integer requestId) {

        ResponseExample example = new ResponseExample();
        example.createCriteria().andRequestIdEqualTo(requestId);
        return responseMapper.selectByExample(example).get(0);
    }

    @Override
    public ResponseTemplate getResponseOfCore(Response response) {
        ResponseTemplate template = new ResponseTemplate();
        Optional.ofNullable(response.getStatusCode()).ifPresent(template::setStatusCode);
        Optional.ofNullable(response.getBody()).ifPresent(template::setBody);
        Optional.ofNullable(response.getDelay()).ifPresent(template::setDelay);

        //注意，此处的处理可能有问题，MediaType.parse的源码太长了，没有完全阅读，但大致应该是这意思
        if (Optional.ofNullable(response.getContentType()).isPresent() && !response.getContentType().equals("")){
            template.setContentType(MediaType.parse(response.getContentType()));
        }

        if (Optional.ofNullable(response.getHeaders()).isPresent() && !response.getHeaders().equals("")){
            template.setHeaders(JsonUtils.StringToMap(response.getHeaders()));
        }

        if (Optional.ofNullable(response.getCookies()).isPresent() && !response.getCookies().equals("")){
            template.setCookies(JsonUtils.StringToMap(response.getCookies()));
        }

        return template;
    }

    @Override
    public List<ResponseTemplate> getResponsesOfCore(List<Response> responses) {
        List<ResponseTemplate> responseTemplates = new ArrayList<>();

        for (Response response: responses){
            responseTemplates.add(getResponseOfCore(response));
        }

        return responseTemplates;
    }

}

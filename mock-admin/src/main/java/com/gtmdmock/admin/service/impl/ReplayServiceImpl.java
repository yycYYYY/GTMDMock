package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.Expectations;
import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.admin.model.entity.Response;
import com.gtmdmock.admin.service.ExpectationsService;
import com.gtmdmock.admin.service.ReplayService;
import com.gtmdmock.admin.service.RequestService;
import com.gtmdmock.admin.service.ResponseService;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.client.ClientAction;
import com.gtmdmock.core.client.ServerClient;
import com.gtmdmock.core.expectation.ExpectationAction;
import org.mockserver.mock.Expectation;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.LogEventRequestAndResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Service
public class ReplayServiceImpl implements ReplayService {

    private final Logger logger = LoggerFactory.getLogger(ReplayServiceImpl.class);

    private final ClientAction clientAction = Bootstrap.getInstance().getClientAction();

    private final ExpectationAction expectationAction = new ExpectationAction();

    @Autowired
    ExpectationsService expectationsService;

    @Autowired
    RequestService requestService;

    @Autowired
    ResponseService responseService;

    @Override
    public Boolean replayAll(Integer projectId) {
        ServerClient client = clientAction.getClient(projectId);
        Expectation[] expectations = clientAction.retrieveAllExpectations(client);
        client.upsert(expectations);
        return true;
    }

    @Override
    public Boolean replay(Integer projectId, String path) {
        String finalPath = path + "/*";
        ServerClient client = clientAction.getClient(projectId);
        Expectation[] expectations = clientAction.retrieveExpectationsByPath(client,finalPath);
        client.upsert(expectations);
        return true;
    }

    @Override
    public Boolean replayAllAndSave(Integer projectId) {
        //TODO:如果往库里存的话，肯定有一种情况会发生，就是接口的响应长度大于数据库可存储的最大长度，这个要想下怎么解决
        Expectations expectations = getNewExpectations(projectId);
        expectationsService.insertExpectationsToCore(expectations);
        int expectationsId = expectations.getId();
        logger.info("录制-->新增录制结果期望集，期望集id：{}",expectationsId);

        ServerClient client = clientAction.getClient(projectId);

        LogEventRequestAndResponse[] logEventRequestAndResponses =
                clientAction.retrieveAllRequestAndResponse(client);

        for (LogEventRequestAndResponse requestAndResponse: logEventRequestAndResponses){
            //将RequestAndResponse注册到client中
            insertRequestAndResponseTOClient(client,requestAndResponse);
            int requestId = saveRequestToAdmin(expectationsId,(HttpRequest) requestAndResponse.getHttpRequest());
            saveResponseToAdmin(requestId,requestAndResponse.getHttpResponse());
        }

        return true;
    }

    @Override
    public Boolean replayAndSave(Integer projectId, String path) {

        Expectations expectations = getNewExpectations(projectId);
        expectationsService.insertExpectationsToCore(expectations);
        int expectationsId = expectations.getId();
        logger.info("录制-->新增录制结果期望集，期望集id：{}",expectationsId);

        String finalPath = path + "/*";
        ServerClient client = clientAction.getClient(projectId);

        LogEventRequestAndResponse[] logEventRequestAndResponses =
                clientAction.retrieveRequestAndResponseByPath(client,finalPath);

        for (LogEventRequestAndResponse requestAndResponse: logEventRequestAndResponses){
            //将RequestAndResponse注册到client中
            insertRequestAndResponseTOClient(client,requestAndResponse);
            int requestId = saveRequestToAdmin(expectationsId,(HttpRequest) requestAndResponse.getHttpRequest());
            saveResponseToAdmin(requestId,requestAndResponse.getHttpResponse());
        }
        return true;
    }


    private void insertRequestAndResponseTOClient(ServerClient client,LogEventRequestAndResponse requestAndResponse){
        Expectation expectation = expectationAction.genExpectation((HttpRequest) requestAndResponse.getHttpRequest(),
                requestAndResponse.getHttpResponse());
        client.upsert(expectation);
    }

    private Expectations getNewExpectations(Integer projectId){

        DateTimeFormatter FORMAT_FOURTEEN = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        Expectations expectations = new Expectations();
        String name = "录制期望集" + FORMAT_FOURTEEN.format(LocalDateTime.now());

        expectations.setProjectId(projectId);
        expectations.setExpectationsName(name);
        return expectations;
    }

    private Integer saveRequestToAdmin(Integer expectationsId,HttpRequest httpRequest){

        Request request = new Request();
        //下面没做空指针处理，可能也有毛病
        request.setIsSecure(httpRequest.isSecure()?1:0);
        request.setResponseType("response");
        request.setIsKeepAlive(httpRequest.isKeepAlive()?1:0);
        //这里可能有问题
        request.setMethod(httpRequest.getMethod("get"));
        request.setPath(httpRequest.getPath().toString());
        request.setExpectationsId(expectationsId);
        requestService.insertRequest(request);
        logger.info("录制-->新增一个request，id:{}",request.getId());
        return request.getId();
    }

    private void saveResponseToAdmin(Integer requestId, HttpResponse httpResponse){
        Response response = new Response();
        response.setRequestId(requestId);
        response.setStatusCode(httpResponse.getStatusCode());
        response.setContentType(httpResponse.getBody().getContentType());
        response.setBody(httpResponse.getBodyAsString());
        //TODO:这里对于header和cookie的处理可能是有问题的
        if (Optional.ofNullable(httpResponse.getHeaders()).isPresent()){
            response.setHeaders((httpResponse.getHeaders().toString()));
        }
        if (Optional.ofNullable(httpResponse.getCookies()).isPresent()){
            response.setCookies((httpResponse.getCookies().toString()));
        }
        responseService.insertResponse(response);
        logger.info("录制-->新增一个response，id:{}",response.getId());
    }
}

package com.gtmdmock.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.gtmdmock.admin.model.constants.ResponseTypeConstants;
import com.gtmdmock.admin.model.entity.*;
import com.gtmdmock.admin.model.mapper.ProjectMapper;
import com.gtmdmock.admin.service.ExpectationsService;
import com.gtmdmock.admin.service.ProjectService;
import com.gtmdmock.admin.service.RequestService;
import com.gtmdmock.admin.service.ResponseService;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.client.ClientAction;
import com.gtmdmock.core.client.ClientInfo;
import com.gtmdmock.core.client.ServerClient;
import com.gtmdmock.core.expectation.ExpectationGenerator;
import com.gtmdmock.core.expectation.ExpectationsAction;
import com.gtmdmock.core.expectation.ExpectationsTemplate;
import org.mockserver.mock.Expectation;
import org.mockserver.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final Logger logger = LoggerFactory.getLogger("ProjectServiceImpl.class");

    private final ClientAction clientAction = Bootstrap.getInstance().getClientAction();

    private final ExpectationsAction expectationsAction = Bootstrap.getInstance().getExpectationsAction();

    private final ExpectationGenerator expectationGenerator = new ExpectationGenerator();

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ExpectationsService expectationsService;

    @Autowired
    RequestService requestService;

    @Autowired
    ResponseService responseService;

    @Override
    public Project getProjectByName(String name) {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andProjectNameEqualTo(name);
        return projectMapper.selectByExample(example).get(0);
    }

    @Override
    public Project getProjectById(Integer id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insertProject(Project project) {
        projectMapper.insert(project);
    }

    @Override
    public void updateProject(Project project) {
        projectMapper.updateByPrimaryKey(project);
    }

    @Override
    public void deleteProjectById(Integer id) {
        projectMapper.deleteByPrimaryKey(id);
        expectationsService.deleteExpectationsByProjectId(id);
    }

    @Override
    public void deleteProjectByName(String projectName) {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andProjectNameEqualTo(projectName);
        projectMapper.deleteByExample(example);
    }

    @Override
    public void insertProjectToCore(Project project) {
        this.insertProject(project);
        clientAction.newClient(getClientInfo(project));
    }

    @Override
    public void updateProjectOfCore(Project project) {
        this.updateProject(project);
        clientAction.deleteClient(project.getId());
        clientAction.newClient(getClientInfo(project));
    }

    @Override
    public void deleteProjectOfCore(Integer id) {
        this.deleteProjectById(id);
        clientAction.deleteClient(id);
    }

    @Override
    public List<Project> getAllProjects() {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andIdIsNotNull();
        return projectMapper.selectByExample(example);
    }

    //有分页的获取全部
    @Override
    public List<Project> getAllProjects(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        ProjectExample example = new ProjectExample();
        example.createCriteria().andIdIsNotNull();
        return projectMapper.selectByExample(example);
    }

    @Override
    public ClientInfo getClientInfo(Project project) {
        ClientInfo info = new ClientInfo();
        info.setProjectName(project.getProjectName());
        info.setProjectId(project.getId());
        Optional.ofNullable(project.getProxyAddress()).ifPresent(info::setProxyAddress);
        Optional.ofNullable(project.getPort()).ifPresent(info::setPort);
        if (project.getIsSecure() != null){
            info.setScheme(project.getIsSecure() == 1? HttpForward.Scheme.HTTPS: HttpForward.Scheme.HTTP);
        }
        return info;
    }

    //获取所有isOpen为1的项目
    @Override
    public List<ClientInfo> getAllOpenClientInfos() {
        List<Project> configs = getAllProjects();
        List<ClientInfo> infos = new ArrayList<>();
        for (Project config:configs){
            if (config.getIsOpen() == 1){
                ClientInfo info = getClientInfo(config);
                infos.add(info);
            }
        }
        return infos;
    }

    @Override
    public void replay(Integer projectId, String path, Integer save) {
        if (save == 0){
            replay(projectId,path);
        }else {
            replayAndSave(projectId,path);
        }

    }

    //TODO:关闭开启的逻辑关联可能需要商榷下
    @Override
    public void switchProject(Integer projectId, Integer isOpen) {
        Project project = getProjectById(projectId);
        project.setIsOpen(isOpen);
        updateProject(project);
        if (isOpen == 1){
            ServerClient client = clientAction.newClient(getClientInfo(project));
            List<ExpectationsTemplate> templates = expectationsAction.getExpectationsTemplatesByProjectId(projectId);
            for (ExpectationsTemplate template: templates){
                template.setServer(client);
                template.setOpen();
            }
        }else {
            clientAction.deleteClient(projectId);
        }
    }

    /**
     * 将录制的期望回放
     * @param projectId 工程id
     * @param path 需要录制的路径（所以此路径下的请求，都将被录制）
     */
    public void replay(Integer projectId, String path) {
        String finalPath = path + "/.*";
        ServerClient client = clientAction.getClient(projectId);

        LogEventRequestAndResponse[] logEventRequestAndResponses = clientAction.retrieveRequestAndResponseByPath(client,finalPath);

        for (LogEventRequestAndResponse requestAndResponse: logEventRequestAndResponses){
            //将RequestAndResponse注册到client中
            insertRequestAndResponseTOClient(client,requestAndResponse,null);
        }
    }

    public void replayAndSave(Integer projectId, String path) {

        Expectations expectations = getNewExpectations(projectId);
        expectationsService.insertExpectations(expectations);
        int expectationsId = expectations.getId();
        logger.info("录制-->新增录制结果期望集，期望集id：{}",expectationsId);

        String finalPath = path + "/.*";
        ServerClient client = clientAction.getClient(projectId);

        LogEventRequestAndResponse[] logEventRequestAndResponses = clientAction.retrieveRequestAndResponseByPath(client,finalPath);

        for (LogEventRequestAndResponse requestAndResponse: logEventRequestAndResponses){
            //将RequestAndResponse注册到client中
            insertRequestAndResponseTOClient(client,requestAndResponse,expectationsId);
            int requestId = saveRequestToAdmin(expectationsId,(HttpRequest) requestAndResponse.getHttpRequest());
            saveResponseToAdmin(requestId,requestAndResponse.getHttpResponse());
        }
    }


    private void insertRequestAndResponseTOClient(ServerClient client,LogEventRequestAndResponse requestAndResponse, Integer expectationsId){
        HttpResponse tempResponse = requestAndResponse.getHttpResponse();
        HttpRequest tempRequest = (HttpRequest) requestAndResponse.getHttpRequest();
        ExpectationsTemplate template = new ExpectationsTemplate();
        if (expectationsId != null){
            template.setProjectId(client.getProjectId());
            template.setServer(client);
            template.setExpectationsId(expectationsId);
        }

        //这里只对了状态码和body做了录制，如果对其他信息也有需求，可以后续添加
        HttpResponse response = HttpResponse.response()
                .withBody(tempResponse.getBody())
                .withStatusCode(tempResponse.getStatusCode());

        //这里只对了部分内容做了录制，如果对其他信息也有需求，可以后续添加
        HttpRequest request = HttpRequest.request()
                .withPath(tempRequest.getPath())
                .withBody(tempRequest.getBody())
                .withMethod(tempRequest.getMethod())
                .withSecure(tempRequest.isSecure());

        Expectation expectation = expectationGenerator.genExpectation(request, response);
        client.upsert(expectation);
    }

    private Expectations getNewExpectations(Integer projectId){

        DateTimeFormatter FORMAT_FOURTEEN = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        Expectations expectations = new Expectations();
        String name = "录制期望集" + FORMAT_FOURTEEN.format(LocalDateTime.now());

        expectations.setProjectId(projectId);
        expectations.setExpectationsName(name);
        expectations.setIsOpen(1);
        return expectations;
    }

    /**
     * 将录制的请求，保存至admin
     * 此处有个逻辑需要注意，在第一次录制并注册到core时，保留的时request所有的信息
     * 但保存到admin时，只保留了部分信息，对于header cookie之类的信息没有保存
     * 如果以后有需求，这里可以做下保存
     * @param expectationsId 期望集id
     * @param httpRequest 录制好的request（core）
     * @return 保存到admin中的requestId
     */
    private Integer saveRequestToAdmin(Integer expectationsId,HttpRequest httpRequest){

        Request request = new Request();
        //下面没做空指针处理，可能也有毛病
        request.setIsSecure(httpRequest.isSecure()?1:0);
        request.setResponseType(ResponseTypeConstants.RESPONSE);
        request.setIsKeepAlive(httpRequest.isKeepAlive()?1:0);
        //这里可能有问题
        request.setMethod(httpRequest.getMethod("get"));
        request.setPath(httpRequest.getPath().toString());
        request.setExpectationsId(expectationsId);
        request.setIsOpen(1);
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
        //这里对于header和cookie的处理可能是有问题的,框架本身存储的headers value中包含了[]不符合http规范，导致返回给调用方，报错
//        if (Optional.ofNullable(httpResponse.getHeaders()).isPresent()){
//            response.setHeaders((httpResponse.getHeaders().toString()));
//        }
//        if (Optional.ofNullable(httpResponse.getCookies()).isPresent()){
//            response.setCookies((httpResponse.getCookies().toString()));
//        }
        responseService.insertResponse(response);
        logger.info("录制-->新增一个response，id:{}",response.getId());
    }
}

package com.gtmdmock.core.client;

import com.gtmdmock.core.expectation.ExpectationsTemplate;

import com.gtmdmock.core.expectation.ExpectationsAction;
import org.mockserver.mock.Expectation;
import org.mockserver.model.HttpForward;
import org.mockserver.model.HttpOverrideForwardedRequest;
import org.mockserver.model.LogEventRequestAndResponse;


import java.util.*;


import static org.mockserver.model.HttpRequest.request;

public class ClientAction {

    private Map<Integer,ClientInfo> clientInfos = new HashMap<>();
    private Map<Integer,ServerClient> clients = new HashMap<>();

    public Map<Integer, ClientInfo> getClientInfos() {
        return clientInfos;
    }

    public void setClientInfos(Map<Integer, ClientInfo> clientInfos) {
        this.clientInfos = clientInfos;
    }

    public void setClientInfos(List<ClientInfo> clientInfos) {
        for (ClientInfo c : clientInfos) {
            this.clientInfos.put(c.getProjectId(),c);
        }
    }

    public Map<Integer, ServerClient> getClients() {
        return clients;
    }

    public ServerClient getClient(Integer projectId){
        return this.clients.get(projectId);
    }

    public void setClients(Map<Integer, ServerClient> clients) {
        this.clients = clients;
    }

    //初始化客户端全局代理配置
    public void setGlobalProxy(ServerClient server, String proxyAddress, HttpForward.Scheme scheme){

        int port = scheme == HttpForward.Scheme.HTTPS?443:80;
        boolean isSecure = scheme == HttpForward.Scheme.HTTPS;

        Expectation proxyExpectation = new Expectation(
                request().withPath("/.*")
        ).withPriority(-1).thenForward(
                HttpOverrideForwardedRequest.forwardOverriddenRequest(
                        request().withHeader("Host",proxyAddress)
                                .withSocketAddress(proxyAddress,port)
                                .withSecure(isSecure)
                )
        );

        server.upsert(proxyExpectation);
    }

    public ServerClient newClient(ClientInfo info){
        this.clientInfos.put(info.getProjectId(),info);
        ServerClient server = new ServerClient(info.getPort());
        server.setProjectId(info.getProjectId());
        if (info.getProxyAddress() != null){
            setGlobalProxy(server,info.getProxyAddress(),info.getScheme());
        }
        this.clients.put(server.getProjectId(),server);
        return server;
    }

    public void deleteClient(Integer clientId){

        ServerClient client = this.clients.get(clientId);
        client.stop();
        this.clients.remove(clientId);
        this.clientInfos.remove(clientId);

    }

    //实例化mock server客户端，并监听端口
    public Map<Integer,ServerClient> clientInstantiate(){

        clients = new HashMap<>();

        if (this.clientInfos != null){
            for (Map.Entry<Integer,ClientInfo> entry : clientInfos.entrySet()){
                ServerClient server = new ServerClient(entry.getValue().getPort());
                server.setProjectId(entry.getValue().getProjectId());
                if (entry.getValue().getProxyAddress() != null){
                    setGlobalProxy(server,entry.getValue().getProxyAddress(),entry.getValue().getScheme());
                }
                clients.put(server.getProjectId(),server);
            }
        }

        return clients;
    }

    //获取所有的记录expectations
    public Expectation[] retrieveAllExpectations(ServerClient client){

        return client.retrieveRecordedExpectations(
                request()
        );
    }

    //获取某Path下所有的记录expectations
    public Expectation[] retrieveExpectationsByPath(ServerClient client,String path){

        return client.retrieveRecordedExpectations(
                request()
                .withPath(path)
        );
    }

    //获取所有的记录RequestAndResponse
    public LogEventRequestAndResponse[] retrieveAllRequestAndResponse(ServerClient client){

        return client.retrieveRecordedRequestsAndResponses(
                request()
        );
    }

    //获取某Path下所有的记录RequestAndResponse
    public LogEventRequestAndResponse[] retrieveRequestAndResponseByPath(ServerClient client,String path){

        return client.retrieveRecordedRequestsAndResponses(
                request()
                        .withPath(path)
        );
    }

//    TODO：现在还有一个问题还没有做，在项目部署之后，还需要做一个清除内存中全量log的功能：每天固定时间，清除log；或者每次录制操作之后清除log；或者暴漏一个清除log的方法入口，写个shell脚本监听内存使用，超过份额自动调一下
}

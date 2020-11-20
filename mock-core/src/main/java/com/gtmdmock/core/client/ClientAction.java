package com.gtmdmock.core.client;

import com.gtmdmock.core.expectation.Expectations;

import com.gtmdmock.core.expectation.ExpectationsAction;
import org.mockserver.mock.Expectation;
import org.mockserver.model.HttpForward;
import org.mockserver.model.HttpOverrideForwardedRequest;


import java.util.ArrayList;
import java.util.List;


import static org.mockserver.model.HttpRequest.request;

public class ClientAction {

    private List<ClientInfo> clientInfos;
    private List<ServerClient> clients;

    //TODO 开启客户端
    //TODO 关闭客户端

    public List<ClientInfo> getClientInfos() {
        return clientInfos;
    }

    public void setClientInfos(List<ClientInfo> clientInfos) {
        this.clientInfos = clientInfos;
    }

    public List<ServerClient> getClients() {
        return clients;
    }

    public void setClients(List<ServerClient> clients) {
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

    //实例化mock server客户端，并监听端口，@TODO 并将客户端，开关信息等，写入缓存
    public List<ServerClient> clientInstantiate(){

        clients = new ArrayList<>();

        if (this.clientInfos != null){
            for (ClientInfo info : clientInfos){
                ServerClient server = new ServerClient(info.getPort());
                server.setProjectId(info.getProjectId());
                if (info.getProxyAddress() != null){
                    setGlobalProxy(server,info.getProxyAddress(),info.getScheme());
                }
                clients.add(server);
            }
        }

        return clients;
    }

    //初始化客户端期望信息
    public void clientInit(){
        List<Expectations> expectations = ExpectationsAction.genAllExpections();
        for (Expectations e: expectations) {

            for (ServerClient client: this.clients){
                if (client.getProjectId().equals(e.getProjectId())){
                    e.setServer(client);
                    e.initClient();
                }
            }
        }


    }


}

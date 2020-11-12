package com.gtmdmock.core.client;

import com.gtmdmock.core.model.DTO.ClientInfo;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.mock.Expectation;
import org.mockserver.model.HttpForward;
import org.mockserver.model.HttpOverrideForwardedRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.mockserver.model.HttpRequest.request;

public class ClientInitializer {

    List<ClientInfo> clientInfos;
    List<ServerClient> clients;

    @Resource
    ClientAction action;


    //拉取当前所有mock项目配置
    public void getProfile(){

        List<ClientInfo> clientInfos = new ArrayList<>();
        clientInfos.add(new ClientInfo("test"));
        this.clientInfos = clientInfos;
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

        List<ServerClient> clients = new ArrayList<>();

        getProfile();

        if (this.clientInfos != null){
            for (ClientInfo info : clientInfos){
                ServerClient server = new ServerClient(info.getPort());
                server.setProjectId(info.getProjectId());
                setGlobalProxy(server,info.getProxyAddress(),info.getScheme());
                clients.add(server);
            }
        }
        this.clients = clients;

        return clients;
    }

    //初始化客户端期望信息
    public void clientInit(List<ServerClient> clients,List<Exception> expectations){


    }


}

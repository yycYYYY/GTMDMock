package com.gtmdmock.core.client;

import org.mockserver.integration.ClientAndServer;

import java.util.List;

public class ClientInitializer {

    List<ClientAndServer> clients;

    //拉取当前所有mock项目配置
    public List<Object> pullAllProfile(){
        return null;
    }

    //实例化mock server客户端，并监听端口，并将客户端，开关信息等，写入缓存
    public List<ClientAndServer> clientInstantiate(List<Object> profile){
        return null;
    }

    //初始化客户端期望信息
    public void clientInit(List<ClientAndServer> clients,List<Exception> expectations){

        for (ClientAndServer client:
             clients) {
            client.stop();
        }
    }


}

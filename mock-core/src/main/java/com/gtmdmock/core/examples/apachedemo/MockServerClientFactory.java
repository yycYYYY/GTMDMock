package com.gtmdmock.core.examples.apachedemo;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.netty.MockServer;

public class MockServerClientFactory {
    public ClientAndServer getClient(){
        //这里给定可用端口为1080-1100，判断当前端口是否可用

        ClientAndServer server = new ClientAndServer(1080);

        return server;
    }
    public static void main(String[] args) {
        ClientAndServer server = new ClientAndServer(1080);
        MockServer server1 = new MockServer(1090);

        System.out.println(server.getLocalPorts());


    }
}

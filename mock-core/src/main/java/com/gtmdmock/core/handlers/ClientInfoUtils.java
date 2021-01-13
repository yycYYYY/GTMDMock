package com.gtmdmock.core.handlers;

import com.gtmdmock.core.client.ClientInfo;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class ClientInfoUtils {

    public static ClientInfo getClientInfo(HttpRequest httpRequest){
        ClientInfo info = new ClientInfo();
        //从header中获取出host
        String host = httpRequest.headers().get("host");
        //从host中获取出端口
        String[] hostStrArr = host.split(":");
        int port = 80;
        if (hostStrArr.length > 1) {
            port = Integer.parseInt(hostStrArr[1]);
        } else if (httpRequest.uri().startsWith("https")) {
            port = 443;
        }
        info.setProxyAddress(hostStrArr[0]);
        info.setPort(port);
        return info;
    }

    public static ClientInfo getClientInfo(Channel channel, HttpRequest httpRequest) {
        ClientInfo clientInfo;
        Attribute<ClientInfo> clientRequestAttribute = channel.attr(AttributeKey.valueOf("clientInfo"));
        if (clientRequestAttribute.get() == null){
            clientInfo = getClientInfo(httpRequest);
        //将clientRequest保存到channel中
            clientRequestAttribute.setIfAbsent(clientInfo);
        }{
            clientInfo = clientRequestAttribute.get();
        }
        return clientInfo;
    }
}

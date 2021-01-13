package com.gtmdmock.core.handlers;

import com.gtmdmock.core.client.ClientInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class HttpsProxyHandler extends ChannelInboundHandlerAdapter {

    private Logger logger =  LoggerFactory.getLogger(HttpProxyHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        if (msg instanceof HttpRequest){
            HttpRequest request = (HttpRequest) msg;
            //获取请求信息
            ClientInfo info = ClientInfoUtils.getClientInfo(request);


        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        logger.info("HTTPS连接发生异常");
        cause.printStackTrace();
        ctx.close();
    }
}

package com.gtmdmock.core.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 长连接处理信道Handler
 */
public class SocketProxyHandler extends ChannelInboundHandlerAdapter {

    Logger logger =  LoggerFactory.getLogger(SocketProxyHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        logger.debug("[SocksProxyHandler]");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        logger.info("Socket连接发生异常");
        cause.printStackTrace();
        ctx.close();
    }
}

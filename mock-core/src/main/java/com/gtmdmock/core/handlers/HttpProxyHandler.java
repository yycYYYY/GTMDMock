package com.gtmdmock.core.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class HttpProxyHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = (Logger) LoggerFactory.getLogger(HttpProxyHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("连接发生异常");
        cause.printStackTrace();
        ctx.close();
    }
}

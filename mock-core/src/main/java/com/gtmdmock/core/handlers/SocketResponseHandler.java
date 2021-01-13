package com.gtmdmock.core.handlers;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SocketResponseHandler extends ChannelInboundHandlerAdapter {

    private Channel clientChannel;

    public SocketResponseHandler(Channel clientChannel) {
        this.clientChannel = clientChannel;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //直接返回给客户端
        ctx.channel().writeAndFlush(msg);
    }
}

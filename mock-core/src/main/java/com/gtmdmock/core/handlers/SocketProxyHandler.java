package com.gtmdmock.core.handlers;

import com.gtmdmock.core.client.ClientInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 长连接处理信道Handler
 */
public class SocketProxyHandler extends ChannelInboundHandlerAdapter {

    Logger logger =  LoggerFactory.getLogger(SocketProxyHandler.class);
    private ChannelFuture notHttpReuqstCf;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        logger.debug("[SocksProxyHandler]");
        Attribute<ClientInfo> clientRequestAttribute = ctx.channel().attr(AttributeKey.valueOf("clientInfo"));
        ClientInfo clientInfo = clientRequestAttribute.get();
        sendToServer(clientInfo, ctx, msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        logger.info("Socket连接发生异常");
        cause.printStackTrace();
        ctx.close();
    }

    public void sendToServer(ClientInfo clientInfo, ChannelHandlerContext ctx, Object msg) {
        //不是http请求就不管，全转发出去
        if (notHttpReuqstCf == null) {
            //连接至目标服务器
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(ctx.channel().eventLoop())
                    // 复用客户端连接线程池
                    .channel(ctx.channel().getClass())
                    // 使用NioSocketChannel来作为连接用的channel类
                    .handler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new SocketResponseHandler(ctx.channel()));
                        }
                    });
            notHttpReuqstCf = bootstrap.connect(clientInfo.getProxyAddress(), clientInfo.getPort());
            notHttpReuqstCf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        future.channel().writeAndFlush(msg);
                    } else {
                        ctx.channel().close();
                    }
                }
            });
        } else {
            notHttpReuqstCf.channel().writeAndFlush(msg);
        }
    }
}

package com.gtmdmock.core.handlers;

import com.gtmdmock.core.client.ClientInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;

import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpProxyHandler extends ChannelInboundHandlerAdapter {

    private Logger logger =  LoggerFactory.getLogger(HttpProxyHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        if (msg instanceof HttpRequest){
            String[] uri = ((HttpRequest) msg).headers().get("host").split(":");
            String host = uri[0];

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        logger.info("HTTP连接发生异常");
        cause.printStackTrace();
        ctx.close();
    }

    public void sendToServer(ClientInfo clientInfo, ChannelHandlerContext ctx, Object msg) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(ctx.channel().eventLoop())
                // 注册线程池
                .channel(ctx.channel().getClass())
                // 使用NioSocketChannel来作为连接用的channel类
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        //添加接收远程server的handler
                        ch.pipeline().addLast(new HttpRequestEncoder());
                        ch.pipeline().addLast(new HttpResponseDecoder());
                        ch.pipeline().addLast(new HttpObjectAggregator(6553600));
                        //代理handler,负责给客户端响应结果
                        ch.pipeline().addLast(new HttpProxyResponseHandler(ctx.channel()));
                    }
                });

        //连接远程server
        ChannelFuture cf = bootstrap.connect(clientInfo.getProxyAddress(), clientInfo.getPort());
        cf.addListener(new ChannelFutureListener() {

            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    //连接成功
                    future.channel().writeAndFlush(msg);
                    logger.debug("[operationComplete] connect remote server success!");
                } else {
                    //连接失败
                    logger.error("[operationComplete] 连接远程server失败了");
                    ctx.channel().close();
                }
            }
        });
    }
}

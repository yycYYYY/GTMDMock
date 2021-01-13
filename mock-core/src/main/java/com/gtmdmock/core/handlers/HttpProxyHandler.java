package com.gtmdmock.core.handlers;

import com.gtmdmock.core.client.ClientInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;

import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;
import org.mockserver.model.HttpForward;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpProxyHandler extends ChannelInboundHandlerAdapter {

    private Logger logger =  LoggerFactory.getLogger(HttpProxyHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        if (msg instanceof HttpRequest){
            ClientInfo clientInfo = ClientInfoUtils.getClientInfo(ctx.channel(), (HttpRequest) msg);
            if (sendSuccessResponseIfConnectMethod(ctx, ((HttpRequest) msg).method().name())){
                logger.debug("[HttpProxyHandler][channelRead] sendSuccessResponseConnect");
                ctx.channel().pipeline().remove("httpRequestDecoder");
                ctx.channel().pipeline().remove("httpResponseEncoder");
                ctx.channel().pipeline().remove("httpAggregator");
                ReferenceCountUtil.release(msg);
                return;
            }
            if (clientInfo.getScheme() == HttpForward.Scheme.HTTPS){
                try {
                    super.channelRead(ctx, msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
            sendToServer(clientInfo, ctx, msg);
            return;
        }
        try {
            super.channelRead(ctx,msg);
        } catch (Exception e) {
            logger.info("channel read failed");
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        logger.info("HTTP连接发生异常");
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 如果是connect请求的话，返回连接建立成功
     *
     * @param ctx        ChannelHandlerContext
     * @param methodName 请求类型名
     * @return 是否为connect请求
     */
    private boolean sendSuccessResponseIfConnectMethod(ChannelHandlerContext ctx, String methodName) {
        if ("CONNECT".equalsIgnoreCase(methodName)) {
            //代理建立成功
            //HTTP代理建立连接
            HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, new HttpResponseStatus(200, "Connection established"));
            ctx.writeAndFlush(response);
            return true;
        }
        return false;
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

package com.gtmdmock.core.handlers;

import com.gtmdmock.core.client.ClientInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.mockserver.model.HttpForward;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpsProxyHandler extends ChannelInboundHandlerAdapter {

    private Logger logger =  LoggerFactory.getLogger(HttpProxyHandler.class);
    private ChannelFuture httpsRequestCf;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        Attribute<ClientInfo> clientRequestAttribute = ctx.channel().attr(AttributeKey.valueOf("clientInfo"));
        ClientInfo clientInfo = clientRequestAttribute.get();
        if (msg instanceof HttpRequest) {
            sendToServer(clientInfo, ctx, msg);
        } else if (msg instanceof HttpContent) {
            logger.debug("[HttpsProxyHandler][HttpContent]不作处理！");
            //content不做处理
//            ReferenceCountUtil.release(msg);
        } else {
            ByteBuf byteBuf = (ByteBuf) msg;
            // ssl握手
            if (byteBuf.getByte(0) == 22) {
                logger.debug("[HttpsProxyHandler][do hands]");
                sendToClient(clientInfo, ctx, msg);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        logger.info("HTTPS连接发生异常");
        cause.printStackTrace();
        ctx.close();
    }

    public void sendToServer(ClientInfo clientInfo, ChannelHandlerContext ctx, Object msg) {
        logger.debug("[HttpsProxyHandler][sendToServer] 发送https请求到server");
        Channel clientChannel = ctx.channel();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup(1))
                // 注册线程池
                .channel(NioSocketChannel.class)
                // 使用NioSocketChannel来作为连接用的channel类
                .handler(new ChannelInitializer() {

                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        //添加一个ssl处理器进行处理
                        ch.pipeline().addLast(
                                HttpsSupport.getInstance().getClientSslCtx().newHandler(ch.alloc(),
                                        clientInfo.getProxyAddress(), clientInfo.getPort()));
                        ch.pipeline().addLast("httpCodec", new HttpClientCodec());
                        //添加响应处理器
                        ch.pipeline().addLast("proxyClientHandle", new HttpProxyResponseHandler(clientChannel));
                    }
                });
        httpsRequestCf = bootstrap.connect(clientInfo.getProxyAddress(), clientInfo.getPort());
        //建立连接
        httpsRequestCf.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                future.channel().writeAndFlush(msg);
                logger.debug("[HttpsProxyHandler][sendToServer]目标连接创建成功，并已转发了数据包");
            } else {
                logger.error("[HttpsProxyHandler][sendToServer]连接远程server失败");
            }
        });
    }

    public void sendToClient(ClientInfo clientInfo, ChannelHandlerContext ctx, Object msg) {
        try {
            logger.debug("[HttpsProxyHandler][sendToClient] 与客户端进行https握手");
            SslContext sslCtx = SslContextBuilder
                    .forServer(HttpsSupport.getInstance().getServerPriKey(), HttpsSupport.getInstance().getCert(clientInfo.getProxyAddress())).build();
            //接收客户端请求，将客户端的请求内容解码
            ctx.pipeline().addFirst("httpRequestDecoder", new HttpRequestDecoder());
            //发送响应给客户端，并将发送内容编码
            ctx.pipeline().addFirst("httpResponseEncoder", new HttpResponseEncoder());
            //http聚合
            ctx.pipeline().addLast("httpAggregator", new HttpObjectAggregator(65536));
            //ssl处理
            ctx.pipeline().addFirst("sslHandle", sslCtx.newHandler(ctx.alloc()));
            // 重新过一遍pipeline，拿到解密后的的http报文
            ctx.pipeline().fireChannelRead(msg);
            Attribute<ClientInfo> clientRequestAttribute = ctx.channel().attr(AttributeKey.valueOf("clientInfo"));
            clientInfo.setScheme(HttpForward.Scheme.HTTPS);
            clientRequestAttribute.set(clientInfo);
        } catch (Exception e) {
            logger.error("[sendToServer] err:{}", e.getMessage());
        }
    }
}

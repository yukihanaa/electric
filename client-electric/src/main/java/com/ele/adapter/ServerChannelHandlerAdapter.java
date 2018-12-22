package com.ele.adapter;

import com.ele.config.RedisService;
import com.ele.dispatcher.RequestDispatcher;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Component
@Sharable
public class ServerChannelHandlerAdapter extends ChannelHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerChannelHandlerAdapter.class);

    private WebSocketServerHandshaker handshaker ;
    /**
     * 注入请求分排器
     */
    @Autowired
    private RequestDispatcher dispatcher;
    @Autowired
    private RedisService redisService;
    private int lossConnectCount = 0;


    /**
     * 连接上服务器
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("【handlerAdded】====>"+ctx.channel().id());
    }

    /**
     * 断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("【handlerRemoved】====>"+ctx.channel().id());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("{} -> [连接异常] {}通道异常，异常原因：{}", this.getClass().getName(),
                ctx.channel().id(), cause.getMessage());
        ctx.close();
    }

    /**
     * 这里只要完成 flush
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


//    /**
//     * 收发消息处理
//     * @param ctx
//     * @param msg
//     * @throws Exception
//     */
//    @Override
//    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
//        if(msg instanceof HttpRequest){
//           // doHandlerHttpRequest(ctx,(HttpRequest) msg);
//        }else if(msg instanceof WebSocketFrame){
//            doHandlerWebSocketFrame(ctx,(WebSocketFrame) msg);
//        }
//    }
//
//
//    /**
//     * websocket消息处理
//     * @param ctx
//     * @param msg
//     */
//    private void doHandlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame msg) {
//        //判断msg 是哪一种类型  分别做出不同的反应
//        if(msg instanceof CloseWebSocketFrame){
//            LOGGER.info("【关闭】");
//            handshaker.close(ctx.channel(), (CloseWebSocketFrame) msg);
//            return ;
//        }
//        if(msg instanceof PingWebSocketFrame){
//            LOGGER.info("【ping】");
//            PongWebSocketFrame pong = new PongWebSocketFrame(msg.content().retain());
//            ctx.channel().writeAndFlush(pong);
//            return ;
//        }
//        if(msg instanceof PongWebSocketFrame){
//            LOGGER.info("【pong】");
//            PingWebSocketFrame ping = new PingWebSocketFrame(msg.content().retain());
//            ctx.channel().writeAndFlush(ping);
//            return ;
//        }
//        if(!(msg instanceof TextWebSocketFrame)){
//            LOGGER.info("【不支持二进制】");
//            throw new UnsupportedOperationException("不支持二进制");
//        }
//
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        byte[] msg = ("200").getBytes();
        ByteBuf buf = Unpooled.buffer(msg.length);
        buf.writeBytes(msg);
        ctx.writeAndFlush(buf);
    }




    private FileOutputStream fileOutputStream;
    private File file;

    /**
     * 服务器接收到消息时进行进行的处理
     *
     * @param channelHandlerContext channelHandlerContext
     * @param msg                   msg
     */
    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) {
        file = new File("D://a.txt");
        try {
            fileOutputStream = new FileOutputStream(file,true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        lossConnectCount++;
        ByteBuf message = (ByteBuf) msg;
        StringBuffer storeMessage=new StringBuffer(message.toString(CharsetUtil.UTF_8));
        try {
            redisService.setObjectWithExpireTime(lossConnectCount+"",storeMessage,60L, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("传输内容是");
        LOGGER.info(storeMessage.toString());
        byte[] bytes = new byte[message.readableBytes()];
        message.readBytes(bytes);
        try {
            fileOutputStream.write(bytes);
            fileOutputStream.write("\r\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] a = ("200").getBytes();
        ByteBuf buf = Unpooled.buffer(a.length);
        buf.writeBytes(a);
        channelHandlerContext.writeAndFlush(buf);
//        if (msg instanceof String) {
//            if ("ping-pong-ping-pong".equals(msg)) {
//                LOGGER.info("{} -> [心跳监测] {}：通道活跃", this.getClass().getName(), channelHandlerContext.channel().id());
//                // 心跳消息
//                lossConnectCount = 0;
//                return;
//            }
//        }
//        // 转换为MethodInvokeMeta
//        MethodInvokeMeta invokeMeta = (MethodInvokeMeta) msg;
//        LOGGER.info("{} -> [客户端信息] \n 方法名  - > {} \n 参数列表  -> {} \n " +
//                        "返回值  ->  {} ", this.getClass().getName(), invokeMeta.getMethodName(), invokeMeta.getArgs()
//                , invokeMeta.getReturnType());
//        // 具体的处理类
//        this.dispatcher.dispatcher(channelHandlerContext, invokeMeta);
    }

    /**
     * 触发器
     *
     * @param channelHandlerContext channelHandlerContext
     * @param evt
     * @throws Exception exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            PingWebSocketFrame ping = new PingWebSocketFrame();
            switch (stateEvent.state()){
                //读空闲（服务器端）
                case READER_IDLE:
                    LOGGER.info("【"+channelHandlerContext.channel().remoteAddress()+"】读空闲（服务器端）");
                    channelHandlerContext.writeAndFlush(ping);
                    break;
                //写空闲（客户端）
                case WRITER_IDLE:
                    LOGGER.info("【"+channelHandlerContext.channel().remoteAddress()+"】写空闲（客户端）");
                    channelHandlerContext.writeAndFlush(ping);
                    break;
                case ALL_IDLE:
                    LOGGER.info("【"+channelHandlerContext.channel().remoteAddress()+"】读写空闲");
                    break;
            }
        }
//        LOGGER.info("{} -> [已经有5秒中没有接收到客户端的消息了]", this.getClass().getName());
//        if (evt instanceof IdleStateEvent) {
//            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
//            if (idleStateEvent.state() == IdleState.READER_IDLE) {
//                lossConnectCount++;
//                if (lossConnectCount > 2) {
//                    LOGGER.info("{} -> [释放不活跃通道] {}", this.getClass().getName(), channelHandlerContext.channel().id());
//                    channelHandlerContext.channel().close();
//                }
//            }
//        } else {
//            super.userEventTriggered(channelHandlerContext, evt);
//        }
    }

}

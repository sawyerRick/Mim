package cn.sawyer.mim.client.handler;

import cn.sawyer.mim.tool.enums.MsgType;
import cn.sawyer.mim.tool.protocol.MimProtocol;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.ScheduledFuture;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 20:18
 **/

@Component
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HeartBeatHandler.class);

    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MimProtocol protocol =(MimProtocol) msg;
        //如果握手成功，主动发送心跳消息
        if (protocol != null && protocol.getType() == MsgType.HANDSHAKE_RESP) {
            // 5s 发送一次心跳
            heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatHandler.HeartBeatTask(ctx),
                    0, 5000, TimeUnit.MILLISECONDS);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(heartBeat != null){
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }

    private class HeartBeatTask implements Runnable{
        private final ChannelHandlerContext ctx;
        public HeartBeatTask(final ChannelHandlerContext ctx){
            this.ctx = ctx;
        }
        public void run() {
            MimProtocol heatBeat  = buildHeatBeat();
            System.out.println("发送心跳..." + new Date().toString());
            ctx.writeAndFlush(heatBeat);
        }

        private MimProtocol buildHeatBeat() {
            MimProtocol protocol  = new MimProtocol();
            protocol.setType(MsgType.HEARTBEAT_REQ);
            return protocol;
        }

    }

}

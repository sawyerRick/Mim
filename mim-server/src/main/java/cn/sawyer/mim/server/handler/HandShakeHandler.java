package cn.sawyer.mim.server.handler;

import cn.sawyer.mim.tool.enums.MsgType;
import cn.sawyer.mim.tool.protocol.MimProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;

import static cn.sawyer.mim.server.util.ConnSessionCache.channelMap;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 20:42
 **/
public class HandShakeHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MimProtocol protocol = (MimProtocol) msg;

        if (protocol != null && protocol.getType() == MsgType.HANDSHAKE_REQ) {
            System.out.println("[+] 握手请求:" + protocol);
            MimProtocol handshakeResp = buildResponse();
            ctx.writeAndFlush(handshakeResp);
            if (channelMap.containsKey(protocol.getSrcId())) {
                // 清除重复连接
                NioSocketChannel cacheChannel = channelMap.get(protocol.getSrcId());
                System.out.println("清除连接：" + protocol);
                channelMap.remove(protocol.getSrcId());
                cacheChannel.close();
            }

            System.out.println("缓存id-channel :" + protocol.getSrcId() + "- " + ctx.channel().remoteAddress());
            channelMap.put(protocol.getSrcId(), (NioSocketChannel) ctx.channel());
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    // 构造握手响应
    private MimProtocol buildResponse() {
        MimProtocol protocol = new MimProtocol();
        protocol.setType(MsgType.HANDSHAKE_RESP);

        return protocol;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }
}

package com.fantasybaby.mybatis.websocket.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * channel handler pool
 *
 * @author fantasybaby
 */
public class SocketChannelHandlerPool {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void addToPool(Channel channel) {
        channelGroup.add(channel);
    }
    public static void removeFromPool(Channel channel) {
        channelGroup.remove(channel);
    }
    public static void writeAndFlush(TextWebSocketFrame frame){
        channelGroup.writeAndFlush(frame);
    }
}

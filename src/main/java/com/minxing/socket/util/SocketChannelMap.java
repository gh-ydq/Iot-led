package com.minxing.socket.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.netty.channel.ChannelHandlerContext;

/**
 * 存储socketChannel
 * @author yudengqiu
 *
 */
public class SocketChannelMap {
	//存储终端上行socket链接关系
	public static ConcurrentMap<String ,ChannelHandlerContext> upConcurrentMap = new ConcurrentHashMap<String ,ChannelHandlerContext>();
	
	//存储服务下行socket链接关系
	public static ConcurrentMap<String ,ChannelHandlerContext> downConcurrentMap = new ConcurrentHashMap<String ,ChannelHandlerContext>();
}

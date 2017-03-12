package com.qizhi.socket.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qizhi.socket.util.ByteArrayToNumber;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * 发送下行数据包
 * @author yudengqiu
 *
 */
public class SendDownPackage {
	private static Logger logger = LoggerFactory.getLogger(SendDownPackage.class);
	/**
	 * 发送下行心跳响应包
	 * 截取上行心跳包 前8个字节
	 * @param bytes 上行心跳包
	 * @param ctx
	 */
	public static void sendPHeartbeat(byte[] bytes,ChannelHandlerContext ctx){
		logger.info("发送下行心跳响应包");
		byte[] pheart = new byte[8];
		System.arraycopy(bytes, 0, pheart, 0, 8);
		ByteBuf encoded = ctx.alloc().buffer(pheart.length);  
        encoded.writeBytes(pheart);  
        ctx.write(encoded);  
        ctx.flush(); 
	}
	
	/**
	 * 发送下行命令响应包
	 * @param bytes 下行命令响应包
	 * @param ctx
	 */
	public static void sendCMDResp(byte[] bytes,ChannelHandlerContext ctx,byte result){
		logger.info("发送下行命令响应包");
		byte[] params =  new byte[7];
		System.arraycopy(bytes, 0, params, 0, 6);
		ByteBuf encoded = ctx.alloc().buffer(params.length);  
        encoded.writeBytes(params);  
        ctx.write(encoded);  
        ctx.flush(); 
	}
	
	/**
	 * 发送下行命令包
	 * @param bytes
	 * @param ctx 和设备IMEI号 绑定
	 */
	public static void sendDownCMD(byte[] bytes,ChannelHandlerContext ctx){
		char header0 = ByteArrayToNumber.byteToChar(bytes[0]);
		char header1 = ByteArrayToNumber.byteToChar(bytes[1]);
		int imei = ByteArrayToNumber.byteArrayToInt(bytes, 2);
		byte seq = bytes[6];
		byte cmd = bytes[7];
		byte[] params =  new byte[bytes.length-8];
		System.arraycopy(bytes, 8, params, 0, bytes.length-8);
		String param = ByteArrayToNumber.bytesToString(params);
		logger.info("发送下行命令包 header0="+header0+",header1="+header1+",ime1="+imei+",seq="+seq+",cmd="+cmd+",param="+param);
		ByteBuf encoded = ctx.alloc().buffer(bytes.length);  
        encoded.writeBytes(bytes);  
        ctx.write(encoded);  
        ctx.flush(); 
	}
}

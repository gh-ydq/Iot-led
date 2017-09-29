package com.qizhi.socket.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qizhi.socket.client.SendDownPackage;
import com.qizhi.socket.constants.DownCMDResultEnum;
import com.qizhi.socket.exception.IotServiceBizException;
import com.qizhi.socket.exception.IotServiceExceptionEnum;
import com.qizhi.socket.factory.ParsePackageServiceFactory;
import com.qizhi.socket.service.impl.ParsePackageServiceImpl;

import io.netty.channel.ChannelHandlerContext;

public class ParseByteUtil {
	private static Logger logger = LoggerFactory.getLogger(ParseByteUtil.class);
	public static byte[] iteratorBytes(byte[] bytes,ChannelHandlerContext ctx){
		char header0 = ByteArrayToNumber.byteToChar(bytes[0]);
		char header1 = ByteArrayToNumber.byteToChar(bytes[1]);
		int imei = ByteArrayToNumber.byteArrayToInt(bytes, 2);
		logger.info("header0:"+header0+",header1:"+header1+",imei:"+imei+",length:"+bytes.length);
		String imeiKey = imei+"";
		// PX 包为自定义下行包
		if('T' == header1){
			// 保存下行socket链接关系
			SocketChannelMap.downConcurrentMap.put(imeiKey, ctx);
			//发送下行命令包
			try {
				sendDownCmdPackage(bytes, imeiKey);
			} catch (Exception e) {
				logger.error("发送下行命令异常 header0:"+header0+",header1:"+header1+",imei:"+imei,e);
				throw new IotServiceBizException(IotServiceExceptionEnum.CMD_DOWN_ERROR.getCode(),IotServiceExceptionEnum.CMD_DOWN_ERROR.getMsg());
			}
		}else{
			// 保存上行socket链接关系
			SocketChannelMap.upConcurrentMap.put(imeiKey, ctx);
		}
		if('P' == header0 && 'H' == header1){
			//发送下行心跳包
			try {
				SendDownPackage.sendPHeartbeat(bytes, ctx);
			} catch (Exception e) {
				logger.error("发送下行心跳包异常 header0:"+header0+",header1:"+header1+",imei:"+imei,e);
				throw new IotServiceBizException(IotServiceExceptionEnum.PH_HEART_ERROR.getCode(),IotServiceExceptionEnum.PH_HEART_ERROR.getMsg());
			}
		}
		if('C' == header1){
			ChannelHandlerContext downCtx = SocketChannelMap.downConcurrentMap.get(imeiKey);
			//发送下行命令响应包
			try {
				SendDownPackage.sendCMDResp(bytes, downCtx,DownCMDResultEnum.SUCCESS.getResult());
			} catch (Exception e) {
				SendDownPackage.sendCMDResp(bytes, downCtx,DownCMDResultEnum.FAIL.getResult());
				logger.error("发送下行命令响应包异常 header0:"+header0+",header1:"+header1+",imei:"+imei,e);
				throw new IotServiceBizException(IotServiceExceptionEnum.CMD_DOWN_RESP_ERROR.getCode(),IotServiceExceptionEnum.CMD_DOWN_RESP_ERROR.getMsg());
			}
		}
		ParsePackageServiceImpl parsePackageService = ParsePackageServiceFactory.getParsePackageService(header0, header1);
		if(parsePackageService != null && 'T' != header1){
			parsePackageService.parseUpBytes(bytes, header0, header1,imei);	
		}
		if(parsePackageService != null && 'T' == header1){
			parsePackageService.parseDownBytes(bytes, header0, header1, imei);
		}
		return bytes;
	}
	
	private static void sendDownCmdPackage(byte[] bytes,String imeiKey){
		if(bytes.length < 9){
			String[] arg = new String[2];
			arg[0] = imeiKey;
			arg[1] = String.valueOf(bytes);
			logger.info("下行命令数据接口格式错误 bytes length={},imeiKey={},byte={}",bytes.length,arg);
			return;
		}
		ChannelHandlerContext imeiCtx = SocketChannelMap.upConcurrentMap.get(imeiKey);
		bytes[1] = 'C';
		SendDownPackage.sendDownCMD(bytes, imeiCtx);
	}
	
}

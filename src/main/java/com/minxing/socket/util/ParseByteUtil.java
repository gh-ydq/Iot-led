package com.minxing.socket.util;


import com.minxing.socket.client.SendDownPackage;
import com.minxing.socket.constants.DownCMDResultEnum;
import com.minxing.socket.dto.PackageServiceDto;
import com.minxing.socket.exception.IotServiceExceptionEnum;
import com.minxing.socket.factory.ParsePackageServiceFactory;
import com.minxing.socket.service.impl.ParsePackageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minxing.socket.exception.IotServiceBizException;

import io.netty.channel.ChannelHandlerContext;

public class ParseByteUtil {
	private static Logger logger = LoggerFactory.getLogger(ParseByteUtil.class);
	public static byte[] iteratorBytes(byte[] bytes,ChannelHandlerContext ctx){
		char header0 = ByteArrayToNumber.byteToChar(bytes[0]);
		char header1 = ByteArrayToNumber.byteToChar(bytes[1]);
		String header2 = ByteArrayToNumber.bytesToString(bytes,2,2);
		int imei = ByteArrayToNumber.byteArrayToInt(bytes, 4);
		logger.info("header0:"+header0+",header1:"+header1+",header2:"+header2+",imei:"+imei+",length:"+bytes.length);
		String imeiKey = imei+"";
		// PX 包为自定义下行包
		if('X' == header1){
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
				SendDownPackage.sendCMDResp(bytes, downCtx, DownCMDResultEnum.SUCCESS.getResult());
			} catch (Exception e) {
				SendDownPackage.sendCMDResp(bytes, downCtx,DownCMDResultEnum.FAIL.getResult());
				logger.error("发送下行命令响应包异常 header0:"+header0+",header1:"+header1+",imei:"+imei,e);
				throw new IotServiceBizException(IotServiceExceptionEnum.CMD_DOWN_RESP_ERROR.getCode(),IotServiceExceptionEnum.CMD_DOWN_RESP_ERROR.getMsg());
			}
		}
		ParsePackageServiceImpl parsePackageService = ParsePackageServiceFactory.getParsePackageService(header0, header1,header2);
		PackageServiceDto packageServiceDto = buildPackageServiceDto(bytes,header0,header1,header2,imei);
		if(parsePackageService != null && 'X' != header1){
			parsePackageService.parseUpBytes(packageServiceDto);
		}
		if(parsePackageService != null && 'X' == header1){
			parsePackageService.parseDownBytes(packageServiceDto);
		}
		return bytes;
	}

	private static PackageServiceDto buildPackageServiceDto(byte[] bytes,char header0,char header1,String busiType,int imei){
		PackageServiceDto packageServiceDto = new PackageServiceDto();
		packageServiceDto.setPackageBytes(bytes);
		packageServiceDto.setHeader0(header0);
		packageServiceDto.setHeader1(header1);
		packageServiceDto.setBusiType(busiType);
		packageServiceDto.setImei(imei);
		return packageServiceDto;
	}
	
	private static void sendDownCmdPackage(byte[] bytes,String imeiKey){
		if(bytes.length < bytes.length){
			String[] arg = new String[2];
			arg[0] = imeiKey;
			arg[1] = String.valueOf(bytes);
			logger.info("下行命令数据接口格式错误 bytes length={},imeiKey={},byte={}",bytes.length,arg);
			return;
		}
		logger.info("SocketChannelMap.upConcurrentMap"+SocketChannelMap.upConcurrentMap.values());
		ChannelHandlerContext imeiCtx = SocketChannelMap.upConcurrentMap.get(imeiKey);
		bytes[1] = 'C';
		SendDownPackage.sendDownCMD(bytes, imeiCtx);
	}
	
}

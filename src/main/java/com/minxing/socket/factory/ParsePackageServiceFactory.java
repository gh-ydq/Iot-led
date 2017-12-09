package com.minxing.socket.factory;

import com.minxing.socket.service.bike.ParsePHPackageService;
import com.minxing.socket.exception.IotServiceBizException;
import com.minxing.socket.exception.IotServiceExceptionEnum;
import com.minxing.socket.service.bike.ParsePCPackageService;
import com.minxing.socket.service.bike.ParsePLPackageService;
import com.minxing.socket.service.impl.ParsePackageServiceImpl;

public class ParsePackageServiceFactory {
	
	public static ParsePackageServiceImpl getParsePackageService(char header0,char header1){
		ParsePackageServiceImpl parsePackageService = null;
		if('P' == header0 && 'L' == header1){
			parsePackageService = new ParsePLPackageService();
		}else if('P' == header0 && 'H' == header1){
			parsePackageService = new ParsePHPackageService();
		}else if('P' == header0 && 'C' == header1){
			parsePackageService = new ParsePCPackageService();
		}else{
			String errorMsg = String.format("header0={},header1={}", header0,header1);
			throw new IotServiceBizException(IotServiceExceptionEnum.NOT_SUPPORT_SOCKET_DATA.getCode(),IotServiceExceptionEnum.NOT_SUPPORT_SOCKET_DATA.getMsg()+errorMsg);
		}
		return parsePackageService;
	}
}

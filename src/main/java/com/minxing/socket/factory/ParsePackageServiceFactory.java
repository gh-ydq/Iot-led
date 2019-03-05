package com.minxing.socket.factory;

import com.minxing.socket.constants.BusiTypeEnum;
import com.minxing.socket.service.bike.ParsePHPackageService;
import com.minxing.socket.exception.IotServiceBizException;
import com.minxing.socket.exception.IotServiceExceptionEnum;
import com.minxing.socket.service.bike.ParsePCPackageService;
import com.minxing.socket.service.bike.ParsePLPackageService;
import com.minxing.socket.service.impl.ParsePackageServiceImpl;
import com.minxing.socket.service.led.ParseLedPCPackageService;
import com.minxing.socket.service.led.ParseLedPHPackageService;

public class ParsePackageServiceFactory {
	
	public static ParsePackageServiceImpl getParsePackageService(char header0,char header1,String busiType){
		ParsePackageServiceImpl parsePackageService = null;
//		if(BusiTypeEnum.TRACKER.getType().equals(busiType)){
//			return buildTrackerPackageImpl(header0,header1);
//		}
		if(BusiTypeEnum.LED_LIGHT.getType().equals(busiType)){
			return buildLedPackageImpl(header0,header1);
		}
		return buildTrackerPackageImpl(header0,header1);
	}
	private static ParsePackageServiceImpl buildTrackerPackageImpl(char header0,char header1){
		ParsePackageServiceImpl parsePackageService = null;
		if('P' == header0 && 'L' == header1){
			parsePackageService = new ParsePLPackageService();
		}else if('P' == header0 && 'H' == header1){
			parsePackageService = new ParsePHPackageService();
		}else if('P' == header0 && 'C' == header1){
			parsePackageService = new ParsePCPackageService();
		}else{
			String errorMsg = String.format("tracker header0={},header1={}", header0,header1);
			throw new IotServiceBizException(IotServiceExceptionEnum.NOT_SUPPORT_SOCKET_DATA.getCode(),IotServiceExceptionEnum.NOT_SUPPORT_SOCKET_DATA.getMsg()+errorMsg);
		}
		return parsePackageService;
	}

	private static ParsePackageServiceImpl buildLedPackageImpl(char header0,char header1){
		ParsePackageServiceImpl parsePackageService = null;
		if('P' == header0 && 'H' == header1){
			parsePackageService = new ParseLedPHPackageService();
		}else if('P' == header0 && 'C' == header1){
			parsePackageService = new ParseLedPCPackageService();
		}else if('P' == header0 && 'X' == header1){
			parsePackageService = new ParseLedPCPackageService();
		}else{
			String errorMsg = String.format("led header0={},header1={}", header0,header1);
			throw new IotServiceBizException(IotServiceExceptionEnum.NOT_SUPPORT_SOCKET_DATA.getCode(),IotServiceExceptionEnum.NOT_SUPPORT_SOCKET_DATA.getMsg()+errorMsg);
		}
		return parsePackageService;
	}
}

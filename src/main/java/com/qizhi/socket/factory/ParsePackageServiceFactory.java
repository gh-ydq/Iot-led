package com.qizhi.socket.factory;

import com.qizhi.socket.exception.IotServiceBizException;
import com.qizhi.socket.exception.IotServiceExceptionEnum;
import com.qizhi.socket.service.bike.ParsePCPackageService;
import com.qizhi.socket.service.bike.ParsePGPackageService;
import com.qizhi.socket.service.bike.ParsePHPackageService;
import com.qizhi.socket.service.bike.ParsePLPackageService;
import com.qizhi.socket.service.chargePile.ParseMCPackageService;
import com.qizhi.socket.service.chargePile.ParseMDPackageService;
import com.qizhi.socket.service.chargePile.ParseMLPackageService;
import com.qizhi.socket.service.impl.ParsePackageServiceImpl;

public class ParsePackageServiceFactory {
	
	public static ParsePackageServiceImpl getParsePackageService(char header0,char header1){
		ParsePackageServiceImpl parsePackageService = null;
		if('P' == header0 && 'L' == header1){
			parsePackageService = new ParsePLPackageService();
		}else if('P' == header0 && 'G' == header1){
			parsePackageService = new ParsePGPackageService();
		}else if('P' == header0 && 'H' == header1){
			parsePackageService = new ParsePHPackageService();
		}else if('P' == header0 && 'C' == header1){
			parsePackageService = new ParsePCPackageService();
		}else if('P' == header0 && 'M' == header1){
			parsePackageService = new ParsePCPackageService();
		}else if('M' == header0 && 'D' == header1){
			parsePackageService = new ParseMDPackageService();
		}else if('M' == header0 && 'L' == header1){
			parsePackageService = new ParseMLPackageService();
		}else if('M' == header0 && 'C' == header1){
			parsePackageService = new ParseMCPackageService();
		}else if('M' == header0 && 'X' == header1){
			parsePackageService = new ParseMCPackageService();
		}else{
			String errorMsg = String.format("header0={},header1={}", header0,header1);
			throw new IotServiceBizException(IotServiceExceptionEnum.NOT_SUPPORT_SOCKET_DATA.getCode(),IotServiceExceptionEnum.NOT_SUPPORT_SOCKET_DATA.getMsg()+errorMsg);
		}
		return parsePackageService;
	}
}

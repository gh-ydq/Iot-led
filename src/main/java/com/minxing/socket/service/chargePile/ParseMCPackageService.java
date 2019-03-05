package com.minxing.socket.service.chargePile;

import java.util.Date;

import com.minxing.socket.dto.PackageServiceDto;
import com.minxing.socket.util.ByteArrayToNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minxing.socket.dto.baseStation.mc.MCPacketDto;
import com.minxing.socket.http.charge.MCManage;
import com.minxing.socket.service.impl.ParsePackageServiceImpl;
import com.minxing.socket.util.DateUtil;

public class ParseMCPackageService extends ParsePackageServiceImpl{
	private Logger logger = LoggerFactory.getLogger(ParseMCPackageService.class);
	@Override
	public  MCPacketDto parseUpBytes(PackageServiceDto packageServiceDto){
		logger.info("pc system:"+DateUtil.format(new Date(), DateUtil.YMDHMS_PATTERN));
		byte[] bytes = packageServiceDto.getPackageBytes();
		byte seq = bytes[6];
		byte cmd = bytes[7];
		byte[] params =  new byte[bytes.length-8];
		System.arraycopy(bytes, 8, params, 0, bytes.length-8);
		String param = ByteArrayToNumber.bytesToString(params);
		MCPacketDto mcPacketDto = buildMCPacketDto(packageServiceDto.getHeader0(), packageServiceDto.getHeader1(), packageServiceDto.getImei(),seq,cmd,param);
		MCManage pcManage = new MCManage();
		pcManage.saveUpMCInfo(mcPacketDto);
		pcManage.sendMsg(mcPacketDto);
		return mcPacketDto;
	}
	
	@Override
	public MCPacketDto parseDownBytes(PackageServiceDto packageServiceDto) {
		byte[] bytes = packageServiceDto.getPackageBytes();
		byte seq = bytes[6];
		byte cmd = bytes[7];
		byte[] params =  new byte[bytes.length-8];
		System.arraycopy(bytes, 8, params, 0, bytes.length-8);
		String param = ByteArrayToNumber.bytesToString(params);
		MCPacketDto mcPacketDto = buildMCPacketDto(packageServiceDto.getHeader0(), packageServiceDto.getHeader1()
				, packageServiceDto.getImei(),seq,cmd,param);
		MCManage pcManage = new MCManage();
		pcManage.saveDownMCInfo(mcPacketDto);
		return mcPacketDto;
	}
	
	private MCPacketDto buildMCPacketDto(char header0,char header1,int imei,
			byte seq,byte cmd,String param){
		MCPacketDto mcPacketDto = new MCPacketDto();
		mcPacketDto.setHeader0(header0);
		mcPacketDto.setHeader1(header1);
		mcPacketDto.setImei(imei);
		mcPacketDto.setSeq(seq);
		mcPacketDto.setCmd(cmd);
		mcPacketDto.setParam(param);
		logger.info("mcPacketDto["+mcPacketDto+"]");
		return mcPacketDto;
	}
}

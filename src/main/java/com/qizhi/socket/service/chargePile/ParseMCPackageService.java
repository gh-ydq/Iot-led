package com.qizhi.socket.service.chargePile;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qizhi.socket.dto.baseStation.mc.MCPacketDto;
import com.qizhi.socket.http.charge.MCManage;
import com.qizhi.socket.service.impl.ParsePackageServiceImpl;
import com.qizhi.socket.util.ByteArrayToNumber;
import com.qizhi.socket.util.DateUtil;

public class ParseMCPackageService extends ParsePackageServiceImpl{
	private Logger logger = LoggerFactory.getLogger(ParseMCPackageService.class);
	@Override
	public  MCPacketDto parseUpBytes(byte[] bytes,char header0,char header1,int imei){
		logger.info("pc system:"+DateUtil.format(new Date(), DateUtil.YMDHMS_PATTERN));
		byte seq = bytes[6];
		byte cmd = bytes[7];
		byte[] params =  new byte[bytes.length-8];
		System.arraycopy(bytes, 8, params, 0, bytes.length-8);
		String param = ByteArrayToNumber.bytesToString(params);
		MCPacketDto mcPacketDto = buildMCPacketDto(header0, header1, imei,seq,cmd,param);
		MCManage pcManage = new MCManage();
		pcManage.saveUpMCInfo(mcPacketDto);
		pcManage.sendMsg(mcPacketDto);
		return mcPacketDto;
	}
	
	@Override
	public MCPacketDto parseDownBytes(byte[] bytes,char header0,char header1,int imei) {
		byte seq = bytes[6];
		byte cmd = bytes[7];
		byte[] params =  new byte[bytes.length-8];
		System.arraycopy(bytes, 8, params, 0, bytes.length-8);
		String param = ByteArrayToNumber.bytesToString(params);
		MCPacketDto mcPacketDto = buildMCPacketDto(header0, header1, imei,seq,cmd,param);
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

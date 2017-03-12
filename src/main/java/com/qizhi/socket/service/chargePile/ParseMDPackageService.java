package com.qizhi.socket.service.chargePile;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qizhi.socket.dto.baseStation.md.MDPacketDto;
import com.qizhi.socket.http.charge.MDManage;
import com.qizhi.socket.service.impl.ParsePackageServiceImpl;
import com.qizhi.socket.util.ByteArrayToNumber;
import com.qizhi.socket.util.DateUtil;

public class ParseMDPackageService extends ParsePackageServiceImpl{
	private Logger logger = LoggerFactory.getLogger(ParseMDPackageService.class);
	@Override
	public  MDPacketDto parseUpBytes(byte[] bytes,char header0,char header1,int imei){
		logger.info("MD system:"+DateUtil.format(new Date(), DateUtil.YMDHMS_PATTERN));
		int length = bytes.length;
		short voltage = ByteArrayToNumber.byteArrayToShort(bytes, 6);
		byte electric = bytes[8];
		byte status = bytes[9];
		byte chargePortNo = bytes[10];
		byte chargeFail = bytes[11];
		int carIdNo = ByteArrayToNumber.byteArrayToInt(bytes, 12);
		MDPacketDto mdPacketDto = buildMDPacketDto(length,header0, header1, imei, voltage, electric, status,
				chargePortNo, chargeFail, carIdNo);
		MDManage mdManage = new MDManage();
		mdManage.saveMDInfo(mdPacketDto);
		mdManage.sendMsg(mdPacketDto);
		return mdPacketDto;
		}
	
	
	
	private MDPacketDto buildMDPacketDto(int length,char header0,char header1,int imei,
			short voltage,byte electric ,byte status,byte chargePortNo,byte chargeFail,int carIdNo){
		MDPacketDto mdPacketDto = new MDPacketDto();
		mdPacketDto.setLength(length);
		mdPacketDto.setHeader0(header0);
		mdPacketDto.setHeader1(header1);
		mdPacketDto.setImei(imei);
		mdPacketDto.setVoltage(voltage);
		mdPacketDto.setElectric(electric);
		mdPacketDto.setStatus(status);
		mdPacketDto.setChargePortNo(chargePortNo);
		mdPacketDto.setChargeFail(chargeFail);
		mdPacketDto.setCarIdNo(carIdNo);
		logger.info("mdPacketDto["+mdPacketDto+"]");
		return mdPacketDto;
	}
}

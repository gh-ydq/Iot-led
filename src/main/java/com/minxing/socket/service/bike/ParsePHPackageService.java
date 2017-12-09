package com.minxing.socket.service.bike;

import java.util.Date;

import com.minxing.socket.util.ByteArrayToNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minxing.socket.dto.gprs.GPRSSubStatus;
import com.minxing.socket.dto.gprs.ph.PHErrorCode;
import com.minxing.socket.dto.gprs.ph.PHPacketDto;
import com.minxing.socket.http.bike.PHManage;
import com.minxing.socket.service.impl.ParsePackageServiceImpl;
import com.minxing.socket.util.DateUtil;

public class ParsePHPackageService extends ParsePackageServiceImpl{
	private Logger logger = LoggerFactory.getLogger(ParsePHPackageService.class);
	@Override
	public  PHPacketDto parseUpBytes(byte[] bytes,char header0,char header1,int imei){
		logger.info("PH system:"+DateUtil.format(new Date(), DateUtil.YMDHMS_PATTERN));
		byte seq = bytes[6];
		long imsi = ByteArrayToNumber.bytesToLong(bytes, 7);
		byte powerSwitch = bytes[15];
		byte chargeStatus = bytes[16];
		short batteryVoltage = ByteArrayToNumber.byteArrayToShort(bytes,17);
		int activeTime  = ByteArrayToNumber.byteArrayToInt(bytes,19);
		//用电量
		int usedPowerNum  = ByteArrayToNumber.byteArrayToInt(bytes,23);
		short activeLightNum = ByteArrayToNumber.byteArrayToShort(bytes,27);
		int lightUsedHour  = ByteArrayToNumber.byteArrayToInt(bytes,29);
		PHPacketDto phPacketDto = buildPHPacketDto(bytes.length,header0, header1, imei, seq, imsi,
				powerSwitch, chargeStatus, batteryVoltage, activeTime, usedPowerNum,activeLightNum,lightUsedHour);
		PHManage phManage = new PHManage();
		phManage.sendMsg(phPacketDto);
		return phPacketDto;
   }

	private PHPacketDto buildPHPacketDto(int byteLength,char header0,char header1,int imei,
			byte seq,long imsi,byte powerSwitch,byte chargeStatus,short batteryVoltage,int activeTime,int usedPowerNum,
										 short activeLightNum,int lightUsedHour ){
		PHPacketDto phPacketDto = new PHPacketDto();
		phPacketDto.setLength(byteLength);
		phPacketDto.setHeader0(header0);
		phPacketDto.setHeader1(header1);
		phPacketDto.setImei(imei);
		phPacketDto.setSeq(seq);
		phPacketDto.setImsi(imsi);
		phPacketDto.setPowerSwitch(powerSwitch);
		phPacketDto.setChargeStatus(chargeStatus);
		phPacketDto.setBatteryVoltage(batteryVoltage);
		phPacketDto.setActiveTime(activeTime);
		phPacketDto.setUsedPowerNum(usedPowerNum);
		phPacketDto.setActiveLightNum(activeLightNum);
		phPacketDto.setLightUsedHour(lightUsedHour);
		logger.info("phPacketDto["+phPacketDto.toString()+"]");
		return phPacketDto;
	}
}

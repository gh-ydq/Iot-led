package com.minxing.socket.service.led;

import com.minxing.socket.dto.PackageServiceDto;
import com.minxing.socket.dto.gprs.ph.PHPacketDto;
import com.minxing.socket.dto.led.LedPHPacketDto;
import com.minxing.socket.http.bike.PHManage;
import com.minxing.socket.http.led.LedPHManage;
import com.minxing.socket.service.impl.ParsePackageServiceImpl;
import com.minxing.socket.util.ByteArrayToNumber;
import com.minxing.socket.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class ParseLedPHPackageService extends ParsePackageServiceImpl{
	private Logger logger = LoggerFactory.getLogger(ParseLedPHPackageService.class);
	@Override
	public LedPHPacketDto parseUpBytes(PackageServiceDto packageServiceDto){
		logger.info("LedPH system:"+DateUtil.format(new Date(), DateUtil.YMDHMS_PATTERN));
		byte[] bytes = packageServiceDto.getPackageBytes();
		byte seq = bytes[8];
		long imsi = ByteArrayToNumber.bytesToLong(bytes, 9);
		byte deviceSatus = bytes[18];
		byte chargeStatus = bytes[19];
		short batteryVotage = ByteArrayToNumber.byteArrayToShort(bytes,20);
		int activeTime = ByteArrayToNumber.byteArrayToInt(bytes,22);
		int lighteUsedCharge = ByteArrayToNumber.byteArrayToInt(bytes,26);
		short lightNum = ByteArrayToNumber.byteArrayToShort(bytes,30);
		int ledUsedHour = ByteArrayToNumber.byteArrayToInt(bytes,32);
		LedPHPacketDto phPacketDto = buildLedPHPacketDto(bytes.length,packageServiceDto.getHeader0(),packageServiceDto.getHeader1(),
				packageServiceDto.getBusiType(),packageServiceDto.getImei(),seq,imsi,deviceSatus,
				chargeStatus,batteryVotage,activeTime,lighteUsedCharge,lightNum,ledUsedHour);
		LedPHManage ledPHManage = new LedPHManage();
		ledPHManage.sendMsg(phPacketDto);
		return phPacketDto;
   }

	private LedPHPacketDto buildLedPHPacketDto(int byteLength,char header0,char header1,String busiType,int imei,
			byte seq,long imsi,byte deviceSatus,byte chargeStatus,short batteryVoltage,int activeTime,int usedPowerNum,
										 short activeLightNum,int lightUsedHour ){
		LedPHPacketDto phPacketDto = new LedPHPacketDto();
		phPacketDto.setLength(byteLength);
		phPacketDto.setHeader0(header0);
		phPacketDto.setHeader1(header1);
		phPacketDto.setBusiType(busiType);
		phPacketDto.setImei(imei);
		phPacketDto.setSeq(seq);
		phPacketDto.setImsi(imsi);
		phPacketDto.setDeviceSatus(deviceSatus);
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

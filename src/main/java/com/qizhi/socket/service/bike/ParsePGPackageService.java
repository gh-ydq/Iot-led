package com.qizhi.socket.service.bike;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qizhi.socket.dto.gprs.GPRSSubStatus;
import com.qizhi.socket.dto.gprs.pg.PGPacketDto;
import com.qizhi.socket.http.bike.PGManage;
import com.qizhi.socket.service.impl.ParsePackageServiceImpl;
import com.qizhi.socket.util.ByteArrayToNumber;

public class ParsePGPackageService extends ParsePackageServiceImpl{
	private Logger logger = LoggerFactory.getLogger(ParsePGPackageService.class);
	@Override
	public  PGPacketDto parseUpBytes(byte[] bytes,char header0,char header1,int imei){
		logger.info("开始解析PG数据包------");
		int lng = ByteArrayToNumber.byteArrayToInt(bytes, 6);
		int lat = ByteArrayToNumber.byteArrayToInt(bytes, 10);
		short hight = ByteArrayToNumber.byteArrayToShort(bytes, 14);
		short speed = ByteArrayToNumber.byteArrayToShort(bytes, 16);
		byte status = bytes[18];
		byte star = bytes[19];
		int time = ByteArrayToNumber.byteArrayToInt(bytes, 20);
		GPRSSubStatus pgSubStatus = buildPGSubStatus(status);
		PGPacketDto pgPacketDto = buildPGPacketDto(bytes.length,header0, header1, imei, lng, lat, hight, speed, status, star,time, pgSubStatus);
		PGManage pgManage = new PGManage();
//		pgManage.savePGInfo(pgPacketDto);
//		pgManage.sendMsg(pgPacketDto);
		return pgPacketDto;
	}
	
	
	// 解析GPS数据包状态
	private GPRSSubStatus buildPGSubStatus(byte status){
		GPRSSubStatus pgSubStatus = new GPRSSubStatus();
		//Bit0：电源(1接通，0断开）
		byte powerStatus = ByteArrayToNumber.biteToByte(status, 0);
		//电门锁开关  bit1
		byte eDoorSwitchStatus = ByteArrayToNumber.biteToByte(status, 1);
		//Bit2： 是否为静默模式
		byte silentModeStatus = ByteArrayToNumber.biteToByte(status, 2);
		// Bit3：蓝牙是否锁车（1布防，0撤防）
		byte bluetoothLockStatus = ByteArrayToNumber.biteToByte(status, 3);
		
		pgSubStatus.setPowerStatus(powerStatus);
		pgSubStatus.setEDoorSwitchStatus(eDoorSwitchStatus);
		pgSubStatus.setSilentModeStatus(silentModeStatus);
		pgSubStatus.setBluetoothLockStatus(bluetoothLockStatus);
		return pgSubStatus;
	}
	
	private PGPacketDto buildPGPacketDto(int bytesLength,char header0,char header1,int imei,
			int lng,int lat, short hight,short speed,
			byte status,byte star,int time,GPRSSubStatus pgSubStatus){
		PGPacketDto pgPacketDto = new PGPacketDto();
		pgPacketDto.setLength(bytesLength);
		pgPacketDto.setHeader0(header0);
		pgPacketDto.setHeader1(header1);
		pgPacketDto.setImei(imei);
		pgPacketDto.setLng(new BigDecimal(lng/1000000f).setScale(6, RoundingMode.HALF_UP).floatValue());
		pgPacketDto.setLat(new BigDecimal(lat/1000000f).setScale(6, RoundingMode.HALF_UP).floatValue());
		pgPacketDto.setHight(hight);
		pgPacketDto.setSpeed(new BigDecimal(speed/100f).setScale(2, RoundingMode.HALF_UP).floatValue());
		pgPacketDto.setStatus(status);
		pgPacketDto.setStar(star);
		pgPacketDto.setTime(time);
		pgPacketDto.setPgSubStatus(pgSubStatus);
		logger.info("pgPacketDto["+pgPacketDto.toString()+"]");
		return pgPacketDto;
	}
}

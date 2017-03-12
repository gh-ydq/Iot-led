package com.qizhi.socket.service.bike;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qizhi.socket.dto.gprs.GPRSSubStatus;
import com.qizhi.socket.dto.gprs.px.PXPacketDto;
import com.qizhi.socket.http.bike.PXManage;
import com.qizhi.socket.service.impl.ParsePackageServiceImpl;
import com.qizhi.socket.util.ByteArrayToNumber;

public class ParsePXPackageService extends ParsePackageServiceImpl{
	private Logger logger = LoggerFactory.getLogger(ParsePXPackageService.class);
	@Override
	public  PXPacketDto parseUpBytes(byte[] bytes,char header0,char header1,int imei){
		logger.info("开始解析PG数据包------");
		int lng = ByteArrayToNumber.byteArrayToInt(bytes, 6);
		int lat = ByteArrayToNumber.byteArrayToInt(bytes, 10);
		short hight = ByteArrayToNumber.byteArrayToShort(bytes, 14);
		short speed = ByteArrayToNumber.byteArrayToShort(bytes, 16);
		byte status = bytes[18];
		byte star = bytes[19];
		int time = ByteArrayToNumber.byteArrayToInt(bytes, 20);
		GPRSSubStatus pgSubStatus = buildPGSubStatus(status);
		PXPacketDto pgPacketDto = buildPGPacketDto(header0, header1, imei, lng, lat, hight, speed, status, star,time, pgSubStatus);
		PXManage pgManage = new PXManage();
		pgManage.savePXInfo(pgPacketDto);
//		pgManage.sendMsg(pgPacketDto);
		return pgPacketDto;
	}
	
	
	// 解析GPS数据包状态
	private GPRSSubStatus buildPGSubStatus(byte status){
		GPRSSubStatus pxSubStatus = new GPRSSubStatus();
		//Bit0：电源(1接通，0断开）
		byte powerStatus = ByteArrayToNumber.biteToByte(status, 0);
		//电门锁开关  bit1
		byte eDoorSwitchStatus = ByteArrayToNumber.biteToByte(status, 1);
		//Bit2： 是否为静默模式
		byte silentModeStatus = ByteArrayToNumber.biteToByte(status, 2);
		// Bit3：蓝牙是否锁车（1布防，0撤防）
		byte bluetoothLockStatus = ByteArrayToNumber.biteToByte(status, 3);
		
		pxSubStatus.setPowerStatus(powerStatus);
		pxSubStatus.setEDoorSwitchStatus(eDoorSwitchStatus);
		pxSubStatus.setSilentModeStatus(silentModeStatus);
		pxSubStatus.setBluetoothLockStatus(bluetoothLockStatus);
		System.out.println("pxSubStatus["+pxSubStatus+"]");
		return pxSubStatus;
	}
	
	private PXPacketDto buildPGPacketDto(char header0,char header1,int imei,
			int lng,int lat, short hight,short speed,
			byte status,byte star,int time,GPRSSubStatus pgSubStatus){
		PXPacketDto pxPacketDto = new PXPacketDto();
		pxPacketDto.setHeader0(header0);
		pxPacketDto.setHeader1(header1);
		pxPacketDto.setImei(imei);
		pxPacketDto.setLng(new BigDecimal(lng/1000000f).setScale(6, RoundingMode.HALF_UP).floatValue());
		pxPacketDto.setLat(new BigDecimal(lat/1000000f).setScale(6, RoundingMode.HALF_UP).floatValue());
		pxPacketDto.setHight(hight);
		pxPacketDto.setSpeed(new BigDecimal(speed/100f).setScale(2, RoundingMode.HALF_UP).floatValue());
		pxPacketDto.setStatus(status);
		pxPacketDto.setStar(star);
		pxPacketDto.setTime(time);
		pxPacketDto.setPgSubStatus(pgSubStatus);
		logger.info("pxPacketDto["+pxPacketDto+"]");
		return pxPacketDto;
	}
}

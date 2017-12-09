package com.minxing.socket.service.bike;


import java.util.Date;

import com.minxing.socket.dto.gprs.GPRSSubStatus;
import com.minxing.socket.dto.gprs.pl.PLPacketDto;
import com.minxing.socket.http.bike.PLManage;
import com.minxing.socket.util.ByteArrayToNumber;
import com.minxing.socket.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minxing.socket.service.impl.ParsePackageServiceImpl;

public class ParsePLPackageService extends ParsePackageServiceImpl{
	private Logger logger = LoggerFactory.getLogger(ParsePLPackageService.class);
	@Override
	public PLPacketDto parseUpBytes(byte[] bytes, char header0, char header1, int imei){
		logger.info("PL system:"+ DateUtil.format(new Date(), DateUtil.YMDHMS_PATTERN));
		int lac = ByteArrayToNumber.byteArrayToInt(bytes, 6);
		int cellid = ByteArrayToNumber.byteArrayToInt(bytes, 10);
		short signal = ByteArrayToNumber.byteArrayToShort(bytes, 14);
		byte status = bytes[16];
		int time = ByteArrayToNumber.byteArrayToShort(bytes, 17);
		GPRSSubStatus pgSubStatus = buildPLSubStatus(status);
		PLPacketDto plPacketDto = buildPLPacketDto(bytes.length,header0, header1, imei, lac, cellid, signal,status, time,pgSubStatus);
		PLManage plManage = new PLManage();
//		plManage.savePLInfo(plPacketDto);
//		plManage.sendMsg(plPacketDto);
		return plPacketDto;
	}
	
	
	//���status ��ȡ��״̬
	private GPRSSubStatus buildPLSubStatus(byte status){
		GPRSSubStatus plSubStatus = new GPRSSubStatus();
		//Bit0：电源(1接通，0断开）
		byte powerStatus = ByteArrayToNumber.biteToByte(status, 0);
		//电门锁开关  bit1
		byte eDoorSwitchStatus = ByteArrayToNumber.biteToByte(status, 1);
		//Bit2： 是否为静默模式
		byte silentModeStatus = ByteArrayToNumber.biteToByte(status, 2);
		// Bit3：蓝牙是否锁车（1布防，0撤防）
		byte bluetoothLockStatus = ByteArrayToNumber.biteToByte(status, 3);
		
		plSubStatus.setPowerStatus(powerStatus);
		plSubStatus.setEDoorSwitchStatus(eDoorSwitchStatus);
		plSubStatus.setSilentModeStatus(silentModeStatus);
		plSubStatus.setBluetoothLockStatus(bluetoothLockStatus);
		return plSubStatus;
	}
	
	private PLPacketDto buildPLPacketDto(int bytesLength,char header0,char header1,int imei,
			int lac,int cellid,short signal,byte status,int time,GPRSSubStatus plSubStatus){
		PLPacketDto plPacketDto = new PLPacketDto();
		plPacketDto.setLength(bytesLength);
		plPacketDto.setHeader0(header0);
		plPacketDto.setHeader1(header1);
		plPacketDto.setImei(imei);
		plPacketDto.setLac(lac);
		plPacketDto.setCellid(cellid);
		plPacketDto.setSignal(signal);
		plPacketDto.setStatus(status);
		plPacketDto.setTime(time);
		plPacketDto.setPlSubStatus(plSubStatus);
		logger.info("plPacketDto["+plPacketDto.toString()+"]");
		return plPacketDto;
	}

}

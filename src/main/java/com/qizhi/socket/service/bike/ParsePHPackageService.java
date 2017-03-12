package com.qizhi.socket.service.bike;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qizhi.socket.dto.gprs.GPRSSubStatus;
import com.qizhi.socket.dto.gprs.ph.PHErrorCode;
import com.qizhi.socket.dto.gprs.ph.PHPacketDto;
import com.qizhi.socket.http.bike.PHManage;
import com.qizhi.socket.service.impl.ParsePackageServiceImpl;
import com.qizhi.socket.util.ByteArrayToNumber;
import com.qizhi.socket.util.DateUtil;

public class ParsePHPackageService extends ParsePackageServiceImpl{
	private Logger logger = LoggerFactory.getLogger(ParsePHPackageService.class);
	@Override
	public  PHPacketDto parseUpBytes(byte[] bytes,char header0,char header1,int imei){
		logger.info("PH system:"+DateUtil.format(new Date(), DateUtil.YMDHMS_PATTERN));
		byte seq = bytes[6];
		byte status = bytes[7];
		long imsi = ByteArrayToNumber.bytesToLong(bytes, 8);
		short powerVoltage = ByteArrayToNumber.byteArrayToShort(bytes, 16);
		short batteryVotage = ByteArrayToNumber.byteArrayToShort(bytes, 18);
		byte sensity = bytes[20];
		byte star = bytes[21];
		byte ecode = bytes[22];
//		byte soc = bytes[23];
		GPRSSubStatus gprsSubStatus = buildPHSubStatus(status);
		PHErrorCode errorCode = buildPHErrorCode(ecode);
		PHPacketDto phPacketDto = buildPHPacketDto(bytes.length,header0, header1, imei, seq, status, imsi, 
				powerVoltage, batteryVotage, sensity, star, ecode,gprsSubStatus,errorCode);
		PHManage phManage = new PHManage();
//		phManage.savePHInfo(phPacketDto);
//		phManage.sendMsg(phPacketDto);
		return phPacketDto;
   }
	
	
	// 解析GPS数据包状态
	private GPRSSubStatus buildPHSubStatus(byte status){
		GPRSSubStatus phSubStatus = new GPRSSubStatus();
		//Bit0：电源(1接通，0断开）
		byte powerStatus = ByteArrayToNumber.biteToByte(status, 0);
		//电门锁开关  bit1
		byte eDoorSwitchStatus = ByteArrayToNumber.biteToByte(status, 1);
		//Bit2： 是否为静默模式
		byte silentModeStatus = ByteArrayToNumber.biteToByte(status, 2);
		// Bit3：蓝牙是否锁车（1布防，0撤防）
		byte bluetoothLockStatus = ByteArrayToNumber.biteToByte(status, 3);
		
		phSubStatus.setPowerStatus(powerStatus);
		phSubStatus.setEDoorSwitchStatus(eDoorSwitchStatus);
		phSubStatus.setSilentModeStatus(silentModeStatus);
		phSubStatus.setBluetoothLockStatus(bluetoothLockStatus);
		return phSubStatus;
	}
	
	private PHErrorCode buildPHErrorCode(byte ecode){
		PHErrorCode errorCode = new PHErrorCode();
		//无电机故障 bit0
		byte phMachineError = ByteArrayToNumber.biteToByte(ecode, 0);
		//刹车故障 bit1
		byte phBrakeErroe = ByteArrayToNumber.biteToByte(ecode, 1);
		//无转把故障 bit2
		byte phHandleBarError = ByteArrayToNumber.biteToByte(ecode, 2);
		// 无控制器故障 bit3
		byte phControlError = ByteArrayToNumber.biteToByte(ecode, 3);
		
		errorCode.setPhMachineError(phMachineError);
		errorCode.setPhBrakeErroe(phBrakeErroe);
		errorCode.setPhControlError(phControlError);
		errorCode.setPhHandleBarError(phHandleBarError);
		return errorCode;
	}
	
	private PHPacketDto buildPHPacketDto(int byteLength,char header0,char header1,int imei,
			byte seq,byte status,long imsi, short powerVoltage,short batteryVotage,
			byte sensity,byte star,byte ecode,GPRSSubStatus gprsSubStatus,PHErrorCode phErrorCode){
		PHPacketDto phPacketDto = new PHPacketDto();
		phPacketDto.setLength(byteLength);
		phPacketDto.setHeader0(header0);
		phPacketDto.setHeader1(header1);
		phPacketDto.setImei(imei);
		phPacketDto.setSeq(seq);
		phPacketDto.setStatus(status);
		phPacketDto.setImsi(imsi);
		phPacketDto.setPowerVoltage(powerVoltage);
		phPacketDto.setBatteryVotage(batteryVotage);
		phPacketDto.setSensity(sensity);
		phPacketDto.setStar(star);
		phPacketDto.setEcode(ecode);
		phPacketDto.setGprsSubStatus(gprsSubStatus);
		logger.info("phPacketDto["+phPacketDto.toString()+"]");
		return phPacketDto;
	}
}

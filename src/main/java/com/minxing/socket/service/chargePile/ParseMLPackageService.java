package com.minxing.socket.service.chargePile;


import java.util.Date;

import com.minxing.socket.dto.baseStation.ml.MLPacketDto;
import com.minxing.socket.http.charge.MLManage;
import com.minxing.socket.service.impl.ParsePackageServiceImpl;
import com.minxing.socket.util.ByteArrayToNumber;
import com.minxing.socket.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseMLPackageService extends ParsePackageServiceImpl {
	private Logger logger = LoggerFactory.getLogger(ParseMLPackageService.class);
	@Override
	public MLPacketDto parseUpBytes(byte[] bytes, char header0, char header1, int imei){
		logger.info("ML system:"+ DateUtil.format(new Date(), DateUtil.YMDHMS_PATTERN));
		int length = bytes.length;
		short lac = ByteArrayToNumber.byteArrayToShort(bytes, 6);
		short cellid = ByteArrayToNumber.byteArrayToShort(bytes, 8);
		byte signal = bytes[10];
		byte temperature = bytes[11];
		Long imsi = ByteArrayToNumber.bytesToLong(bytes, 12);
		MLPacketDto mlPacketDto =  buildMLPacketDto(length, header0, header1, imei, 
				lac, cellid, signal, temperature, imsi);
		MLManage mlManage = new MLManage();
		mlManage.sendMsg(mlPacketDto);
		return mlPacketDto;
	}
	
	
	private MLPacketDto buildMLPacketDto(int length,char header0,char header1,int imei,
			short lac,short cellid ,byte signal,byte temperature,Long imsi){
		MLPacketDto mlPacketDto = new MLPacketDto();
		mlPacketDto.setLength(length);
		mlPacketDto.setHeader0(header0);
		mlPacketDto.setHeader1(header1);
		mlPacketDto.setImei(imei);
		
		mlPacketDto.setLac(lac);
		mlPacketDto.setCellid(cellid);
		mlPacketDto.setSignal(signal);
		mlPacketDto.setTemperature(temperature);
		mlPacketDto.setImsi(imsi);
		logger.info("mlPacketDto["+mlPacketDto+"]");
		return mlPacketDto;
	}
}

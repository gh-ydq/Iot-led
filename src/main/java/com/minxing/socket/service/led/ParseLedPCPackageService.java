package com.minxing.socket.service.led;

import com.minxing.socket.dto.PackageServiceDto;
import com.minxing.socket.dto.baseStation.mc.MCPacketDto;
import com.minxing.socket.dto.led.LedPCPacketDto;
import com.minxing.socket.http.charge.MCManage;
import com.minxing.socket.http.led.LedPCManage;
import com.minxing.socket.service.impl.ParsePackageServiceImpl;
import com.minxing.socket.util.ByteArrayToNumber;
import com.minxing.socket.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class ParseLedPCPackageService extends ParsePackageServiceImpl{
	private Logger logger = LoggerFactory.getLogger(ParseLedPCPackageService.class);

	public  LedPCPacketDto parseUpBytes(PackageServiceDto packageServiceDto){
		logger.info("pc system:"+DateUtil.format(new Date(), DateUtil.YMDHMS_PATTERN));
		byte[] bytes = packageServiceDto.getPackageBytes();
		byte seq = bytes[8];
		byte cmd = bytes[9];
		byte[] params =  new byte[bytes.length-10];
		System.arraycopy(bytes, 10, params, 0, bytes.length-10);
		String param = ByteArrayToNumber.bytesToString(params);
		LedPCPacketDto mcPacketDto = buildMCPacketDto(packageServiceDto.getHeader0(),
				packageServiceDto.getHeader1(),packageServiceDto.getBusiType(), packageServiceDto.getImei(), seq,cmd,param);
		LedPCManage pcManage = new LedPCManage();
		pcManage.sendMsg(mcPacketDto);
		return mcPacketDto;
	}

	@Override
	public LedPCPacketDto parseDownBytes(PackageServiceDto packageServiceDto) {
		byte[] bytes = packageServiceDto.getPackageBytes();
		byte seq = bytes[8];
		byte cmd = bytes[9];
		byte[] params =  new byte[bytes.length-10];
		System.arraycopy(bytes, 10, params, 0, bytes.length-10);
		String param = ByteArrayToNumber.bytesToString(params);
		LedPCPacketDto mcPacketDto = buildMCPacketDto(packageServiceDto.getHeader0(),
				packageServiceDto.getHeader1(),packageServiceDto.getBusiType(), packageServiceDto.getImei(),seq,cmd,param);
		return mcPacketDto;
	}

	private LedPCPacketDto buildMCPacketDto(char header0, char header1, String busiType, int imei,
											byte seq, byte cmd, String param){
		LedPCPacketDto mcPacketDto = new LedPCPacketDto();
		mcPacketDto.setHeader0(header0);
		mcPacketDto.setHeader1(header1);
		mcPacketDto.setImei(imei);
		mcPacketDto.setSeq(seq);
		mcPacketDto.setCmd(cmd);
		mcPacketDto.setParam(param);
		mcPacketDto.setBusiType(busiType);
		logger.info("LedPCPacketDto["+mcPacketDto+"]");
		return mcPacketDto;
	}
}

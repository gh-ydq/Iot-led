package com.minxing.socket.service.bike;

import com.minxing.socket.client.SendDownPackage;
import com.minxing.socket.dto.PackageServiceDto;
import com.minxing.socket.http.bike.PCManage;
import com.minxing.socket.util.ByteArrayToNumber;
import com.minxing.socket.util.SocketChannelMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minxing.socket.dto.gprs.pc.PCPacketDto;
import com.minxing.socket.service.impl.ParsePackageServiceImpl;

import io.netty.channel.ChannelHandlerContext;

public class ParsePCPackageService extends ParsePackageServiceImpl{
	private Logger logger = LoggerFactory.getLogger(ParsePCPackageService.class);
	@Override
	public  PCPacketDto parseUpBytes(PackageServiceDto packageServiceDto){
		byte[] bytes = packageServiceDto.getPackageBytes();
		byte seq = bytes[6];
		byte cmd = bytes[7];
		byte[] params =  new byte[bytes.length-8];
		System.arraycopy(bytes, 8, params, 0, bytes.length-8);
		String param = ByteArrayToNumber.bytesToString(params);
		PCPacketDto pcPacketDto = buildPCPacketDto(bytes.length,packageServiceDto.getHeader0(), packageServiceDto.getHeader1()
				, packageServiceDto.getImei(),seq,cmd,param);
		PCManage pcManage = new PCManage();
//		pcManage.saveUpPCInfo(pcPacketDto);
//		pcManage.sendMsg(pcPacketDto);
		return pcPacketDto;
	}
	@Override
	public PCPacketDto parseDownBytes(PackageServiceDto packageServiceDto) {
		byte[] bytes = packageServiceDto.getPackageBytes();
		byte seq = bytes[6];
		byte cmd = bytes[7];
		byte[] params =  new byte[bytes.length-8];
		System.arraycopy(bytes, 8, params, 0, bytes.length-8);
		String param = ByteArrayToNumber.bytesToString(params);
		PCPacketDto pcPacketDto = buildPCPacketDto(bytes.length,packageServiceDto.getHeader0(), packageServiceDto.getHeader1()
				, packageServiceDto.getImei(),seq,cmd,param);
		PCManage pcManage = new PCManage();
		pcManage.saveDownPCInfo(pcPacketDto);
		return pcPacketDto;
	}
	
	private PCPacketDto buildPCPacketDto(int bytesLength,char header0,char header1,int imei,
			byte seq,byte cmd,String param){
		PCPacketDto pcPacketDto = new PCPacketDto();
		pcPacketDto.setLength(bytesLength);
		pcPacketDto.setHeader0(header0);
		pcPacketDto.setHeader1(header1);
		pcPacketDto.setImei(imei);
		pcPacketDto.setSeq(seq);
		pcPacketDto.setCmd(cmd);
		pcPacketDto.setParam(param);
		logger.info("pcPacketDto["+pcPacketDto.toString()+"]");
		return pcPacketDto;
	}
	
	public void sendDownCmdPackage(byte[] bytes,String imeiKey){
		if(bytes.length < 9){
			String[] arg = new String[2];
			arg[0] = imeiKey;
			arg[1] = String.valueOf(bytes);
			logger.info("下行命令数据接口格式错误 bytes length={},imeiKey={},byte={}",bytes.length,arg);
			return;
		}
		ChannelHandlerContext imeiCtx = SocketChannelMap.upConcurrentMap.get(imeiKey);
		bytes[1] = 'C';
		SendDownPackage.sendDownCMD(bytes, imeiCtx);
	}
}

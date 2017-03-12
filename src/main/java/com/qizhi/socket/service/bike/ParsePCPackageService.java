package com.qizhi.socket.service.bike;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qizhi.socket.client.SendDownPackage;
import com.qizhi.socket.dto.gprs.pc.PCPacketDto;
import com.qizhi.socket.http.bike.PCManage;
import com.qizhi.socket.service.impl.ParsePackageServiceImpl;
import com.qizhi.socket.util.ByteArrayToNumber;
import com.qizhi.socket.util.SocketChannelMap;

import io.netty.channel.ChannelHandlerContext;

public class ParsePCPackageService extends ParsePackageServiceImpl{
	private Logger logger = LoggerFactory.getLogger(ParsePCPackageService.class);
	@Override
	public  PCPacketDto parseUpBytes(byte[] bytes,char header0,char header1,int imei){
		byte seq = bytes[6];
		byte cmd = bytes[7];
		byte[] params =  new byte[bytes.length-8];
		System.arraycopy(bytes, 8, params, 0, bytes.length-8);
		String param = ByteArrayToNumber.bytesToString(params);
		PCPacketDto pcPacketDto = buildPCPacketDto(bytes.length,header0, header1, imei,seq,cmd,param);
		PCManage pcManage = new PCManage();
//		pcManage.saveUpPCInfo(pcPacketDto);
//		pcManage.sendMsg(pcPacketDto);
		return pcPacketDto;
	}
	@Override
	public PCPacketDto parseDownBytes(byte[] bytes,char header0,char header1,int imei) {
		byte seq = bytes[6];
		byte cmd = bytes[7];
		byte[] params =  new byte[bytes.length-8];
		System.arraycopy(bytes, 8, params, 0, bytes.length-8);
		String param = ByteArrayToNumber.bytesToString(params);
		PCPacketDto pcPacketDto = buildPCPacketDto(bytes.length,header0, header1, imei,seq,cmd,param);
		PCManage pcManage = new PCManage();
		pcManage.saveDownPCInfo(pcPacketDto);
		return pcPacketDto;
	}
	
	private PCPacketDto buildPCPacketDto(int bytesLength,char header0,char header1,int imei,
			byte seq,byte cmd,String param){
		PCPacketDto pcPacketDto = new PCPacketDto();
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

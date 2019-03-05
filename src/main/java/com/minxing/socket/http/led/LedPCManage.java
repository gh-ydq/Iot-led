package com.minxing.socket.http.led;

import com.minxing.socket.constants.BikeStatusEnum;
import com.minxing.socket.dto.gprs.pc.PCPacketDto;
import com.minxing.socket.dto.http.req.gprs.PCReqDto;
import com.minxing.socket.dto.led.LedPCPacketDto;
import com.minxing.socket.exception.IotServiceBizException;
import com.minxing.socket.exception.IotServiceExceptionEnum;
import com.minxing.socket.util.DateUtil;
import com.minxing.socket.util.HttpClient;
import com.minxing.socket.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LedPCManage {
	private Logger logger = LoggerFactory.getLogger(LedPCManage.class);
    private static final String url = "http://api.qdigo.net/v1.0/bikeProtocol/command";
//	private static final String url = "http://192.168.0.101/v1.0/bikeProtocol/command";
   
   public void sendMsg(LedPCPacketDto pcPacketDto){
	   try {
		   PCReqDto pcReqDto = buildPCReqDto(pcPacketDto);
		   HttpClient.sendMsg(url, pcReqDto);
		} catch (Exception e) {
			logger.error("发送上行PC包http请求异常 header0:"+pcPacketDto.getHeader0()+",header1:"+pcPacketDto.getHeader1()+",imei:"+pcPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SEND_UP_PC_HTTP_ERROR.getCode(),IotServiceExceptionEnum.SEND_UP_PC_HTTP_ERROR.getMsg());
		}
   }
   public PCReqDto buildPCReqDto(LedPCPacketDto pcPacketDto){
	   PCReqDto pcReqDto = new PCReqDto();
	   pcReqDto.setPcImei(pcPacketDto.getImei());
	   pcReqDto.setPcCmd(pcPacketDto.getCmd());
	   pcReqDto.setPcSequence(pcPacketDto.getSeq());
	   pcReqDto.setPcParam(pcPacketDto.getParam());
	   return pcReqDto;
   }
}

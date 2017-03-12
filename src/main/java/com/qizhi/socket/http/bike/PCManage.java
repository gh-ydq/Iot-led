package com.qizhi.socket.http.bike;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qizhi.socket.constants.BikeStatusEnum;
import com.qizhi.socket.dto.gprs.pc.PCPacketDto;
import com.qizhi.socket.dto.http.req.gprs.PCReqDto;
import com.qizhi.socket.exception.IotServiceBizException;
import com.qizhi.socket.exception.IotServiceExceptionEnum;
import com.qizhi.socket.util.DateUtil;
import com.qizhi.socket.util.HttpClient;
import com.qizhi.socket.util.RedisUtil;

public class PCManage {
	private Logger logger = LoggerFactory.getLogger(PCManage.class);
    private static final String url = "http://api.qdigo.net/v1.0/bikeProtocol/command";
//	private static final String url = "http://192.168.0.101/v1.0/bikeProtocol/command";
   
   public void sendMsg(PCPacketDto pcPacketDto){
	   try {
		   PCReqDto pcReqDto = buildPGReqDto(pcPacketDto);
		   HttpClient.sendMsg(url, pcReqDto);
		} catch (Exception e) {
			logger.error("发送上行PC包http请求异常 header0:"+pcPacketDto.getHeader0()+",header1:"+pcPacketDto.getHeader1()+",imei:"+pcPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SEND_UP_PC_HTTP_ERROR.getCode(),IotServiceExceptionEnum.SEND_UP_PC_HTTP_ERROR.getMsg());
		}
   }
   public void saveUpPCInfo(PCPacketDto pcPacketDto){
	   try {
		   RedisUtil redisUtil = new RedisUtil();
		   String imei = String.valueOf(pcPacketDto.getImei());
		   String model = imei.substring(imei.length()-1);
		   String monitorAllBikeKey = BikeStatusEnum.MONITOR_ALLBIKE_STATUS.getBikeStatus()+model;
		   String motitorValue = BikeStatusEnum.MONITOR_BIKE_STATUS.getBikeStatus()+imei;
		   redisUtil.hset(monitorAllBikeKey, imei, motitorValue);
		   Map<String, String> bikePCMaP = getBikeUpStatus(pcPacketDto);
		   redisUtil.hmSet(motitorValue, bikePCMaP);
	   } catch (Exception e) {
		   logger.error("保存上行pc包到缓存异常异常 header0:"+pcPacketDto.getHeader0()+",header1:"+pcPacketDto.getHeader1()+",imei:"+pcPacketDto.getImei(),e);
		   throw new IotServiceBizException(IotServiceExceptionEnum.SEND_UP_PC_HTTP_ERROR.getCode(),IotServiceExceptionEnum.SEND_UP_PC_HTTP_ERROR.getMsg());
		}
	   
   }
   
   private Map<String,String> getBikeUpStatus(PCPacketDto pcPacketDto){
	   Map<String, String> bikePGMap = new HashMap<String, String>();
	   String upPCType = "CMD_"+pcPacketDto.getCmd()+" _SEQ_"+pcPacketDto.getSeq()+"_para_"+pcPacketDto.getParam();
	   bikePGMap.put(BikeStatusEnum.IMEI.getBikeStatus(), pcPacketDto.getImei()+"");
	   bikePGMap.put(BikeStatusEnum.UP_PC_LASTTIME.getBikeStatus(), DateUtil.format(new Date(), DateUtil.DEFAULT_PATTERN));
	   bikePGMap.put(BikeStatusEnum.UP_PC_TYPE.getBikeStatus(), upPCType);
	   return bikePGMap;
   }
   
   public void saveDownPCInfo(PCPacketDto pcPacketDto){
	   try {
		   RedisUtil redisUtil = new RedisUtil();
		   String imei = String.valueOf(pcPacketDto.getImei());
		   String model = imei.substring(imei.length()-1);
		   String monitorAllBikeKey = BikeStatusEnum.MONITOR_ALLBIKE_STATUS.getBikeStatus()+model;
		   String motitorValue = BikeStatusEnum.MONITOR_BIKE_STATUS.getBikeStatus()+imei;
		   redisUtil.hset(monitorAllBikeKey, imei, motitorValue);
		   Map<String, String> bikePCMaP = getBikeDownStatus(pcPacketDto);
		   redisUtil.hmSet(motitorValue, bikePCMaP);
		} catch (Exception e) {
			logger.error("保存下行pc包到缓存异常异常 header0:"+pcPacketDto.getHeader0()+",header1:"+pcPacketDto.getHeader1()+",imei:"+pcPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SAVE_DOWN_PC_REDIS_ERROR.getCode(),IotServiceExceptionEnum.SAVE_DOWN_PC_REDIS_ERROR.getMsg());

		}
	  
   }
   
   private Map<String,String> getBikeDownStatus(PCPacketDto pcPacketDto){
	   Map<String, String> bikePGMap = new HashMap<String, String>();
	   String upPCType = "CMD_"+pcPacketDto.getCmd()+" _SEQ_"+pcPacketDto.getSeq()+"_para_"+pcPacketDto.getParam();
	   bikePGMap.put(BikeStatusEnum.IMEI.getBikeStatus(), pcPacketDto.getImei()+"");
	   bikePGMap.put(BikeStatusEnum.DOWN_PC_LASTTIME.getBikeStatus(), DateUtil.format(new Date(), DateUtil.DEFAULT_PATTERN));
	   bikePGMap.put(BikeStatusEnum.DOWN_PC_TYPE.getBikeStatus(), upPCType);
	   return bikePGMap;
   }
   
   public PCReqDto buildPGReqDto(PCPacketDto pcPacketDto){
	   PCReqDto pcReqDto = new PCReqDto();
	   pcReqDto.setPcImei(pcPacketDto.getImei());
	   pcReqDto.setPcCmd(pcPacketDto.getCmd());
	   pcReqDto.setPcSequence(pcPacketDto.getSeq());
	   pcReqDto.setPcParam(pcPacketDto.getParam());
	   return pcReqDto;
   }
}

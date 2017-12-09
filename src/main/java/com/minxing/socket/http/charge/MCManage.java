package com.minxing.socket.http.charge;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.minxing.socket.constants.BikeStatusEnum;
import com.minxing.socket.constants.ChargeStatusEnum;
import com.minxing.socket.exception.IotServiceExceptionEnum;
import com.minxing.socket.util.HttpClient;
import com.minxing.socket.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minxing.socket.dto.baseStation.mc.MCPacketDto;
import com.minxing.socket.dto.http.req.charge.MCReqDto;
import com.minxing.socket.exception.IotServiceBizException;
import com.minxing.socket.util.DateUtil;

public class MCManage {
    private Logger logger = LoggerFactory.getLogger(MCManage.class);
    private static final String url = "http://api.qdigo.net/v1.0/chargerProtocol/MC";
//	private static final String url = "http://192.168.0.101/v1.0/chargerProtocol/MC";
   
   public void sendMsg(MCPacketDto mcPacketDto){
	   try {
		   MCReqDto mcReqDto = buildPGReqDto(mcPacketDto);
		   HttpClient.sendMsg(url, mcReqDto);
		} catch (Exception e) {
			logger.error("发送上行MC包http请求异常 header0:"+mcPacketDto.getHeader0()+",header1:"+mcPacketDto.getHeader1()+",imei:"+mcPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SEND_UP_MD_HTTP_ERROR.getCode(),IotServiceExceptionEnum.SEND_UP_MD_HTTP_ERROR.getMsg());
		}
   }
   
   public void saveUpMCInfo(MCPacketDto mcPacketDto){
	   try {
		   RedisUtil redisUtil = new RedisUtil();
		   String imei = String.valueOf(mcPacketDto.getImei());
		   String model = imei.substring(imei.length()-1);
		   String monitorAllBikeKey = ChargeStatusEnum.MONITOR_ALLCHARGERPILE_STATUS.getChargeStatus()+model;
		   String motitorValue = ChargeStatusEnum.MONITOR_CHARGERPILE_STATUS.getChargeStatus()+imei;
		   redisUtil.hset(monitorAllBikeKey, imei, motitorValue);
		   Map<String, String> bikePGMaP = getChargeUpStatus(mcPacketDto);
		   redisUtil.hmSet(motitorValue, bikePGMaP);
		} catch (Exception e) {
			logger.error("保存上行MC包到缓存异常 header0:"+mcPacketDto.getHeader0()+",header1:"+mcPacketDto.getHeader1()+",imei:"+mcPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SAVE_UP_MC_REDIS_ERROR.getCode(),IotServiceExceptionEnum.SAVE_UP_MC_REDIS_ERROR.getMsg());
		}
   }
   
   public void saveDownMCInfo(MCPacketDto mcPacketDto){
	   try {
		   RedisUtil redisUtil = new RedisUtil();
		   String imei = String.valueOf(mcPacketDto.getImei());
		   String model = imei.substring(imei.length()-1);
		   String monitorAllBikeKey = ChargeStatusEnum.MONITOR_ALLCHARGERPILE_STATUS.getChargeStatus()+model;
		   String motitorValue = ChargeStatusEnum.MONITOR_CHARGERPILE_STATUS.getChargeStatus()+imei;
		   redisUtil.hset(monitorAllBikeKey, imei, motitorValue);
		   Map<String, String> bikePGMaP = getChargeDownStatus(mcPacketDto);
		   redisUtil.hmSet(motitorValue, bikePGMaP);
		} catch (Exception e) {
			logger.error("保存下行MC包到缓存异常 header0:"+mcPacketDto.getHeader0()+",header1:"+mcPacketDto.getHeader1()+",imei:"+mcPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SAVE_DOWN_MC_REDIS_ERROR.getCode(),IotServiceExceptionEnum.SAVE_DOWN_MC_REDIS_ERROR.getMsg());
		}
   }
   
   public MCReqDto buildPGReqDto(MCPacketDto mcPacketDto){
	   MCReqDto mcReqDto = new MCReqDto();
	   mcReqDto.setMcImei(mcPacketDto.getImei());
	   mcReqDto.setMcCmd(mcPacketDto.getCmd());
	   mcReqDto.setMcSequence(mcPacketDto.getSeq());
	   mcReqDto.setMcParam(mcPacketDto.getParam());
	   return mcReqDto;
   }
   
   
   
   private Map<String,String> getChargeDownStatus(MCPacketDto pcPacketDto){
	   Map<String, String> bikePGMap = new HashMap<String, String>();
	   String upPCType = "CMD_"+pcPacketDto.getCmd()+" _SEQ_"+pcPacketDto.getSeq()+"_para_"+pcPacketDto.getParam();
	   bikePGMap.put(BikeStatusEnum.IMEI.getBikeStatus(), pcPacketDto.getImei()+"");
	   bikePGMap.put(ChargeStatusEnum.DOWN_MC_LASTTIME.getChargeStatus(), DateUtil.format(new Date(), DateUtil.DEFAULT_PATTERN));
	   bikePGMap.put(ChargeStatusEnum.DOWN_MC_TYPE.getChargeStatus(), upPCType);
	   return bikePGMap;
   }
   
   private Map<String,String> getChargeUpStatus(MCPacketDto pcPacketDto){
	   Map<String, String> bikePGMap = new HashMap<String, String>();
	   String upPCType = "CMD_"+pcPacketDto.getCmd()+" _SEQ_"+pcPacketDto.getSeq()+"_para_"+pcPacketDto.getParam();
	   bikePGMap.put(BikeStatusEnum.IMEI.getBikeStatus(), pcPacketDto.getImei()+"");
	   bikePGMap.put(ChargeStatusEnum.UP_MC_LASTTIME.getChargeStatus(), DateUtil.format(new Date(), DateUtil.DEFAULT_PATTERN));
	   bikePGMap.put(ChargeStatusEnum.UP_MC_TYPE.getChargeStatus(), upPCType);
	   return bikePGMap;
   }
   
}

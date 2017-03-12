package com.qizhi.socket.http.charge;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qizhi.socket.constants.ChargeStatusEnum;
import com.qizhi.socket.dto.baseStation.ml.MLPacketDto;
import com.qizhi.socket.dto.http.req.charge.MLReqDto;
import com.qizhi.socket.exception.IotServiceBizException;
import com.qizhi.socket.exception.IotServiceExceptionEnum;
import com.qizhi.socket.util.DateUtil;
import com.qizhi.socket.util.HttpClient;
import com.qizhi.socket.util.RedisUtil;

public class MLManage {
	private Logger logger = LoggerFactory.getLogger(MLManage.class);
	
	private static final String url = "http://api.qdigo.net/v1.0/chargerProtocol/ML";
//	private static final String url = "http://192.168.0.101/v1.0/chargerProtocol/ML";
   
   public void sendMsg(MLPacketDto mlPacketDto){
	   try {
		   MLReqDto pcReqDto = buildMDReqDto(mlPacketDto);
		   HttpClient.sendMsg(url, pcReqDto);
		} catch (Exception e) {
			logger.error("发送上行ML包http请求异常 header0:"+mlPacketDto.getHeader0()+",header1:"+mlPacketDto.getHeader1()+",imei:"+mlPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SEND_UP_ML_HTTP_ERROR.getCode(),IotServiceExceptionEnum.SEND_UP_ML_HTTP_ERROR.getMsg());
		}
	  
   }
   
   public void saveMLInfo(MLPacketDto mlPacketDto){
	   try {
		   RedisUtil redisUtil = new RedisUtil();
		   String imei = String.valueOf(mlPacketDto.getImei());
		   String model = imei.substring(imei.length()-1);
		   String monitorAllBikeKey = ChargeStatusEnum.MONITOR_ALLCHARGERPILE_STATUS.getChargeStatus()+model;
		   String motitorValue = ChargeStatusEnum.MONITOR_CHARGERPILE_STATUS.getChargeStatus()+imei;
		   redisUtil.hset(monitorAllBikeKey, imei, motitorValue);
		   Map<String, String> bikePGMaP = getChargeStatus(mlPacketDto);
		   redisUtil.hmSet(motitorValue, bikePGMaP);
		} catch (Exception e) {
			logger.error("保存上行ML包到缓存异常 header0:"+mlPacketDto.getHeader0()+",header1:"+mlPacketDto.getHeader1()+",imei:"+mlPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SAVE_UP_ML_REDIS_ERROR.getCode(),IotServiceExceptionEnum.SAVE_UP_ML_REDIS_ERROR.getMsg());
		}
   }
   
   private Map<String,String> getChargeStatus(MLPacketDto mlPacketDto){
	   Map<String, String> chargeMDMap = new HashMap<String, String>();
	   chargeMDMap.put(ChargeStatusEnum.IMEI.getChargeStatus(), mlPacketDto.getImei()+"");
	   chargeMDMap.put(ChargeStatusEnum.ML_LASTTIME.getChargeStatus(), DateUtil.format(new Date(), DateUtil.DEFAULT_PATTERN));
	   return chargeMDMap;
   }
   
   public MLReqDto buildMDReqDto(MLPacketDto mlPacketDto){
	   MLReqDto mlReqDto = new MLReqDto();
	   mlReqDto.setMlImei(mlPacketDto.getImei());
	   mlReqDto.setMlCellid(mlPacketDto.getCellid());
	   mlReqDto.setMlLAC(mlPacketDto.getLac());
	   mlReqDto.setMlSingal(mlPacketDto.getSignal());
	   mlReqDto.setMlTemperature(mlPacketDto.getTemperature());
	   mlReqDto.setMlImsi(mlPacketDto.getImsi());
	   return mlReqDto;
   }
}

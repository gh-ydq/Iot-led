package com.minxing.socket.http.led;

import com.minxing.socket.constants.BikeStatusEnum;
import com.minxing.socket.dto.gprs.px.PXPacketDto;
import com.minxing.socket.exception.IotServiceBizException;
import com.minxing.socket.exception.IotServiceExceptionEnum;
import com.minxing.socket.util.DateUtil;
import com.minxing.socket.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LedPXManage {
   private Logger logger = LoggerFactory.getLogger(LedPXManage.class);
   private static final String url = "http://api.qdigo.net/v1.0/bikeProtocol/GPS";
//	private static final String url = "http://192.168.0.101/v1.0/bikeProtocol/GPS";
   
   public void sendMsg(PXPacketDto pgPacketDto){
	   try {
//		   PGReqDto pgReqDto = buildPGReqDto(pgPacketDto);
//		   HttpClient.sendMsg(url, pgReqDto);
		} catch (Exception e) {
			logger.error("发送上行PG包http请求异常 header0:"+pgPacketDto.getHeader0()+",header1:"+pgPacketDto.getHeader1()+",imei:"+pgPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SEND_UP_PG_HTTP_ERROR.getCode(),IotServiceExceptionEnum.SEND_UP_PG_HTTP_ERROR.getMsg());
		}
   }
   
   public void savePXInfo(PXPacketDto pgPacketDto){
	   try {
		   RedisUtil redisUtil = new RedisUtil();
		   String imei = String.valueOf(pgPacketDto.getImei());
		   String model = imei.substring(imei.length()-1);
		   String monitorAllBikeKey = BikeStatusEnum.MONITOR_ALLBIKE_STATUS.getBikeStatus()+model;
		   String motitorValue = BikeStatusEnum.MONITOR_BIKE_STATUS.getBikeStatus()+imei;
		   redisUtil.hset(monitorAllBikeKey, imei, motitorValue);
		   Map<String, String> bikePGMaP = getBikeStatus(pgPacketDto);
		   redisUtil.hmSet(motitorValue, bikePGMaP);
		} catch (Exception e) {
			logger.error("保存上行PG包到缓存异常异常 header0:"+pgPacketDto.getHeader0()+",header1:"+pgPacketDto.getHeader1()+",imei:"+pgPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SAVE_UP_PG_REDIS_ERROR.getCode(),IotServiceExceptionEnum.SAVE_UP_PG_REDIS_ERROR.getMsg());
		}
   }
   
   private Map<String,String> getBikeStatus(PXPacketDto pXPacketDto){
	   Map<String, String> bikePGMap = new HashMap<String, String>();
	   bikePGMap.put(BikeStatusEnum.IMEI.getBikeStatus(), pXPacketDto.getImei()+"");
	   bikePGMap.put(BikeStatusEnum.PG_LASTTIME.getBikeStatus(), DateUtil.format(new Date(), DateUtil.DEFAULT_PATTERN));
	   return bikePGMap;
   }
   
}

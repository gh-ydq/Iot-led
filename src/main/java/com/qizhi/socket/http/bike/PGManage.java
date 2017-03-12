package com.qizhi.socket.http.bike;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qizhi.socket.constants.BikeStatusEnum;
import com.qizhi.socket.dto.gprs.pg.PGPacketDto;
import com.qizhi.socket.dto.http.req.gprs.PGReqDto;
import com.qizhi.socket.exception.IotServiceBizException;
import com.qizhi.socket.exception.IotServiceExceptionEnum;
import com.qizhi.socket.util.DateUtil;
import com.qizhi.socket.util.HttpClient;
import com.qizhi.socket.util.RedisUtil;

public class PGManage {
   private Logger logger = LoggerFactory.getLogger(PGManage.class);
   private static final String url = "http://api.qdigo.net/v1.0/bikeProtocol/GPS";
//	private static final String url = "http://192.168.0.101/v1.0/bikeProtocol/GPS";
   
   public void sendMsg(PGPacketDto pgPacketDto){
	   try {
		   PGReqDto pgReqDto = buildPGReqDto(pgPacketDto);
		   HttpClient.sendMsg(url, pgReqDto);
		} catch (Exception e) {
			logger.error("发送上行PG包http请求异常 header0:"+pgPacketDto.getHeader0()+",header1:"+pgPacketDto.getHeader1()+",imei:"+pgPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SEND_UP_PG_HTTP_ERROR.getCode(),IotServiceExceptionEnum.SEND_UP_PG_HTTP_ERROR.getMsg());
		}
   }
   
   public void savePGInfo(PGPacketDto pgPacketDto){
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
   
   private Map<String,String> getBikeStatus(PGPacketDto pgPacketDto){
	   Map<String, String> bikePGMap = new HashMap<String, String>();
	   bikePGMap.put(BikeStatusEnum.IMEI.getBikeStatus(), pgPacketDto.getImei()+"");
	   bikePGMap.put(BikeStatusEnum.PG_LASTTIME.getBikeStatus(), DateUtil.format(new Date(), DateUtil.DEFAULT_PATTERN));
	   return bikePGMap;
   }
   
   private PGReqDto buildPGReqDto(PGPacketDto pgPacketDto){
	   PGReqDto pgReqDto = new PGReqDto();
	   pgReqDto.setPgImei(pgPacketDto.getImei());
	   pgReqDto.setPgLongitude(pgPacketDto.getLng());
	   pgReqDto.setPgLatitude(pgPacketDto.getLat());
	   pgReqDto.setPgHight(pgPacketDto.getHight());
	   pgReqDto.setPgSpeed(pgPacketDto.getSpeed());
	   pgReqDto.setPgStar(pgPacketDto.getStar());
	   
	   return pgReqDto;
   }
}

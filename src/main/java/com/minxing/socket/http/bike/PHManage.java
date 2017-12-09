package com.minxing.socket.http.bike;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.minxing.socket.constants.BikeStatusEnum;
import com.minxing.socket.dto.gprs.ph.PHPacketDto;
import com.minxing.socket.dto.http.req.gprs.PHReqDto;
import com.minxing.socket.exception.IotServiceExceptionEnum;
import com.minxing.socket.util.DateUtil;
import com.minxing.socket.util.HttpClient;
import com.minxing.socket.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minxing.socket.exception.IotServiceBizException;

public class PHManage {
	private Logger logger = LoggerFactory.getLogger(PHManage.class);
    private static final String url = "http://api.qdigo.net/v1.0/bikeProtocol/heart";
//  private static final String url = "http://192.168.0.101/v1.0/bikeProtocol/heart";
   
   public void sendMsg(PHPacketDto phPacketDto){
	   try {
		   PHReqDto phReqDto = buildPHReqDto(phPacketDto);
		   HttpClient.sendMsg(url, phReqDto);
		} catch (Exception e) {
			logger.error("发送上行PH包http请求异常 header0:"+phPacketDto.getHeader0()+",header1:"+phPacketDto.getHeader1()+",imei:"+phPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SEND_UP_PH_HTTP_ERROR.getCode(),IotServiceExceptionEnum.SEND_UP_PH_HTTP_ERROR.getMsg());
		}
	  
   }
   
   public void savePHInfo(PHPacketDto phPacketDto){
	   try {
		   RedisUtil redisUtil = new RedisUtil();
		   String imei = String.valueOf(phPacketDto.getImei());
		   String model = imei.substring(imei.length()-1);
		   String monitorAllBikeKey = BikeStatusEnum.MONITOR_ALLBIKE_STATUS.getBikeStatus()+model;
		   String motitorValue = BikeStatusEnum.MONITOR_BIKE_STATUS.getBikeStatus()+imei;
		   redisUtil.hset(monitorAllBikeKey, imei, motitorValue);
		   Map<String, String> bikePHMaP = getBikeStatus(phPacketDto);
		   redisUtil.hmSet(motitorValue, bikePHMaP);
		} catch (Exception e) {
			logger.error("保存上行PH包到缓存异常异常 header0:"+phPacketDto.getHeader0()+",header1:"+phPacketDto.getHeader1()+",imei:"+phPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SAVE_UP_PH_REDIS_ERROR.getCode(),IotServiceExceptionEnum.SAVE_UP_PH_REDIS_ERROR.getMsg());
		}
	   
   }
   
   private Map<String,String> getBikeStatus(PHPacketDto phPacketDto){
	   Map<String, String> bikePGMap = new HashMap<String, String>();
	   bikePGMap.put(BikeStatusEnum.IMEI.getBikeStatus(), phPacketDto.getImei()+"");
	   bikePGMap.put(BikeStatusEnum.PH_LASTTIME.getBikeStatus(), DateUtil.format(new Date(), DateUtil.DEFAULT_PATTERN));
	   return bikePGMap;
   }
   
   private PHReqDto buildPHReqDto(PHPacketDto phPacketDto){
	   PHReqDto phReqDto = new PHReqDto();
	   phReqDto.setPhImei(phPacketDto.getImei());
	   phReqDto.setPhImsi(phPacketDto.getImsi());
	   phReqDto.setPhSequence(phPacketDto.getSeq());
	   phReqDto.setPhPowerVoltage(phPacketDto.getPowerVoltage());
	   phReqDto.setPhSentity(phPacketDto.getSensity());
	   phReqDto.setPhStar(phPacketDto.getStar());
	   return phReqDto;
   }
}

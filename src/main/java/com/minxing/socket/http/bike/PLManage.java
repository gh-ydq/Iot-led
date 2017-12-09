package com.minxing.socket.http.bike;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.minxing.socket.constants.BikeStatusEnum;
import com.minxing.socket.dto.http.req.gprs.PLReqDto;
import com.minxing.socket.exception.IotServiceExceptionEnum;
import com.minxing.socket.util.HttpClient;
import com.minxing.socket.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minxing.socket.dto.gprs.pl.PLPacketDto;
import com.minxing.socket.exception.IotServiceBizException;
import com.minxing.socket.util.DateUtil;

public class PLManage {
	private Logger logger = LoggerFactory.getLogger(PLManage.class);
    private static final String url = "http://api.qdigo.net/v1.0/bikeProtocol/GPSLocation";
//	private static final String url = "http://192.168.0.101/v1.0/bikeProtocol/GPSLocation";
   
   public void sendMsg(PLPacketDto plPacketDto){
	   try {
		   PLReqDto plReqDto = buildPLReqDto(plPacketDto);
		   HttpClient.sendMsg(url, plReqDto);
		} catch (Exception e) {
			logger.error("发送上行PL包http请求异常 header0:"+plPacketDto.getHeader0()+",header1:"+plPacketDto.getHeader1()+",imei:"+plPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SEND_UP_PL_HTTP_ERROR.getCode(),IotServiceExceptionEnum.SEND_UP_PL_HTTP_ERROR.getMsg());
		}
	   
   }
   public void savePLInfo(PLPacketDto plPacketDto){
	   try {
		   RedisUtil redisUtil = new RedisUtil();
		   String imei = String.valueOf(plPacketDto.getImei());
		   String model = imei.substring(imei.length()-1);
		   String monitorAllBikeKey = BikeStatusEnum.MONITOR_ALLBIKE_STATUS.getBikeStatus()+model;
		   String motitorValue = BikeStatusEnum.MONITOR_BIKE_STATUS.getBikeStatus()+imei;
		   redisUtil.hset(monitorAllBikeKey, imei, motitorValue);
		   Map<String, String> bikePLMaP = getBikeStatus(plPacketDto);
		   redisUtil.hmSet(motitorValue, bikePLMaP);
		} catch (Exception e) {
			logger.error("保存上行PL包到缓存异常异常 header0:"+plPacketDto.getHeader0()+",header1:"+plPacketDto.getHeader1()+",imei:"+plPacketDto.getImei(),e);
			throw new IotServiceBizException(IotServiceExceptionEnum.SAVE_UP_PL_REDIS_ERROR.getCode(),IotServiceExceptionEnum.SAVE_UP_PL_REDIS_ERROR.getMsg());
		}
   }
   
   private Map<String,String> getBikeStatus(PLPacketDto plPacketDto){
	   Map<String, String> bikePGMap = new HashMap<String, String>();
	   bikePGMap.put(BikeStatusEnum.IMEI.getBikeStatus(), plPacketDto.getImei()+"");
	   bikePGMap.put(BikeStatusEnum.PL_LASTTIME.getBikeStatus(), DateUtil.format(new Date(), DateUtil.DEFAULT_PATTERN));
	   return bikePGMap;
   }
   
   public PLReqDto buildPLReqDto(PLPacketDto plPacketDto){
	   PLReqDto pgReqDto = new PLReqDto();
	   pgReqDto.setPlImei(plPacketDto.getImei());
	   pgReqDto.setPlLac(plPacketDto.getLac());
	   pgReqDto.setPlCellid(plPacketDto.getCellid());
	   pgReqDto.setPlSingal(plPacketDto.getSignal());
	   
	   
	   return pgReqDto;
   }
}

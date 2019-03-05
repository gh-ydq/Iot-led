package com.minxing.socket.http.led;

import com.minxing.socket.constants.BikeStatusEnum;
import com.minxing.socket.dto.gprs.ph.PHPacketDto;
import com.minxing.socket.dto.http.req.gprs.PHReqDto;
import com.minxing.socket.dto.led.LedPHPacketDto;
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

public class LedPHManage {
	private Logger logger = LoggerFactory.getLogger(LedPHManage.class);
//    private static final String url = "http://api.qdigo.net/v1.0/bikeProtocol/heart";
  private static final String url = "http://127.0.0.1:8090/lightUpcmd/save";
   
   public void sendMsg(LedPHPacketDto lePphPacketDto){
	   try {
//		   PHReqDto phReqDto = buildPHReqDto(phPacketDto);
		   HttpClient.sendMsg(url, lePphPacketDto);
		} catch (Exception e) {
			logger.error("发送上行PH包http请求异常 header0:"+lePphPacketDto.getHeader0()+",header1:"+lePphPacketDto.getHeader1()+",header2:"+lePphPacketDto.getBusiType()+",imei:"+lePphPacketDto.getImei(),e);
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
	   return phReqDto;
   }
}

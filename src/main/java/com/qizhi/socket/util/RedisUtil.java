package com.qizhi.socket.util;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisUtil {
	private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	private static JedisPool jedisPool;// 非切片连接池
	private static int defautlDb = 0;// 默认数据库
	private static final Object lockObj = new Object();
	// 192.168.47.128
	private static String REDIS_SERVER_HOST="127.0.0.1";
	private static int REDIS_SERVER_PORT=6379;
	private static int REDIS_SERVER_MAXWAIT=1000;
	private static int REDIS_SERVER_MAXTOTAL=2000;
	private static int REDIS_SERVER_MAXIDLE=20;
	private static boolean REDIS_SERVER_TESTONBORROW=true;
	private static int REDIS_SERVER_DEFAULTDB=1;
	private static String REDIS_SERVER_PASSWORD;

	/****
	 * 通过配置得到 Jedis
	 * 
	 * @return Jedis实例
	 */
	public static Jedis getConnection() {
		Jedis retJedis =null;
		try {
			if (jedisPool == null) {
				synchronized (lockObj) {
					if (jedisPool != null) {
						jedisPool.destroy();
					}
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxTotal(REDIS_SERVER_MAXTOTAL);
					config.setMaxIdle(REDIS_SERVER_MAXIDLE);
					config.setMaxWaitMillis(REDIS_SERVER_MAXWAIT);
					config.setTestOnBorrow(REDIS_SERVER_TESTONBORROW);
					defautlDb = (REDIS_SERVER_DEFAULTDB > 15 || REDIS_SERVER_DEFAULTDB < 0) ? 0
							: REDIS_SERVER_DEFAULTDB;
					String password = REDIS_SERVER_PASSWORD;
					if (StringUtils.isBlank(password)) {// 有设置密码
						jedisPool = new JedisPool(config, REDIS_SERVER_HOST, REDIS_SERVER_PORT);
					} else {
						jedisPool = new JedisPool(config, REDIS_SERVER_HOST, REDIS_SERVER_PORT, REDIS_SERVER_MAXIDLE,
								password);
					}
				}
			}
			 retJedis = jedisPool.getResource();
			retJedis.select(defautlDb);
		} catch (Exception e) {
			logger.error("获取redis连接池失败",e);
			throw new RuntimeException(e);
		}
		return retJedis;
	}

	/***
	 * 释放资源
	 *
	 * @param jedis
	 */
	public void returnResource(Jedis jedis) {
		try {
			if (jedis != null) {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error("jedis释放连接失败",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * hash 存储
	 * @param key
	 * @param field
	 * @param value
	 */
	public final void hset(String key, String field,String value) {
		Jedis jedis = null;
		try {
			jedis = getConnection();
			jedis.hset(key, field, value);
		} catch (Exception e) {
			logger.error("set设值失败", e);
			throw new RuntimeException(e);
		} finally {
			returnResource(jedis);
		}
	}
	
	
	public final void set(String key, String value, Integer expire) {
		Jedis jedis = null;
		try {
			jedis = getConnection();
			jedis.set(key, value);
			if (null != expire) {
				jedis.expire(key, expire);
			}
		} catch (Exception e) {
			logger.error("set设值失败", e);
			throw new RuntimeException(e);
		} finally {
			returnResource(jedis);
		}

	}
	public final void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getConnection();
			jedis.set(key, value);
		} catch (Exception e) {
			logger.error("set设值失败", e);
			throw new RuntimeException(e);
		} finally {
			returnResource(jedis);
		}

	}
	
	/**
	 * 存储map数据接口
	 * @param key
	 * @param mapValue
	 */
	public final void hmSet(String key, Map<String,String> mapValue) {
		Jedis jedis = null;
		try {
			jedis = getConnection();
			jedis.hmset(key, mapValue);
		} catch (Exception e) {
			logger.error("set设值失败", e);
			throw new RuntimeException(e);
		} finally {
			returnResource(jedis);
		}

	}

	public final String get(String key) {
		Jedis jedis = null;
		try {
			jedis = getConnection();
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
			logger.error("get取值失败", e);
			throw new RuntimeException(e);
		} finally {
			returnResource(jedis);
		}
	}
	
	
	public static void main(String[] args) {
		RedisUtil redisUtil = new RedisUtil();
		String allBikeKey = "Monitor_AllBike_Status_0";
		String imei = "12345678";
		String key = "Monitor_Bike_Status_"+imei;
		String allBikeValue = "{"+imei+":"+key+"}";
		System.out.println("allBikeValue"+allBikeValue);
//		redisUtil.hset(allBikeKey,imei, key);
		
		String imei2 = "123456789";
		String key2 = "Monitor_Bike_Status_"+imei2;
		String allBikeValue2 = "{"+imei2+":"+key2+"}";
		System.out.println("allBikeValue2"+allBikeValue2);
//		redisUtil.hset(allBikeKey,imei2,key2);
		
		
		
		////////////////////////
		String bikeKey = "Monitor_Bike_Status_"+imei;
		
		Map<String, String> dataMap = new HashMap<String, String>();
		String Bike_IMEI_HKey ="IMEI";
		String Bike_IMEI_Value=imei;
		

		String Bike_Lat_HKey ="Lat";
		String Bike_Lat_Value="231.3";
		
		dataMap.put(Bike_IMEI_HKey, Bike_IMEI_Value);
		dataMap.put(Bike_Lat_HKey, Bike_Lat_Value);
		System.out.println("bikeKey"+bikeKey);
		redisUtil.hmSet(bikeKey, dataMap);
		
		Map<String, String> dataMap2 = new HashMap<String, String>();
		String Bike_Lon_HKey ="Lon";
		String Bike_Lon_Value="231.5";
		dataMap2.put(Bike_Lon_HKey, Bike_Lon_Value);
		redisUtil.hmSet(bikeKey, dataMap2);
		
	}

}

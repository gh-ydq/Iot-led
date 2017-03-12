package com.qizhi.socket.service.api;

import com.qizhi.socket.dto.DatagramPacketBasicDto;

/**
 * 
 * 解析数据包
 * @author yudengqiu
 * @since 2016-11-12
 */
public interface ParsePackageService {
	// 解析上行数据包
	public DatagramPacketBasicDto parseUpBytes(byte[] bytes,char header0,char header1,int imei);
	//解析下行数据包
	public DatagramPacketBasicDto parseDownBytes(byte[] bytes,char header0,char header1,int imei);
}

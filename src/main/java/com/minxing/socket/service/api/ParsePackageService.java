package com.minxing.socket.service.api;

import com.minxing.socket.dto.DatagramPacketBasicDto;
import com.minxing.socket.dto.PackageServiceDto;

/**
 * 
 * 解析数据包
 * @author yudengqiu
 * @since 2016-11-12
 */
public interface ParsePackageService {
	// 解析上行数据包
	public DatagramPacketBasicDto parseUpBytes(PackageServiceDto packageServiceDto);
	//解析下行数据包
	public DatagramPacketBasicDto parseDownBytes(PackageServiceDto packageServiceDto);
}

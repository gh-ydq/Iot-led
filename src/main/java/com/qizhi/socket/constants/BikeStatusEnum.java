package com.qizhi.socket.constants;

/**
 * 监控车通信信息
 * @author yudengqiu
 *
 */
public enum BikeStatusEnum {
	IMEI("IMEI","设备唯一编号后8位"),
	PG_LASTTIME("PG_LastTime","设备唯一编号后8位"),
	PH_LASTTIME("PH_LastTime","设备唯一编号后8位"),
	PL_LASTTIME("PL_LastTime","设备唯一编号后8位"),
	UP_PC_LASTTIME("UP_PC_LastTime","设备唯一编号后8位"),
	UP_PC_TYPE("UP_PC_TYPE","设备唯一编号后8位"),
	DOWN_PC_LASTTIME("DOWN_PC_LastTime","设备唯一编号后8位"),
	DOWN_PC_TYPE("DOWN_PC_TYPE","设备唯一编号后8位"),
	
	MONITOR_ALLBIKE_STATUS("Monitor_AllBike_Status_","车列表标识"),
	
	MONITOR_BIKE_STATUS("Monitor_Bike_Status_","车明细标识"),
	;
	
	private String bikeStatus;
	private String dec;
	private BikeStatusEnum(String bikeStatus,String dec){
		this.bikeStatus = bikeStatus;
		this.dec = dec;
	}
	public String getBikeStatus() {
		return bikeStatus;
	}
	public void setBikeStatus(String bikeStatus) {
		this.bikeStatus = bikeStatus;
	}
	public String getDec() {
		return dec;
	}
	public void setDec(String dec) {
		this.dec = dec;
	}
	
	
}

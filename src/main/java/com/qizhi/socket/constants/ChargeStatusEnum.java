package com.qizhi.socket.constants;

/**
 * 监控车通信信息
 * @author yudengqiu
 *
 */
public enum ChargeStatusEnum {
	IMEI("IMEI","充电桩唯一编号后8位"),
	MD_LASTTIME("MD_LastTime","最近一次上报的MD包时间"),
	ML_LASTTIME("ML_LastTime","最近一次上报的ML包时间"),
	UP_MC_LASTTIME("UP_MC_LastTime","最近一次上报的MC包时间"),
	UP_MC_TYPE("UP_MC_TYPE","最近一次上报的MC指令"),
	DOWN_MC_LASTTIME("DOWN_MC_LastTime","桩发送的下行指令的时间"),
	DOWN_MC_TYPE("DOWN_MC_TYPE","桩发送的下行指令"),
	CHARGING_BIKE_IMEILIST("ChargingBikeIMEIList","正在充电的车的IMEI号"),
	
	MONITOR_ALLCHARGERPILE_STATUS("Monitor_AllChargerPile_Status_","充电桩列表标识"),
	MONITOR_CHARGERPILE_STATUS("Monitor_ChargerPile_Status_","充电桩明细标识"),
	;
	
	private String chargeStatus;
	private String dec;
	private ChargeStatusEnum(String chargeStatus,String dec){
		this.chargeStatus = chargeStatus;
		this.dec = dec;
	}
	
	public String getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public String getDec() {
		return dec;
	}
	public void setDec(String dec) {
		this.dec = dec;
	}
	
	
}

package com.minxing.socket.dto.http.req.charge;

public class MDReqDto {
	private int mdImei;
	// 电压
	private double mdVoltage;
	// 电流
	private double mdCurrent;
	private int mdState;
	// 充电口编号
	private int mdPortNumber;
	// 充电器故障代号
	private int mdChargeError;
	// 充电口车辆ID号
	private int mdPortBikeNumber;
	public int getMdImei() {
		return mdImei;
	}
	public void setMdImei(int mdImei) {
		this.mdImei = mdImei;
	}
	public double getMdVoltage() {
		return mdVoltage;
	}
	public void setMdVoltage(double mdVoltage) {
		this.mdVoltage = mdVoltage;
	}
	public double getMdCurrent() {
		return mdCurrent;
	}
	public void setMdCurrent(double mdCurrent) {
		this.mdCurrent = mdCurrent;
	}
	public int getMdState() {
		return mdState;
	}
	public void setMdState(int mdState) {
		this.mdState = mdState;
	}
	public int getMdPortNumber() {
		return mdPortNumber;
	}
	public void setMdPortNumber(int mdPortNumber) {
		this.mdPortNumber = mdPortNumber;
	}
	public int getMdChargeError() {
		return mdChargeError;
	}
	public void setMdChargeError(int mdChargeError) {
		this.mdChargeError = mdChargeError;
	}
	public int getMdPortBikeNumber() {
		return mdPortBikeNumber;
	}
	public void setMdPortBikeNumber(int mdPortBikeNumber) {
		this.mdPortBikeNumber = mdPortBikeNumber;
	}

}

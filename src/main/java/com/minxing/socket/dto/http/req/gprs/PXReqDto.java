package com.minxing.socket.dto.http.req.gprs;

import java.io.Serializable;

public class PXReqDto implements Serializable{
	private static final long serialVersionUID = 5286362458144857225L;
	
	// 设备唯一编号（截取8位）
	private int pgImei;
	// 经度
	private double pgLongitude;
	// 纬度
	private double pgLatitude;
	// 海拔高度
	private int pgHight;
	// 实际速度
	private float pgSpeed;
	// 卫星数量
	private int pgStar;
	// 0:无外接电源 1:有外接电源
	private int pgElectric;
	// 0:电门锁关 1:电门锁开
	private int pgDoorLock;
	// 0:没锁车 1:锁车
	private int pgLocked;
	// 0:没震动 1:震动
	private int pgShaked;
	// 0:不是轮车输入模式 1：是轮车输入模式
	private int pgWheelInput;
	// 0:不是自动锁车模式 1:是
	private int pgAutoLocked;
	// 0：没跌倒 1: 跌倒
	private int pgTumble;
	// 0:无故障  1:故障
	private int pgError;
	public int getPgImei() {
		return pgImei;
	}
	public void setPgImei(int pgImei) {
		this.pgImei = pgImei;
	}
	public double getPgLongitude() {
		return pgLongitude;
	}
	public void setPgLongitude(double pgLongitude) {
		this.pgLongitude = pgLongitude;
	}
	public double getPgLatitude() {
		return pgLatitude;
	}
	public void setPgLatitude(double pgLatitude) {
		this.pgLatitude = pgLatitude;
	}
	public int getPgHight() {
		return pgHight;
	}
	public void setPgHight(int pgHight) {
		this.pgHight = pgHight;
	}
	public float getPgSpeed() {
		return pgSpeed;
	}
	public void setPgSpeed(float pgSpeed) {
		this.pgSpeed = pgSpeed;
	}
	public int getPgStar() {
		return pgStar;
	}
	public void setPgStar(int pgStar) {
		this.pgStar = pgStar;
	}
	public int getPgElectric() {
		return pgElectric;
	}
	public void setPgElectric(int pgElectric) {
		this.pgElectric = pgElectric;
	}
	public int getPgDoorLock() {
		return pgDoorLock;
	}
	public void setPgDoorLock(int pgDoorLock) {
		this.pgDoorLock = pgDoorLock;
	}
	public int getPgLocked() {
		return pgLocked;
	}
	public void setPgLocked(int pgLocked) {
		this.pgLocked = pgLocked;
	}
	public int getPgShaked() {
		return pgShaked;
	}
	public void setPgShaked(int pgShaked) {
		this.pgShaked = pgShaked;
	}
	public int getPgWheelInput() {
		return pgWheelInput;
	}
	public void setPgWheelInput(int pgWheelInput) {
		this.pgWheelInput = pgWheelInput;
	}
	public int getPgAutoLocked() {
		return pgAutoLocked;
	}
	public void setPgAutoLocked(int pgAutoLocked) {
		this.pgAutoLocked = pgAutoLocked;
	}
	public int getPgTumble() {
		return pgTumble;
	}
	public void setPgTumble(int pgTumble) {
		this.pgTumble = pgTumble;
	}
	public int getPgError() {
		return pgError;
	}
	public void setPgError(int pgError) {
		this.pgError = pgError;
	}
}

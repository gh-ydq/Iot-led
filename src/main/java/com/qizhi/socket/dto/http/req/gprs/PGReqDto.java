package com.qizhi.socket.dto.http.req.gprs;

import java.io.Serializable;

public class PGReqDto implements Serializable{
	private static final long serialVersionUID = 5262300357495899547L;
	
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
	//UnixTime 时间，0时区，具体时区由应用层计算
	private short time;
	
	
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
	public short getTime() {
		return time;
	}
	public void setTime(short time) {
		this.time = time;
	}
	
}

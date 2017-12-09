package com.minxing.socket.dto.http.req.gprs;

import java.io.Serializable;

public class PGReqDto implements Serializable{
	private static final long serialVersionUID = 5262300357495899547L;
	private char header0;
	private char header1;
	// IMEI 号
	private int imei;
	//数据包字节数
	private int length;
	// 经度
	private float lng;
	// 纬度
	private float lat;
	// 海拔高度
	private short hight;
	// 速度（*100） 取整
	private float speed;
	//设备状态
	private byte status;
	// 卫星数目
	private byte star;
	//UnixTime 时间，0时区，具体时区由应用层计算
	private int time;
	
	// Bit0：电源(1接通，0断开）
	private byte powerStatus;
	//电门锁开关  bit1
	private byte EDoorSwitchStatus;
	//Bit2： 是否为静默模式
	private byte silentModeStatus;
	//Bit3：蓝牙是否锁车（1布防，0撤防）
	private byte bluetoothLockStatus;
	public char getHeader0() {
		return header0;
	}
	public void setHeader0(char header0) {
		this.header0 = header0;
	}
	public char getHeader1() {
		return header1;
	}
	public void setHeader1(char header1) {
		this.header1 = header1;
	}
	public int getImei() {
		return imei;
	}
	public void setImei(int imei) {
		this.imei = imei;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public short getHight() {
		return hight;
	}
	public void setHight(short hight) {
		this.hight = hight;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public byte getStar() {
		return star;
	}
	public void setStar(byte star) {
		this.star = star;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public byte getPowerStatus() {
		return powerStatus;
	}
	public void setPowerStatus(byte powerStatus) {
		this.powerStatus = powerStatus;
	}
	public byte getEDoorSwitchStatus() {
		return EDoorSwitchStatus;
	}
	public void setEDoorSwitchStatus(byte eDoorSwitchStatus) {
		EDoorSwitchStatus = eDoorSwitchStatus;
	}
	public byte getSilentModeStatus() {
		return silentModeStatus;
	}
	public void setSilentModeStatus(byte silentModeStatus) {
		this.silentModeStatus = silentModeStatus;
	}
	public byte getBluetoothLockStatus() {
		return bluetoothLockStatus;
	}
	public void setBluetoothLockStatus(byte bluetoothLockStatus) {
		this.bluetoothLockStatus = bluetoothLockStatus;
	}
	
	
	
	
}

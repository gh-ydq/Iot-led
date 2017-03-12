package com.qizhi.socket.dto.http.req.gprs;

public class PLReqDto {
	private int plImei;
	private int plLac; // location area code 位置区域码
	private int plCellid; // 基站小区编号
	private int plSingal;// 信号强度
	
	private int plElectric; // (0:无外界电源 1:有外接电源)
	private int plDoorLock; // (0:电门锁关，1:电门锁开)
	private int plLocked; // (0:没锁车 1:锁车)
	private int plShaked; // (0:无震动，1:震动)
	private int plWheelInput;// (0:不是轮车输入模式 1:是轮车输入模式)
	private int plAutoLocked; // (0:不是自动锁车 1：自动锁车)
	private int plTumble; // (0:没跌倒 1:跌倒)
	private int plError; // (0:无故障 1:有故障)
	public int getPlImei() {
		return plImei;
	}
	public void setPlImei(int plImei) {
		this.plImei = plImei;
	}
	public int getPlLac() {
		return plLac;
	}
	public void setPlLac(int plLac) {
		this.plLac = plLac;
	}
	public int getPlCellid() {
		return plCellid;
	}
	public void setPlCellid(int plCellid) {
		this.plCellid = plCellid;
	}
	public int getPlSingal() {
		return plSingal;
	}
	public void setPlSingal(int plSingal) {
		this.plSingal = plSingal;
	}
	public int getPlElectric() {
		return plElectric;
	}
	public void setPlElectric(int plElectric) {
		this.plElectric = plElectric;
	}
	public int getPlDoorLock() {
		return plDoorLock;
	}
	public void setPlDoorLock(int plDoorLock) {
		this.plDoorLock = plDoorLock;
	}
	public int getPlLocked() {
		return plLocked;
	}
	public void setPlLocked(int plLocked) {
		this.plLocked = plLocked;
	}
	public int getPlShaked() {
		return plShaked;
	}
	public void setPlShaked(int plShaked) {
		this.plShaked = plShaked;
	}
	public int getPlWheelInput() {
		return plWheelInput;
	}
	public void setPlWheelInput(int plWheelInput) {
		this.plWheelInput = plWheelInput;
	}
	public int getPlAutoLocked() {
		return plAutoLocked;
	}
	public void setPlAutoLocked(int plAutoLocked) {
		this.plAutoLocked = plAutoLocked;
	}
	public int getPlTumble() {
		return plTumble;
	}
	public void setPlTumble(int plTumble) {
		this.plTumble = plTumble;
	}
	public int getPlError() {
		return plError;
	}
	public void setPlError(int plError) {
		this.plError = plError;
	}

	

	
	
}

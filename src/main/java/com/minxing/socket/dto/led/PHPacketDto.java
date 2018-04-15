package com.minxing.socket.dto.led;

import com.minxing.socket.dto.DatagramPacketBasicDto;

/**
 * PH Led 数据心跳包
 * @author yudengqiu
 *
 */
public class PHPacketDto extends DatagramPacketBasicDto  {

	private static final long serialVersionUID = 2152662457803329906L;
	// 数据包字节长度
	private int length;
	
	private byte seq;
	private long imsi;
	byte powerSwitch;
	byte chargeStatus;
	short batteryVoltage;
	int activeTime;
	int usedPowerNum;
	short activeLightNum;
	int lightUsedHour;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte getSeq() {
		return seq;
	}

	public void setSeq(byte seq) {
		this.seq = seq;
	}

	public long getImsi() {
		return imsi;
	}

	public void setImsi(long imsi) {
		this.imsi = imsi;
	}

	public byte getPowerSwitch() {
		return powerSwitch;
	}

	public void setPowerSwitch(byte powerSwitch) {
		this.powerSwitch = powerSwitch;
	}

	public byte getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(byte chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public short getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(short batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(int activeTime) {
		this.activeTime = activeTime;
	}

	public int getUsedPowerNum() {
		return usedPowerNum;
	}

	public void setUsedPowerNum(int usedPowerNum) {
		this.usedPowerNum = usedPowerNum;
	}

	public short getActiveLightNum() {
		return activeLightNum;
	}

	public void setActiveLightNum(short activeLightNum) {
		this.activeLightNum = activeLightNum;
	}

	public int getLightUsedHour() {
		return lightUsedHour;
	}

	public void setLightUsedHour(int lightUsedHour) {
		this.lightUsedHour = lightUsedHour;
	}


	@Override
	public String toString() {
		return "PHPacketDto{" +
				"length=" + length +
				", seq=" + seq +
				", imsi=" + imsi +
				", powerSwitch=" + powerSwitch +
				", chargeStatus=" + chargeStatus +
				", batteryVoltage=" + batteryVoltage +
				", activeTime=" + activeTime +
				", usedPowerNum=" + usedPowerNum +
				", activeLightNum=" + activeLightNum +
				", lightUsedHour=" + lightUsedHour +
				"} " + super.toString();
	}
}

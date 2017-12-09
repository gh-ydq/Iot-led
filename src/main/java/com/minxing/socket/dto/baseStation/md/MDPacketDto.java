package com.minxing.socket.dto.baseStation.md;

import com.minxing.socket.dto.DatagramPacketBasicDto;

/**
 * ���׮ʵʱ���
 * MD ���Ӧ�Ĳ���
 * @author yudengqiu
 *
 */
public class MDPacketDto extends DatagramPacketBasicDto {

	private static final long serialVersionUID = -6999992683698572397L;
	//数据包长度
	private int length;
	//电压
	private short voltage;
	//电流
	private byte electric;
	// 充电状态
	private byte status;
	// 充电桩电口编号
	private byte chargePortNo;
	//充电桩故障
	private byte chargeFail;
	//充电桩关联设备imei号（8位）
	private int carIdNo;
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public short getVoltage() {
		return voltage;
	}
	public void setVoltage(short voltage) {
		this.voltage = voltage;
	}
	public byte getElectric() {
		return electric;
	}
	public void setElectric(byte electric) {
		this.electric = electric;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public byte getChargePortNo() {
		return chargePortNo;
	}
	public void setChargePortNo(byte chargePortNo) {
		this.chargePortNo = chargePortNo;
	}
	public byte getChargeFail() {
		return chargeFail;
	}
	public void setChargeFail(byte chargeFail) {
		this.chargeFail = chargeFail;
	}
	
	public int getCarIdNo() {
		return carIdNo;
	}
	public void setCarIdNo(int carIdNo) {
		this.carIdNo = carIdNo;
	}
	@Override
	public String toString() {
		return "MDPacketDto [length=" + length + ", voltage=" + voltage + ", electric=" + electric + ", status="
				+ status + ", chargePortNo=" + chargePortNo + ", chargeFail=" + chargeFail + ", carIdNo=" + carIdNo
				+ ", getHeader0()=" + getHeader0() + ", getHeader1()=" + getHeader1() + ", getImei()=" + getImei()
				+ "]";
	}
	
}

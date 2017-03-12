package com.qizhi.socket.dto.gprs.ph;

import com.qizhi.socket.dto.DatagramPacketBasicDto;
import com.qizhi.socket.dto.gprs.GPRSSubStatus;
/**
 * PH ���Ӧ�Ĳ���
 * @author yudengqiu
 *
 */
public class PHPacketDto extends DatagramPacketBasicDto  {

	private static final long serialVersionUID = 2152662457803329906L;
	// 数据包字节长度
	private int length;
	
	private byte seq;
	private byte status;
	private long imsi;
	private short powerVoltage;
	private short batteryVotage;
	private byte sensity;
	private byte star;
	private byte ecode;
	
	private GPRSSubStatus gprsSubStatus;
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
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public long getImsi() {
		return imsi;
	}
	public void setImsi(long imsi) {
		this.imsi = imsi;
	}
	public short getPowerVoltage() {
		return powerVoltage;
	}
	public void setPowerVoltage(short powerVoltage) {
		this.powerVoltage = powerVoltage;
	}
	public short getBatteryVotage() {
		return batteryVotage;
	}
	public void setBatteryVotage(short batteryVotage) {
		this.batteryVotage = batteryVotage;
	}
	public byte getSensity() {
		return sensity;
	}
	public void setSensity(byte sensity) {
		this.sensity = sensity;
	}
	public byte getStar() {
		return star;
	}
	public void setStar(byte star) {
		this.star = star;
	}
	public byte getEcode() {
		return ecode;
	}
	public void setEcode(byte ecode) {
		this.ecode = ecode;
	}
	
	
	public GPRSSubStatus getGprsSubStatus() {
		return gprsSubStatus;
	}
	public void setGprsSubStatus(GPRSSubStatus gprsSubStatus) {
		this.gprsSubStatus = gprsSubStatus;
	}
	@Override
	public String toString() {
		return "PHPacketDto [length=" + length + ", seq=" + seq + ", status=" + status + ", imsi=" + imsi
				+ ", powerVoltage=" + powerVoltage + ", batteryVotage=" + batteryVotage + ", sensity=" + sensity
				+ ", star=" + star + ", ecode=" + ecode + ", gprsSubStatus=" + gprsSubStatus.toString()
				+ "]";
	}
	
}

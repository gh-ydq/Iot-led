package com.minxing.socket.dto.gprs.pl;

import com.minxing.socket.dto.gprs.GPRSSubStatus;
import com.minxing.socket.dto.DatagramPacketBasicDto;

/**
 * PL ����Ӧ�Ĳ���
 * @author yudengqiu
 *
 */
public class PLPacketDto extends DatagramPacketBasicDto  {

	private static final long serialVersionUID = 2152662457803329906L;
	//数据包字节数
	private int length;
	//位置区域码
	private int lac;
	//基站小区编号
	private int cellid;
	// 信号强度
	private short signal;
	//设备状态
	private byte status;
	//Unixtimestamp
	private int time;
	
	private GPRSSubStatus plSubStatus;
	
	
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getLac() {
		return lac;
	}
	public void setLac(int lac) {
		this.lac = lac;
	}
	public int getCellid() {
		return cellid;
	}
	public void setCellid(int cellid) {
		this.cellid = cellid;
	}
	public short getSignal() {
		return signal;
	}
	public void setSignal(short signal) {
		this.signal = signal;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public GPRSSubStatus getPlSubStatus() {
		return plSubStatus;
	}
	public void setPlSubStatus(GPRSSubStatus plSubStatus) {
		this.plSubStatus = plSubStatus;
	}
	
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "PLPacketDto [length=" + length + ", getHeader1()=" + getHeader1() + ", getImei()=" + getImei()+ ", lac=" + lac + ", cellid=" + cellid + ", signal=" + signal
				+ ", status=" + status + ", pgSubStatus=" + plSubStatus.toString() + ", getHeader0()=" + getHeader0()
				 + "]";
	}
	
	
	
}

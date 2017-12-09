package com.minxing.socket.dto.gprs.pg;

import com.minxing.socket.dto.gprs.GPRSSubStatus;
import com.minxing.socket.dto.DatagramPacketBasicDto;

/**
 * PG ����Ӧ�Ĳ���
 * @author yudengqiu
 *
 */
public class PGPacketDto extends DatagramPacketBasicDto  {

	private static final long serialVersionUID = -8597161566517930453L;
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
	
	private GPRSSubStatus pgSubStatus;
	
	
	
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
	public GPRSSubStatus getPgSubStatus() {
		return pgSubStatus;
	}
	public void setPgSubStatus(GPRSSubStatus pgSubStatus) {
		this.pgSubStatus = pgSubStatus;
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "PGPacketDto [length=" + length +", getHeader1()=" + getHeader1() + ", getImei()=" + getImei()+ ", lng=" + lng + ", lat=" + lat + ", hight=" + hight + ", speed="
				+ speed + ", status=" + status + ", star=" + star + ", time=" + time+ ", pgSubStatus=" + pgSubStatus.toString() + "]";
	}
	
	
	
}

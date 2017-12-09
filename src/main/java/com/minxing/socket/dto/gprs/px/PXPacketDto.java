package com.minxing.socket.dto.gprs.px;

import com.minxing.socket.dto.DatagramPacketBasicDto;
import com.minxing.socket.dto.gprs.GPRSSubStatus;

/**
 * PX ����Ӧ�Ĳ���
 * @author yudengqiu
 *
 */
public class PXPacketDto extends DatagramPacketBasicDto {

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
	
	private GPRSSubStatus pxSubStatus;
	
	
	
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
	public GPRSSubStatus getPxSubStatus() {
		return pxSubStatus;
	}
	public void setPgSubStatus(GPRSSubStatus pxSubStatus) {
		this.pxSubStatus = pxSubStatus;
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
				+ speed + ", status=" + status + ", star=" + star + ", time=" + time+ ", pgSubStatus=" + pxSubStatus.toString() + "]";
	}
	
	
	
}

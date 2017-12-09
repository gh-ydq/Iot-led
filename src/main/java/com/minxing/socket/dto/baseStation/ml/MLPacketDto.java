package com.minxing.socket.dto.baseStation.ml;

import com.minxing.socket.dto.DatagramPacketBasicDto;
/**
 * ��վ��λ��ݰ�
 * ML ���Ӧ�Ĳ���
 * @author yudengqiu
 *
 */
public class MLPacketDto extends DatagramPacketBasicDto  {

	private static final long serialVersionUID = -378595322683420831L;
	//��ݰ�ĳ���
	private int length;
	// λ��������
	private short lac;
	// ��վС����
	private short cellid;
	//�ź�ǿ��
	private byte signal;
	//�¶�
	private byte temperature;
	// ���׮IMSI
	private Long imsi;
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public short getLac() {
		return lac;
	}
	public void setLac(short lac) {
		this.lac = lac;
	}
	public short getCellid() {
		return cellid;
	}
	public void setCellid(short cellid) {
		this.cellid = cellid;
	}
	public byte getSignal() {
		return signal;
	}
	public void setSignal(byte signal) {
		this.signal = signal;
	}
	public byte getTemperature() {
		return temperature;
	}
	public void setTemperature(byte temperature) {
		this.temperature = temperature;
	}
	public Long getImsi() {
		return imsi;
	}
	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}
	@Override
	public String toString() {
		return "MLPacketDto [length=" + length + ", lac=" + lac + ", cellid=" + cellid + ", signal=" + signal
				+ ", temperature=" + temperature + ", imsi=" + imsi + ", getHeader0()=" + getHeader0()
				+ ", getHeader1()=" + getHeader1() + ", getImei()=" + getImei() + "]";
	}
	
	
}

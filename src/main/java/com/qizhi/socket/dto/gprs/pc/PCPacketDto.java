package com.qizhi.socket.dto.gprs.pc;

import com.qizhi.socket.dto.DatagramPacketBasicDto;
/**
 * PC ����Ӧ�Ĳ���
 * @author yudengqiu
 *
 */
public class PCPacketDto extends DatagramPacketBasicDto  {

	private static final long serialVersionUID = -8597161566517930453L;
	//字节数据长度
	private int length;
	private byte seq;
	private byte cmd;
	private String param;
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
	public byte getCmd() {
		return cmd;
	}
	public void setCmd(byte cmd) {
		this.cmd = cmd;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	@Override
	public String toString() {
		return "PCPacketDto [length=" + length + ", seq=" + seq + ", cmd=" + cmd + ", param=" + param
				+ ", getHeader0()=" + getHeader0() + ", getHeader1()=" + getHeader1() + ", getImei()=" + getImei()
				+ "]";
	}
	
	
	
}

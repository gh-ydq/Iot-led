package com.minxing.socket.dto.baseStation.mc;

import java.io.Serializable;

import com.minxing.socket.dto.DatagramPacketBasicDto;

public class MCPacketDto extends DatagramPacketBasicDto implements Serializable {
	
	private static final long serialVersionUID = -7261269120641548344L;
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
}

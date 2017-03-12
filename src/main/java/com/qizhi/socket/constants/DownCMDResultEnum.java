package com.qizhi.socket.constants;

public enum DownCMDResultEnum {
	SUCCESS((byte)0,"下行命令发送成功"),
	FAIL((byte)1,"下行命令发送失败")
	;
	private byte result;
	private String dec;
	
	private DownCMDResultEnum(byte result,String dec){
		this.result = result;
		this.dec = dec;
	}

	public byte getResult() {
		return result;
	}

	public void setResult(byte result) {
		this.result = result;
	}

	public String getDec() {
		return dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}
	
}

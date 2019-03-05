package com.minxing.socket.constants;

/**
 *
 * @author yudengqiu
 *
 */
public enum BusiTypeEnum {
	TRACKER("01","行车轨迹命令"),
	LED_LIGHT("02","led 灯命令"),
	;

	private String type;
	private String dec;

	private BusiTypeEnum(String type, String dec){
		this.type = type;
		this.dec = dec;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDec() {
		return dec;
	}
	public void setDec(String dec) {
		this.dec = dec;
	}
	
	
}

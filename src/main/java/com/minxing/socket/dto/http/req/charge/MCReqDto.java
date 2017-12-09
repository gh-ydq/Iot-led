package com.minxing.socket.dto.http.req.charge;

public class MCReqDto {
	
	private int mcImei;
	private int mcCmd;
	private int mcSequence;
	private String mcParam;
	public int getMcImei() {
		return mcImei;
	}
	public void setMcImei(int mcImei) {
		this.mcImei = mcImei;
	}
	public int getMcCmd() {
		return mcCmd;
	}
	public void setMcCmd(int mcCmd) {
		this.mcCmd = mcCmd;
	}
	public int getMcSequence() {
		return mcSequence;
	}
	public void setMcSequence(int mcSequence) {
		this.mcSequence = mcSequence;
	}
	public String getMcParam() {
		return mcParam;
	}
	public void setMcParam(String mcParam) {
		this.mcParam = mcParam;
	}

	
	
}

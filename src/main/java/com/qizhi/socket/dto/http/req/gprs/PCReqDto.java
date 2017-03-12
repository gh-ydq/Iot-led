package com.qizhi.socket.dto.http.req.gprs;

public class PCReqDto {
	
	private int pcImei;
	
	private int pcCmd;
	
	private String pcParam;
	
	private int pcSequence;

	public int getPcImei() {
		return pcImei;
	}

	public void setPcImei(int pcImei) {
		this.pcImei = pcImei;
	}

	public int getPcCmd() {
		return pcCmd;
	}

	public void setPcCmd(int pcCmd) {
		this.pcCmd = pcCmd;
	}

	public String getPcParam() {
		return pcParam;
	}

	public void setPcParam(String pcParam) {
		this.pcParam = pcParam;
	}

	public int getPcSequence() {
		return pcSequence;
	}

	public void setPcSequence(int pcSequence) {
		this.pcSequence = pcSequence;
	}


	
	
}

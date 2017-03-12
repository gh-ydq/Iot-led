package com.qizhi.socket.dto.http.req.charge;

public class MLReqDto {
	
	private int mlImei;
	private int mlLAC;
	private int mlCellid;
	private int mlSingal;
	private int mlTemperature;
	private Long mlImsi;
	public int getMlImei() {
		return mlImei;
	}
	public void setMlImei(int mlImei) {
		this.mlImei = mlImei;
	}
	public int getMlLAC() {
		return mlLAC;
	}
	public void setMlLAC(int mlLAC) {
		this.mlLAC = mlLAC;
	}
	public int getMlCellid() {
		return mlCellid;
	}
	public void setMlCellid(int mlCellid) {
		this.mlCellid = mlCellid;
	}
	public int getMlSingal() {
		return mlSingal;
	}
	public void setMlSingal(int mlSingal) {
		this.mlSingal = mlSingal;
	}
	public int getMlTemperature() {
		return mlTemperature;
	}
	public void setMlTemperature(int mlTemperature) {
		this.mlTemperature = mlTemperature;
	}
	public Long getMlImsi() {
		return mlImsi;
	}
	public void setMlImsi(Long mlImsi) {
		this.mlImsi = mlImsi;
	}

}

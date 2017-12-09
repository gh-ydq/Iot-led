package com.minxing.socket.dto.gprs.ph;

public class PHErrorCode {
	private byte phMachineError;
	private byte phBrakeErroe;
	private byte phHandleBarError;
	private byte phControlError;
	private byte phHold;
	public byte getPhMachineError() {
		return phMachineError;
	}
	public void setPhMachineError(byte phMachineError) {
		this.phMachineError = phMachineError;
	}
	public byte getPhBrakeErroe() {
		return phBrakeErroe;
	}
	public void setPhBrakeErroe(byte phBrakeErroe) {
		this.phBrakeErroe = phBrakeErroe;
	}
	public byte getPhHandleBarError() {
		return phHandleBarError;
	}
	public void setPhHandleBarError(byte phHandleBarError) {
		this.phHandleBarError = phHandleBarError;
	}
	public byte getPhControlError() {
		return phControlError;
	}
	public void setPhControlError(byte phControlError) {
		this.phControlError = phControlError;
	}
	public byte getPhHold() {
		return phHold;
	}
	public void setPhHold(byte phHold) {
		this.phHold = phHold;
	}
	@Override
	public String toString() {
		return "PHErrorCode [phMachineError=" + phMachineError + ", phBrakeErroe=" + phBrakeErroe
				+ ", phHandleBarError=" + phHandleBarError + ", phControlError=" + phControlError + ", phHold=" + phHold
				+ "]";
	}
	
	
}

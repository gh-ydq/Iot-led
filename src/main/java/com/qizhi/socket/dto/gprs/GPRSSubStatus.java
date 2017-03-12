package com.qizhi.socket.dto.gprs;

import java.io.Serializable;

public class GPRSSubStatus implements Serializable {
	private static final long serialVersionUID = -5371736380827965009L;
	// Bit0：电源(1接通，0断开）
	private byte powerStatus;
	//电门锁开关  bit1
	private byte EDoorSwitchStatus;
	//Bit2： 是否为静默模式
	private byte silentModeStatus;
	//Bit3：蓝牙是否锁车（1布防，0撤防）
	private byte bluetoothLockStatus;
	
	
	public byte getPowerStatus() {
		return powerStatus;
	}
	public void setPowerStatus(byte powerStatus) {
		this.powerStatus = powerStatus;
	}
	public byte getEDoorSwitchStatus() {
		return EDoorSwitchStatus;
	}
	public void setEDoorSwitchStatus(byte eDoorSwitchStatus) {
		EDoorSwitchStatus = eDoorSwitchStatus;
	}
	public byte getSilentModeStatus() {
		return silentModeStatus;
	}
	public void setSilentModeStatus(byte silentModeStatus) {
		this.silentModeStatus = silentModeStatus;
	}
	public byte getBluetoothLockStatus() {
		return bluetoothLockStatus;
	}
	public void setBluetoothLockStatus(byte bluetoothLockStatus) {
		this.bluetoothLockStatus = bluetoothLockStatus;
	}
	@Override
	public String toString() {
		return "GPRSSubStatus [powerStatus=" + powerStatus + ", EDoorSwitchStatus=" + EDoorSwitchStatus
				+ ", silentModeStatus=" + silentModeStatus + ", bluetoothLockStatus=" + bluetoothLockStatus + "]";
	}
	
	
}
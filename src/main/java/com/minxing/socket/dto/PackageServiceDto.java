package com.minxing.socket.dto;

public class PackageServiceDto {
    // 请求数据包的字节数组
    private byte[] packageBytes;

    private char header0;
    private char header1;
    // 业务类型
    private String busiType;
    // 设备号
    private int imei;

    public byte[] getPackageBytes() {
        return packageBytes;
    }

    public void setPackageBytes(byte[] packageBytes) {
        this.packageBytes = packageBytes;
    }

    public char getHeader0() {
        return header0;
    }

    public void setHeader0(char header0) {
        this.header0 = header0;
    }

    public char getHeader1() {
        return header1;
    }

    public void setHeader1(char header1) {
        this.header1 = header1;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public int getImei() {
        return imei;
    }

    public void setImei(int imei) {
        this.imei = imei;
    }
}

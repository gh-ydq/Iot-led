package com.minxing.socket.util;
/**
 * 基本数据类型转字节数组
 * @author yudengqiu
 *
 */
public class NumberToByteArray {
	/**  
	    * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytesToInt（）配套使用 
	    * @param value  
	    *            要转换的int值 
	    * @return byte数组 
	    */    
	public static byte[] intToBytes( int value )   
	{   
	    byte[] src = new byte[4];  
	    src[0] =  (byte) (value & 0xFF);     
	    src[1] =  (byte) ((value>>8) & 0xFF);
	    src[2] =  (byte) ((value>>16) & 0xFF); 
	    src[3] =  (byte) ((value>>24) & 0xFF);  
	    return src;   
	}  
	 /**  
	    * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。  和bytesToInt2（）配套使用 
	    */    
	public static byte[] intToBytes2(int value)   
	{   
	    byte[] src = new byte[4];  
	    src[0] = (byte) ((value>>24) & 0xFF);  
	    src[1] = (byte) ((value>>16)& 0xFF);  
	    src[2] = (byte) ((value>>8)&0xFF);    
	    src[3] = (byte) (value & 0xFF);       
	    return src;  
	}  
}

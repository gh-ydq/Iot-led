package com.minxing.socket.util;


/**
 * 字节数组转java基本类型
 * @author yudengqiu
 *
 */
public class ByteArrayToNumber {
	
	public static char byteToChar(byte pgByte) {

        char pgChar = (char) pgByte;
        return pgChar;
    }
	
	public static int byteArrayToInt(byte[] src, int offset) {  
	    int value;    
	    value = (int) ( ((src[offset] & 0xFF)<<24) //最高位 
	            |((src[offset+1] & 0xFF)<<16)  
	            |((src[offset+2] & 0xFF)<<8)  
	            |(src[offset+3] & 0xFF));  
	    return value;  
	}
	
	public static short byteArrayToShort(byte[] src, int offset) {  
		short value = 0; 
		value = (short) ( ((src[offset] & 0xFF)<<8) //最高位 
		            |((src[offset+1] & 0xFF)))  ;
        return value; 
	}
	
	public static long bytesToLong(byte[] bytes, int offset) {  
		long s = 0;
        long s0 = bytes[offset] & 0xff;// 最低位
        long s1 = bytes[offset+1] & 0xff;
        long s2 = bytes[offset+2] & 0xff;
        long s3 = bytes[offset+3] & 0xff;
        long s4 = bytes[offset+4] & 0xff;
        long s5 = bytes[offset+5] & 0xff;
        long s6 = bytes[offset+6] & 0xff;
        long s7 = bytes[offset+7] & 0xff;

        s0 <<= 8*7;
        s1 <<= 8*6;
        s2 <<= 8*5;
        s3 <<= 8*4;
        s4 <<= 8*3;
        s5 <<= 8*2;
        s6 <<= 8;
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
        return s;
    }
	
	/**
	 * 取字节第几bit的值
	 * @param b
	 * @param offset 0-7
	 * @return
	 */
	public static byte biteToByte(byte b, int offset) {  
		byte destByte ;
		destByte = (byte)((b>>(7-offset)) & 0x1);
        return destByte; 
	}
	
	/**
	 * 4bit表示一个16进制
	 * 一个字节表示2个16进制
	 * @param b
	 * @return
	 */
	public static String biteToHexString(byte b) {  
		String hexString = null;
		hexString = Integer.toHexString(((b>>4) & 0x0f));
		hexString += Integer.toHexString((b & 0x0f));
        return hexString; 
	}
	
	/**
	 * 字节数组转字符串
	 * @param bytes
	 * @return
	 */
	public static String bytesToString(byte[] bytes) {  
		String newStr = new String(bytes);
        return newStr; 
	}
	
	/** 
	 * 字节转换为浮点 
	 *  
	 * @param b 字节（至少4个字节） 
	 * @param index 开始位置 
	 * @return 
	 */  
	public static float byteArrayfloat(byte[] b, int index) {    
	    int l;                                             
	    l = b[index + 0];                                  
	    l &= 0xff;                                         
	    l |= ((long) b[index + 1] << 8);                   
	    l &= 0xffff;                                       
	    l |= ((long) b[index + 2] << 16);                  
	    l &= 0xffffff;                                     
	    l |= ((long) b[index + 3] << 24);                  
	    return Float.intBitsToFloat(l);                    
	}
	
	/**
	 * 
	 * @param bytes 
	 * @param offset 字节数组开始位置
	 * @param length 长度
	 * @return
	 */
	public static String bytesToHexString(byte[] bytes,int offset,int length){
		StringBuffer buffer = new StringBuffer("");
		for(int i=0;i<length;i++){
			buffer.append(ByteArrayToNumber.biteToHexString(bytes[offset+i]));
		}
		return buffer.toString();
		
	}
	
}

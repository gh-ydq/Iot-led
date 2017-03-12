package com.qizhi;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class Test {
	public static void main(String[] args) {
		byte[] bytes = new byte[1];
		bytes[0] = 42;
		String str = bytesToString(bytes);
		System.out.println("a;;;;;;"+str);
	}
	
	/**
	 * 字节数组转字符串
	 * @param src
	 * @return
	 */
	public static String bytesToString(byte[] bytes) {  
		String newStr=null;
		try {
			newStr = new String(bytes,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return newStr; 
	}
}

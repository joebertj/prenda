package com.prenda.helper;

public class StringUtil {
	public String pad(int len){
		String s = " ";
		for(int i=0;i<22-len;i++){
			s += " ";
		}
		return s;
	}
}

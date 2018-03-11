package com.prenda.helper;

import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;

public class PasswordGenerator {
	
	public static String getPassword() {
		return getPassword(10);
	}
	
	public static String getPassword(int length) {
		//char [] symbols = {'$','-','_','.','+','!','*','(',')'};
		String genkey="";
		Random generator = RandomUtils.JVM_RANDOM;
		for(int i=0;i<length;i++){
			int j=generator.nextInt(3);
			if(j==0){
				genkey+=(char) (generator.nextInt(26)+97);
			}else if(j==1) {
				genkey+=(char) (generator.nextInt(26)+65);
			}else {
				genkey+=(char) (generator.nextInt(10)+48);
			}
		}
		return genkey;
	}
}

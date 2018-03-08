package com.prenda.helper;

import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;

public class PasswordGenerator {
	public static String getPassword() {
		String genkey="";
		Random generator = RandomUtils.JVM_RANDOM;
		for(int i=0;i<10;i++){
			boolean j=generator.nextBoolean();
			if(j){
				genkey+=(char) (generator.nextInt(26)+97);
			}else{
				genkey+=(char) (generator.nextInt(10)+48);
			}
		}
		return genkey;
	}
}

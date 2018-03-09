package com.prenda.helper;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncoderGenerator {
	private static Logger log =Logger.getLogger(PasswordEncoderGenerator.class);
	
	public static String getHash(String input) {
		String hash="";
		int i = 0;
		int j = new Random().nextInt(10);
		while (i < 10 + j) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			hash = passwordEncoder.encode(input);

			log.debug(hash);
			i++;
		}
		return hash;
	}
	
	public static boolean matches(String input, String hash) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(input, hash);
	}
}

package com.prenda.helper;

import java.util.ArrayList;
import java.util.List;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class CustomPasswordGenerator {
	
	// Good enough for username
	public static String getPassword() {
		return getPassword(10);
	}

	// Use for prioritizing length
	public static String getPassword(int length) {
		return getPassword(length, false);
	}
	
	// Use special characters for strongest base
	public static String getPassword(int length, boolean strong) {
		String genkey = "";
		List<CharacterRule> rules = new ArrayList<CharacterRule>();
			rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));

			rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1));

			rules.add(new CharacterRule(EnglishCharacterData.Digit, 1));
		if(strong) {
			rules.add(new CharacterRule(EnglishCharacterData.Special, 1));
		}
		PasswordGenerator generator = new PasswordGenerator();
		genkey = generator.generatePassword(length, rules);
		return genkey;
	}
}

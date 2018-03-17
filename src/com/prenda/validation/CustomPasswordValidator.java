package com.prenda.validation;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

public class CustomPasswordValidator extends  PasswordValidator {

	int minimum = 8;
	int maximum = 60;
	// validator.getMessages(result)
	PasswordValidator validator = new PasswordValidator(
		new LengthRule(minimum, maximum),

		new CharacterRule(EnglishCharacterData.UpperCase, 1),

		new CharacterRule(EnglishCharacterData.LowerCase, 1),

		new CharacterRule(EnglishCharacterData.Digit, 1),

		new CharacterRule(EnglishCharacterData.Special, 1),

		new WhitespaceRule());
	
	public CustomPasswordValidator(){
		
	}
	
	public CustomPasswordValidator(int minimum, int maximum){
		this.minimum = minimum;
		this.maximum = maximum;
	}
	
	public RuleResult validate(String password) {
		RuleResult result = validator.validate(new PasswordData(new String(password)));
		return result;
	}
}

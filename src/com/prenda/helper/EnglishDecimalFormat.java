/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.helper;

public class EnglishDecimalFormat {
	int number;
	String words;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getWords() {
		words = convert(number);
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	private static final String[] majorNames = { "", " thousand", " million", " billion", " trillion", " quadrillion",
			" quintillion" };

	private static final String[] tensNames = { "", " ten", " twenty", " thirty", " fourty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };

	private static final String[] numNames = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen" };

	private String convertLessThanOneThousand(int number) {
		String soFar;

		if (number % 100 < 20) {
			soFar = numNames[number % 100];
			number /= 100;
		} else {
			soFar = numNames[number % 10];
			number /= 10;

			soFar = tensNames[number % 10] + soFar;
			number /= 10;
		}
		if (number == 0)
			return soFar;
		return numNames[number] + " hundred" + soFar;
	}

	public String convert(int number) {
		/* special case */
		if (number == 0) {
			return "zero";
		}

		String prefix = "";

		if (number < 0) {
			number = -number;
			prefix = "negative";
		}

		String soFar = "";
		int place = 0;

		do {
			int n = number % 1000;
			if (n != 0) {
				String s = convertLessThanOneThousand(n);
				soFar = s + majorNames[place] + soFar;
			}
			place++;
			number /= 1000;
		} while (number > 0);

		return (prefix + soFar).trim();
	}
}
package it.univaq.mwt.j2ee.library.business.impl;

public class ConversionUtility {

	public static String addPercentSuffix(String s) {
		if (s==null || "".equals(s)) {
			return "%";
		}
		return s + "%";
	}
}

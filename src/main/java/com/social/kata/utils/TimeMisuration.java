package com.social.kata.utils;

/**
 * 
 * @author giovanni
 *
 */
public enum TimeMisuration {

	SECONDS("seconds"),
	MINUTES("minutes"),
	HOURS("hours"),
	DAYS("days");
	
	private String value;

	
	TimeMisuration(String value) {
		this.value = value;
	}


	public String getValue() {
		return value;
	}
}

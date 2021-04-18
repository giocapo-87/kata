package com.social.kata.utils;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author giovanni
 *
 */
public class TimeUtil {

	/**
	 * 
	 * @param millisecond: tempo intercorso dalla data del post (in millis)
	 * @return il tempo intercorso dalla data del post e l'unità di misura
	 */
	public static PostTime getPostDate(long millisecond) {
		
		PostTime postTime = new PostTime();
		
		if(TimeUnit.MILLISECONDS.toSeconds(millisecond) <60) {
			postTime.setMisurationType(TimeMisuration.SECONDS);
			postTime.setTime(TimeUnit.MILLISECONDS.toSeconds(millisecond));
		}else if(TimeUnit.MILLISECONDS.toMinutes(millisecond) <60) {
			postTime.setMisurationType(TimeMisuration.MINUTES);
			postTime.setTime(TimeUnit.MILLISECONDS.toMinutes(millisecond));
		}else if(TimeUnit.MILLISECONDS.toHours(millisecond) < 24) {
			postTime.setMisurationType(TimeMisuration.HOURS);
			postTime.setTime(TimeUnit.MILLISECONDS.toHours(millisecond));
		}else {
			postTime.setMisurationType(TimeMisuration.DAYS);
			postTime.setTime(TimeUnit.MILLISECONDS.toDays(millisecond));
		}
		
		return postTime;
	}
	
}

package com.deloitte.services.dss.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M");
		String time = sdf.format(date);
		return time;
	}

	public static String getYear() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String time = sdf.format(date);
		return time;
	}

	public static String getMonth() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("M");
		String time = sdf.format(date);
		return time;
	}
}

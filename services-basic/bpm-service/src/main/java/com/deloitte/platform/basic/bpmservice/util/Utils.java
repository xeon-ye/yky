package com.deloitte.platform.basic.bpmservice.util;

import java.io.UnsupportedEncodingException;

public class Utils {
	
	
	public static String covertToUTF(String str)
	{
		try {
			return new String(str.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());	
		}
		
	}

}

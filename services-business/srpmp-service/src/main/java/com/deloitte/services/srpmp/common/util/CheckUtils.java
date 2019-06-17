package com.deloitte.services.srpmp.common.util;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-08
 * @Description : CHECK共用类
 * @Modified :
 */
public class CheckUtils {

	public static Boolean notEmpty(Object object) {
		if (object instanceof Integer) {
			int value = ((Integer) object).intValue();
			if (value == 0) {
				return false;
			} else {
				return true;
			}
		} else if (object instanceof String) {
			String s = (String) object;
			if (s == null || s.length() == 0) {
				return false;
			} else {
				return true;
			}
		} else if (object instanceof Double) {
			double d = ((Double) object).doubleValue();
			if (d == 0) {
				return false;
			} else {
				return true;
			}
		} else if (object instanceof Float) {
			float f = ((Float) object).floatValue();
			if (f == 0) {
				return false;
			} else {
				return true;
			}
		} else if (object instanceof Long) {
			long l = ((Long) object).longValue();
			if (l == 0l) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	public static Boolean isEmpty(Object object) {

		return !notEmpty(object);
	}

	public static Boolean Checkbetween(String str, int v1, int v2) {

		if (str == null || str.length() == 0) {
			return true;
		}
		try {
			double d = Double.parseDouble(str);
			double d1 = v1;
			double d2 = v2;
			if (d < d1 || d > d2) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void checkEmpet(String str, String name, StringBuffer errorMsg) {

		if (str == null || str.length() == 0) {
			errorMsg.append("<br/><br/>");
			errorMsg.append(name + "没有输入");
		}
	}
}

package com.deloitte.services.fssc.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * 文件名称: StringUtil
 * 内容摘要: 字符串工具类
 * 创 建 人: sunrise
 * 创建日期:2015-4-14 上午10:55:04
 * 修改记录1: // 修改历史记录，包括修改日期、修改者及修改内容
 *   修改日期：
 *   版 本 号：
 *   修 改 人：
 *   修改内容：
 * 修改记录2：…
 *
 */
public class StringUtil {
	
	private StringUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * Description:对象转 BigDecimal
	 * @param obj
	 * @return
	 */
	public static BigDecimal getBigDecimal(Object obj){
		if(obj == null ||  obj.toString().equals("") ){
			return new BigDecimal("0");
		}
		return new BigDecimal(obj.toString());
	}


	/**
	 *
	 * Description:对象转 Short
	 * @param obj
	 * @return
	 */
	public static Short getShort(Object obj){
		if(obj == null ||  obj.toString().equals("") ){
			return Short.valueOf("0");
		}
		return  Short.valueOf(obj.toString());
	}

	/**
	 *
	 * Description:对象转 Long
	 * @param obj
	 * @return
	 */
	public static Long getLong(Object obj){
		if(obj == null ||  obj.toString().equals("") ){
			return null;
		}
		return Long.valueOf(obj.toString());
	}

	/**
	 * xuq 新增 2015-08-13
	 * 得到相关键的值
	 * @param key 要得到的键
	 * @param mapCond 得到的键
	 * @return  相应key的value,如果没有 则返回 空字符串
	 */
	public static String getKeyValue(String key,Map<String, Object> mapCond){
		if(mapCond==null){return "";}
		if(mapCond.get(key) == null){return "";}
		return mapCond.get(key).toString();
	}
	
	
	
	/**
	 * xuq 新增 2015-08-13
	 * 对象转字符串
	 * @param  obj 
	 * @return  相应key的value,如果没有 则返回 空字符串
	 */
	public static String objectToString(Object obj){
		if(obj== null){return "";}
		return obj.toString();
	}

	/**
	 * xuq 新增 2015-08-13
	 * 对象转字符串
	 * @param  obj
	 * @return  相应key的value,如果没有 则返回 空字符串
	 */
	public static String objectToStringZero(Object obj){
		if(obj== null){return "0";}
		return obj.toString();
	}

	/**
	 * 判断空 null 和 "" 都返回 true
	 * @param str
	 * @return true 空的
	 */
	public static boolean isEmpty(String str){
		
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
		
	}
	/**
	 * 判断空 null 和 "" 都返回 true
	 * @param str
	 * @return true 空的
	 */
	public static boolean isNotEmpty(String str){
		
		if(str == null || "".equals(str.trim())){
			return false;
		}
		return true;
		
	}
	
	public static boolean isEmpty(String [] sts){
		
		if(sts == null || sts.length == 0){
			return true;
		}
		for(String str : sts){
			if(isEmpty(str)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断空 null 和 "" 都返回 true
	 * @param obj
	 * @return true 空的
	 */
	public static boolean isNotEmpty(Object obj){
		if(obj == null || "".equals(obj.toString().trim())){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断空 null 和 "" 都返回 true
	 * @param obj
	 * @return true 空的
	 */
	public static boolean isEmpty(Object obj){
		if(obj == null || "".equals(obj.toString().trim())){
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Object[] sts){
		if(sts == null || sts.length == 0){
			return true;
		}
		for(Object str : sts){
			if(isEmpty(str)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Description:验证字符串是否存的是Integer
	 * @param value
	 * @return
	 */
	public static boolean isInteger(String [] sts){
		
		if(sts == null || sts.length == 0){
			return false;
		}
		for(String str : sts){
			if(isInteger(str) == false){
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * Description:验证字符串是否存的是Integer
	 * @param value
	 * @return
	 */
	public static boolean isInteger(String value)
	{
		return value.matches("^-?[1-9]\\d*|0$");
	}
	/**
	 * 
	 * Description:验证字符串是否是时间格式 
	 * @param value
	 * @return true 不是 false 是
	 */
	public static boolean isNotDateString(String value)
	{
		return !value.matches("[0-9]{4}-(0[1-9]{1}|1[0-2]{1})-(0[1-9]{1}|[1-2]{1}[0-9]{1}|3[0-1]{1})");
	}
	
	
	/**
	 * 
	 * Description:验证字符串是否是时间格式 
	 * @param value
	 * @return true 不是 false 是
	 */
	public static boolean isDateString(String value)
	{
		return value.matches("[0-9]{4}-(0[1-9]{1}|1[0-2]{1})-(0[1-9]{1}|[1-2]{1}[0-9]{1}|3[0-1]{1})");
	}
	
	/**
	 * 
	 * Description:验证字符串是否存的是Double
	 * @param value
	 * @return
	 */
    public static boolean isDouble(String [] sts){
		
		if(sts == null || sts.length == 0){
			return false;
		}
		for(String str : sts){
			if(isDouble(str) == false){
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * Description:验证字符串是否存的是数字
	 * @param value
	 * @return
	 */
	public static boolean isDouble(String value)
	{
		return value.matches("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$");
	}
	/**
	 * 
	 * Description:验证字符串是否存的是数字
	 * @param value
	 * @return
	 */
    public static boolean isNumber(String [] sts){
		
		if(sts == null || sts.length == 0){
			return false;
		}
		for(String str : sts){
			if(isNumber(str) == false){
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * Description:验证字符串是否存的是数字
	 * @param value
	 * @return
	 */
	public static boolean isNumber(String value)
	{
		return value.matches("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)|(^-?[1-9]\\d*|0)$");
	}
	public static BigDecimal castToBigDecimal(String value)
	{
		
		return isEmpty(value)?null:new BigDecimal(value);
	}
    public static Double castToDouble(String value)
    {
		
		return isEmpty(value)?null:Double.parseDouble(value);
	}
	/**
	 * 传 null或者 null 默认返回 0
	 * @param value
	 * @return
	 */
	public static Long castTolong(String value){
		return isEmpty(value)?null:Long.parseLong(value);
	}
	/**
	 * 
	 * Description: 编码转换<br>
	 * @param strValue
	 * @param readEncode
	 * @param outEncode
	 * @return
	 */
    public static String convertEncode(String strValue,String readEncode,String outEncode){
		
		byte[] strArray =  null;
		try {
			if(readEncode != null )
			{
				strArray = strValue.getBytes(readEncode);		
	        }else{
	        	strArray = strValue.getBytes();
	        }
			if(outEncode == null){
				return new String(strArray);
			}else
			{
			  return 	new String(strArray,outEncode);
			}
			
		} catch (UnsupportedEncodingException e) 
		{
			System.out.println("转换字符异常：传入："+strValue+";"+readEncode+","+outEncode);
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * str格式['22','11','111','2222']
	 * @param str
	 * @return
	 */
	public static List<String> stringToList(String str){
		if(isNotEmpty(str)){
			String s = str.replaceAll("'", "");
			String substring = s.substring(1, s.length() - 1);
			List<String> strings = Arrays.asList(substring.split(","));
			return strings;
		}
		return new ArrayList<>();
	}
}

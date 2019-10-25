package com.fijo.fileservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM");
	private static SimpleDateFormat df4 = new SimpleDateFormat("yyyy");
	
	/**
	 * 获取当前日期时间，返回24小时制
	 * @return
	 */
	public static String getNowDateYMDHMS(){
		String date = df.format(new Date());
		return date;
	}
	public static String getNowDateYM(){
		String date = df3.format(new Date());
		return date;
	}

	/**
	 *
	 * @return
	 */
	public static String getNowDateFirstYM(){
		String date = df4.format(new Date())+"-01";
		return date;
	}
	/**
	 * 获取当前日期时间，返回24小时制
	 * @return
	 */
	public static Date getNowDate(){
		String date = df.format(new Date());
		Date nodwymdate = new Date();
		try {
			nodwymdate = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return nodwymdate;
	}


	/**
	 * str 转date
	 * @param datestr
	 * @return
	 */
	public static Date str2Date(String datestr){
		Date date = new Date();
		try {
			date = df2.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}

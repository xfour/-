package com.xfour.util;

import java.sql.Timestamp;
import java.util.Date;

/**
 *DateUtil这个日期工具类主要是用于java.util.Date类与java.sql.Timestamp 类的互相转换。
 *因为在实体类中日期类型的属性，使用的都是java.util.Date类。
 *而为了在MySQL中的日期格式里保存时间信息
 *必须使用datetime类型的字段
 *而jdbc要获取datetime类型字段的信息
 *需要采用java.sql.Timestamp来获取
 *否则只会保留日期信息，而丢失时间信息。
 * @author square
 *
 */
public class DateUtil {
	
	/*
	 * 将Date格式的时间转为Timestamp格式
	 */
	public static Timestamp d2t(Date date) {
		if(null == date) return null;
		return new Timestamp(date.getTime());
	}
	
	/*
	 * 将Timestamp格式的时间转为Date格式
	 */
	public static Date t2d(Timestamp timestamp) {
		if(null == timestamp) return null;
		return new Date(timestamp.getTime());
	}
}

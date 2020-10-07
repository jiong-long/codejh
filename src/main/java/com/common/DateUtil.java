package com.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期相关工具类
 * 
 * @author wangjinlong
 *
 * @creatTime 2017年7月27日 下午10:46:10
 */
public class DateUtil {
	/**
	 * 日期类型转换成年月日类型
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年7月27日 下午11:04:36
	 * @param date
	 * @return
	 */
	public static String toYMD(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * 日期类型装换成年月日时分秒类型
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年7月27日 下午11:05:26
	 * @param date
	 * @return
	 */
	public static String toYMDHMS(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	public static void main(String[] args) {
		Date a = nextMonthFirstDate(new Date());
		System.out.println(toYMD(a));
		Date b = nextMonthFirstDate(a);
		System.out.println(toYMD(b));
	}

	/**
	 * 取下个月一号
	 * 
	 * @param date
	 * @return
	 */
	public static Date nextMonthFirstDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}
}

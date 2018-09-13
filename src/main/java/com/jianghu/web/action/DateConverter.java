package com.jianghu.web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

/**
 * 重写struts2类型转换
 * 
 * @author wangjinlong
 * 
 * @creatTime 2017年7月25日 下午10:37:37
 */
public class DateConverter extends StrutsTypeConverter {
	// 支持转换的多种日期格式，可增加时间格式
	private final DateFormat[] dataFormat = { new SimpleDateFormat("yyyy年MM月dd日"),
			// 需要时间的话，需要将时间放在上面，否则转换时，遇到没有时间的直接转换就跳过后面所有的，暂时还不知道是怎么匹配上的
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy.MM.dd"), new SimpleDateFormat("yyyy/MM/dd") };

	/**
	 * 将指定格式字符串转换为日期类型
	 */
	@SuppressWarnings("rawtypes")
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		// 获取日期的字符串
		String dateStr = arg1[0];
		// 遍历日期支持格式，进行转换
		if ("".equals(dateStr)) {
			return new Date();
		}
		for (int i = 0; i < dataFormat.length; i++) {
			try {
				return dataFormat[i].parse(dateStr);
			} catch (Exception e) {
				continue;
			}
		}
		// 如果遍历完毕后仍没有转换成功，表示出现转换异常
		throw new TypeConversionException();
	}

	/**
	 * 将日期转换为指定的字符串格式
	 */
	@SuppressWarnings("rawtypes")
	public String convertToString(Map arg0, Object arg1) {
		Date date = (Date) arg1;
		// 输出格式是yyyy-MM-dd
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

}

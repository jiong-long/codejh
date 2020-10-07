package com.common;

import org.apache.log4j.Logger;

/**
 * Log4J工具类
 * 
 * @author wangjinlong
 *
 */
public class Log {
	public static Logger logger = Logger.getLogger(Log.class);

	public static void info(Object obj) {
		logger.info(obj);
	}

	public static void error(Object obj) {
		logger.error(obj);
	}

	public static void debug(Object obj) {
		logger.debug(obj);
	}

}

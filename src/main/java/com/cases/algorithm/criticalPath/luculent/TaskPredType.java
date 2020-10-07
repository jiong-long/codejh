package com.cases.algorithm.criticalPath.luculent;

/**
 * 逻辑关系类型
 * 
 * @author wangjinlong
 *
 */
public class TaskPredType {
	//开始-开始
	public static String S2S = "Start to Start";
	//开始-结束
	public static String S2F = "Start to Finish";
	//结束-开始
	public static String F2S = "Finish to Start";
	//结束-结束
	public static String F2F = "Finish to Finish";
}

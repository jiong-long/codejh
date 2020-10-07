package com.cases.algorithm.criticalPath.luculent;

/**
 * 作业
 * 
 * @author wangjinlong
 *
 */
public class Task {
	private String task_id;// 作业主键
	private Double hr_cnt;// 作业工期
	private TaskPred taskPred;// 第一个逻辑关系
	private Integer inDegree;// 入度
	private Double ve;// 最早开始时间
	private Double vl;// 最晚结束时间

	public Task() {
		super();
	}

	public Task(String task_id, Double hr_cnt, Integer inDegree, TaskPred taskPred, Double ve, Double vl) {
		super();
		this.task_id = task_id;
		this.hr_cnt = hr_cnt;
		this.inDegree = inDegree;
		this.taskPred = taskPred;
		this.ve = ve;
		this.vl = vl;
	}

	public Double getVe() {
		return ve;
	}

	public void setVe(Double ve) {
		this.ve = ve;
	}

	public Double getVl() {
		return vl;
	}

	public void setVl(Double vl) {
		this.vl = vl;
	}

	public Integer getInDegree() {
		return inDegree;
	}

	public void setInDegree(Integer inDegree) {
		this.inDegree = inDegree;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public Double getHr_cnt() {
		return hr_cnt;
	}

	public void setHr_cnt(Double hr_cnt) {
		this.hr_cnt = hr_cnt;
	}

	public TaskPred getTaskPred() {
		return taskPred;
	}

	public void setTaskPred(TaskPred taskPred) {
		this.taskPred = taskPred;
	}
}

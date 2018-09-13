package com.jianghu.other.algorithm.criticalPath.luculent;

/**
 * 逻辑关系
 * 
 * @author wangjinlong
 *
 */
public class TaskPred {
	private String task_pred_id;//主键
	private String task_id;//作业主键
	private String pred_task_id;//关联作业主键
	private String pred_type;//类型
	private Double hr_cnt;//延期
	private TaskPred nextTaskPred;//链式逻辑关系，同一个task开始的逻辑关系组成的链式结构

	public TaskPred() {
		super();
	}

	public TaskPred(String task_pred_id, String task_id, String pred_task_id, String pred_type, Double hr_cnt, TaskPred nextTaskPred) {
		super();
		this.task_pred_id = task_pred_id;
		this.task_id = task_id;
		this.pred_task_id = pred_task_id;
		this.pred_type = pred_type;
		this.hr_cnt = hr_cnt;
		this.nextTaskPred = nextTaskPred;
	}

	public String getTask_pred_id() {
		return task_pred_id;
	}

	public void setTask_pred_id(String task_pred_id) {
		this.task_pred_id = task_pred_id;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getPred_task_id() {
		return pred_task_id;
	}

	public void setPred_task_id(String pred_task_id) {
		this.pred_task_id = pred_task_id;
	}

	public String getPred_type() {
		return pred_type;
	}

	public void setPred_type(String pred_type) {
		this.pred_type = pred_type;
	}

	public Double getHr_cnt() {
		return hr_cnt;
	}

	public void setHr_cnt(Double hr_cnt) {
		this.hr_cnt = hr_cnt;
	}

	public TaskPred getNextTaskPred() {
		return nextTaskPred;
	}

	public void setNextTaskPred(TaskPred nextTaskPred) {
		this.nextTaskPred = nextTaskPred;
	}
}

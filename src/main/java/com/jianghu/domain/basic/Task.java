package com.jianghu.domain.basic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 任务
 * 
 * @author jinlong
 *
 */
@Entity
@Table(name = "bc_task")
public class Task {
	//主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long task_id;
	//任务内容
	@Column(length = 500)
	private String task_content;
	//是否完成
	@Column(length = 3)
	private String task_sta;
	//是否有产出物
	@Column(length = 3)
	private String task_res;
	//产出物地址
	@Column(length = 50)
	private String task_url;
	//开始日期
	private Date begin_dtm;
	//完成日期
	private Date end_dtm;

	public Date getBegin_dtm() {
		return begin_dtm;
	}

	public void setBegin_dtm(Date begin_dtm) {
		this.begin_dtm = begin_dtm;
	}

	public Date getEnd_dtm() {
		return end_dtm;
	}

	public void setEnd_dtm(Date end_dtm) {
		this.end_dtm = end_dtm;
	}

	public long getTask_id() {
		return task_id;
	}

	public void setTask_id(long task_id) {
		this.task_id = task_id;
	}

	public String getTask_content() {
		return task_content;
	}

	public void setTask_content(String task_content) {
		this.task_content = task_content;
	}

	public String getTask_sta() {
		return task_sta;
	}

	public void setTask_sta(String task_sta) {
		this.task_sta = task_sta;
	}

	public String getTask_res() {
		return task_res;
	}

	public void setTask_res(String task_res) {
		this.task_res = task_res;
	}

	public String getTask_url() {
		return task_url;
	}

	public void setTask_url(String task_url) {
		this.task_url = task_url;
	}

}

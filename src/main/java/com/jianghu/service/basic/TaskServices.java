package com.jianghu.service.basic;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.jianghu.domain.basic.Task;

/**
 * 任务services接口
 * 
 * @author jinlong
 * 
 */
public interface TaskServices {
	public String save(Task task);

	public void update(Task task);

	public void delete(String task_id);

	public Task findById(String task_id);

	public List<Task> findByCriteria(DetachedCriteria criteria);
}

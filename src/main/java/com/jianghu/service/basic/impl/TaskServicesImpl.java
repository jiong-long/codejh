package com.jianghu.service.basic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianghu.dao.GenericDAO;
import com.jianghu.domain.basic.Task;
import com.jianghu.service.basic.TaskServices;

/**
 * 任务services实现
 * 
 * @author jinlong
 * 
 */
@Service("taskServices")
@Transactional
public class TaskServicesImpl implements TaskServices {

	@Resource(name = "taskDao")
	private GenericDAO<Task> taskDao;

	@Override
	public String save(Task task) {
		return taskDao.save(task) + "";
	}

	@Override
	public void update(Task task) {
		taskDao.update(task);
	}

	@Override
	public Task findById(String task_id) {
		return taskDao.findById(Task.class, task_id);
	}

	@Override
	public List<Task> findByCriteria(DetachedCriteria criteria) {
		return taskDao.findByCriteria(criteria);
	}

	@Override
	public void delete(String task_id) {
		Task task = new Task();
		task.setTask_id(Long.parseLong(task_id));
		taskDao.delete(task);
	}

}

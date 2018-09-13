package com.jianghu.other.algorithm.criticalPath.luculent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.jianghu.core.Database;
import com.jianghu.core.Tools;

/**
 * 关键路径
 * 
 * @author wangjinlong
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		long begin = System.currentTimeMillis();

		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = Tools.getDatabase();

			// 获取最晚结束的task所有相关的task主键
			String getLastTaskSql = "select task_id from task where target_end_date = (select max(target_end_date) from task)";
			rs = Database.executeQuery(connection, getLastTaskSql);
			Set<String> ketIdSet = new HashSet<String>();

			Set<String> lastTaskIdArr = new HashSet<String>();
			while (rs.next()) {
				lastTaskIdArr.add(rs.getString("task_id"));
			}
			AdjacencyList.getKeyTaskSet(connection, ketIdSet, lastTaskIdArr);

			// 查询所有的task
			String getTaskSql = "select task_id,ifnull(target_drtn_hr_cnt,0) hr_cnt from task";
			rs.close();
			rs = Database.executeQuery(connection, getTaskSql);
			// 为了通过task_id快速获取task对象，使用map存储
			Map<String, Task> taskMap = new HashMap<String, Task>();
			// task对象集合
			List<Task> taskList = new ArrayList<Task>();
			while (rs.next()) {
				String task_id = rs.getString("task_id");
				if (!ketIdSet.contains(task_id)) {
					continue;
				}
				Double hr_cnt = rs.getDouble("hr_cnt");
				Task task = new Task(task_id, hr_cnt, 0, null, 0D, 0D);
				taskMap.put(task_id, task);
				taskList.add(task);
			}

			// 用于判断taskpred是否为垃圾数据
			Set<String> taskIdSet = taskMap.keySet();
			// Task设置TaskPred后的的存储对象
			Map<String, Task> taskMapTemp = new HashMap<String, Task>();
			// 查询所有的taskpred对象
			List<TaskPred> taskPredList = new ArrayList<TaskPred>();
			String getTaskPredSql = "select task_pred_id,task_id,pred_task_id,pred_type,ifnull(lag_hr_cnt,0) hr_cnt from taskpred order by task_id";
			rs.close();
			rs = Database.executeQuery(connection, getTaskPredSql);
			Task oldTask = null;
			TaskPred oldTaskPred = null;
			while (rs.next()) {
				String task_pred_id = rs.getString("task_pred_id");
				String task_id = rs.getString("task_id");
				if (!ketIdSet.contains(task_id)) {
					continue;
				}
				String pred_task_id = rs.getString("pred_task_id");
				String pred_type = rs.getString("pred_type");
				Double hr_cnt = rs.getDouble("hr_cnt");

				if (!taskIdSet.contains(task_id)) {// 垃圾数据
					continue;
				}

				Task currTask = taskMap.get(task_id);// 该taskpred对应的task

				if (oldTask == null) {// 第一次执行，讲当前task赋值给上一个task
					oldTask = currTask;
				}

				if (!task_id.equals(oldTask.getTask_id())) {// 不属于同一task的taskpred
					oldTaskPred = null;
				}

				TaskPred taskPred = new TaskPred(task_pred_id, task_id, pred_task_id, pred_type, hr_cnt, oldTaskPred);

				// 将封装好的对象放到set中
				taskPredList.add(taskPred);

				currTask.setTaskPred(taskPred);
				// 敲黑板，重点！！！ 使用map就不需要判断是否存在
				taskMapTemp.put(task_id, currTask);

				oldTaskPred = taskPred;
				oldTask = currTask;
			}

			// 校验对象是否封装正确
			// AdjacencyList.validateObj(taskList);

			// step1:计算所有事件（节点）的入度
			AdjacencyList.getInDegree(taskList, taskMap);

			// 关键点：最晚开始时间的遍历顺序与最早开始时间的遍历顺序相反
			Stack<Task> importStack = new Stack<Task>();

			// step2:计算ve（事件（节点）的最早开始时间）
			AdjacencyList.toplogicalOrder(taskList, importStack, taskMap);

			// step3: 计算vl（事件（节点）的最晚结束时间）
			AdjacencyList.lastHappen(taskList, importStack, taskMap);

			// List<Object[]> list = new ArrayList<Object[]>();
			for (Task task : taskList) {
				if (task.getVe() + task.getHr_cnt() == task.getVl()) {
					System.out.println(
							task.getTask_id() + "是关键路径:" + task.getVe() + "+" + task.getHr_cnt() + "=" + task.getVl());
				} else {
					System.out.println(
							task.getTask_id() + "不是关键路径" + task.getVe() + "+" + task.getHr_cnt() + "=" + task.getVl());
				}
				// list.add(new Object[]{(task.getVl() - task.getVe() -
				// task.getHr_cnt()),task.getTask_id()});
			}

			// String updateSql = "update task set total_float_hr_cnt1 = ? where
			// task_id = ?";
			// Database.executeUpdateBatch(connection, updateSql, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Database.closeresouceAll(rs);
		}

		long end = System.currentTimeMillis();
		System.out.println("总耗时：" + (end - begin));
	}
}

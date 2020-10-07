package com.cases.algorithm.criticalPath.luculent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.jianghu.dao.Database;

public class AdjacencyList {

	/**
	 * 获取与lastTaskId相关联的所有的taskid
	 * 
	 * @param ketIdSet
	 * @param lastTaskId
	 * @throws Exception
	 */
	public static void getKeyTaskSet(Connection connection, Set<String> ketIdSet, Set<String> lastTaskIdArr) throws Exception {
		String whereSql = "";
		int i = 1;
		int maxLength = lastTaskIdArr.size();
		Set<String> whereSqlList = new HashSet<String>();
		for (String lastTaskId : lastTaskIdArr) {
			if (!ketIdSet.contains(lastTaskId)) {
				ketIdSet.add(lastTaskId);
				if (i % 999 == 0 || i == maxLength) {
					if (!"".equals(whereSql)) {
						whereSqlList.add(whereSql);
						whereSql = "";
					}
				}
				whereSql += "," + lastTaskId;
				i++;
			} else {
				maxLength--;
			}
		}
		//防止最后一个元素已存在，导致whereSql没有加上
		if (!"".equals(whereSql)) {
			whereSqlList.add(whereSql);
		}

		String maxWhereSql = "";
		for (String limitSql : whereSqlList) {
			limitSql = limitSql.substring(1).replaceAll(",", "','");
			maxWhereSql += "or pred_task_id in ('" + limitSql + "') or task_id in ('" + limitSql + "')";
		}

		if (!"".equals(maxWhereSql)) {
			maxWhereSql = maxWhereSql.substring(2);
			String getOtherTaskId = "select task_id,pred_task_id from taskpred where " + maxWhereSql;
			ResultSet rs = Database.executeQuery(connection, getOtherTaskId);
			lastTaskIdArr.clear();
			while (rs.next()) {
				lastTaskIdArr.add(rs.getString("task_id"));
				lastTaskIdArr.add(rs.getString("pred_task_id"));
			}
			Database.closeresouce(rs);
			//Database.closeresouce(rs.getStatement());
			getKeyTaskSet(connection, ketIdSet, lastTaskIdArr);
		}
	}

	/**
	 * 获得每个作业的入度。作业的入度就是该作业需要遍历的次数
	 * 
	 * @param hns
	 * @return
	 */
	public static void getInDegree(List<Task> taskList, Map<String, Task> taskMapStatic) {
		//求每个节点的入度 ，有多少边指向这个节点  
		for (int i = 0; i < taskList.size(); i++) {
			Task task = taskList.get(i);
			for (TaskPred taskPred = task.getTaskPred(); taskPred != null; taskPred = taskPred.getNextTaskPred()) {
				String nextTaskId = taskPred.getPred_task_id();
				Task nextTask = taskMapStatic.get(nextTaskId);
				nextTask.setInDegree(nextTask.getInDegree() + 1);
			}
		}
	}

	/**
	 * 计算作业的最早开始时间
	 * 
	 * @param taskList
	 * @param importStack
	 * @return
	 * @throws Exception
	 */
	public static void toplogicalOrder(List<Task> taskList, Stack<Task> importStack, Map<String, Task> taskMapStatic) throws Exception {
		//所有的源点（开始节点）
		Stack<Task> start = new Stack<Task>();
		//入度为0的作业保存在栈中
		for (Task task : taskList) {
			if (task.getInDegree() == 0) {
				start.push(task);//将元素压入栈中
			}
		}

		int count = 0;

		Double maxVl = 0D;//最大的最晚结束时间

		while (!start.isEmpty()) {
			Task task = start.pop();//当前元素出栈  
			importStack.push(task);//遍历顺序
			count++;
			
			//当作业没有逻辑关系时，最晚=最早+工期
			if(task.getTaskPred() == null){
				maxVl = task.getVe() + task.getHr_cnt();
			}

			for (TaskPred taskPred = task.getTaskPred(); taskPred != null; taskPred = taskPred.getNextTaskPred()) {
				String currTaskId = taskPred.getTask_id();
				Task currTask = taskMapStatic.get(currTaskId);

				String nextTaskId = taskPred.getPred_task_id();
				Task nextTask = taskMapStatic.get(nextTaskId);
				Double nextVe = nextTask.getVe();

				//遍历一次，则下一个作业的入度减1
				Integer nextInDegree = nextTask.getInDegree() - 1;
				nextTask.setInDegree(nextInDegree);
				if (nextInDegree == 0) {//入度为0，则该节点遍历完成
					start.push(nextTask);
				}

				String type = taskPred.getPred_type();

				Double tempVe = null;
				if (TaskPredType.S2S.equals(type)) {//开始-开始
					//下一节点的最早开始时间  = max(当前节点的最早开始时间  + 逻辑关系的时间 )
					tempVe = currTask.getVe() + taskPred.getHr_cnt();
				} else if (TaskPredType.S2F.equals(type)) {//开始-结束
					//下一节点的最早开始时间  = max(当前节点的最早开始时间  + 逻辑关系的时间  - 下一节点的工期   )
					tempVe = currTask.getVe() + taskPred.getHr_cnt() - nextTask.getHr_cnt();
				} else if (TaskPredType.F2F.equals(type)) {//结束-结束
					//下一节点的最早开始时间  = max(当前节点的最早开始时间  + 当前节点的工期  + 逻辑关系的时间  - 下一节点的工期   )
					tempVe = currTask.getVe() + currTask.getHr_cnt() + taskPred.getHr_cnt() - nextTask.getHr_cnt();
				} else {//结束-开始
						//下一节点的最早开始时间  = max(当前节点的最早开始时间  + 逻辑关系的时间  + 当前节点的工期  )
					tempVe = currTask.getVe() + taskPred.getHr_cnt() + currTask.getHr_cnt();
				}

				if (tempVe > nextVe) {
					nextTask.setVe(tempVe);
					//当前作业的最早开始时间 + 当前作业的工期 = 当前作业的最晚完成时间
					Double tempMax = tempVe + nextTask.getHr_cnt();
					if (tempMax > maxVl) {
						maxVl = tempMax;
					}
				}
			}
		}

		for (Task task : taskList) {
			task.setVl(maxVl);
		}

		if (count < taskList.size()) {
			throw new Exception("有环！");
		}
	}

	/**
	 * 计算作业的最晚开始时间
	 * 
	 * @param hns
	 * @param ve
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public static void lastHappen(List<Task> taskList, Stack<Task> importStack, Map<String, Task> taskMapStatic) throws Exception {
		Task task = importStack.pop();//最后一个节点无需计算
		while (!importStack.isEmpty()) {
			task = importStack.pop();
			for (TaskPred taskPred = task.getTaskPred(); taskPred != null; taskPred = taskPred.getNextTaskPred()) {
				String currTaskId = taskPred.getTask_id();
				Task currTask = taskMapStatic.get(currTaskId);
				Double currVl = currTask.getVl();

				String nextTaskId = taskPred.getPred_task_id();
				Task nextTask = taskMapStatic.get(nextTaskId);

				String type = taskPred.getPred_type();

				Double tempVl = null;
				if (TaskPredType.S2S.equals(type)) {//开始-开始
					//当前节点的最晚结束时间  = min(下一节点的最晚结束时间  - 下一节点的工期 - 逻辑关系的时间  + 当前节点工期)
					tempVl = nextTask.getVl() - nextTask.getHr_cnt() - taskPred.getHr_cnt() + currTask.getHr_cnt();
				} else if (TaskPredType.S2F.equals(type)) {//开始-结束
					//当前节点的最晚结束时间  = min(下一节点的最晚结束时间  - 逻辑关系的时间  + 当前节点工期)
					tempVl = nextTask.getVl() - taskPred.getHr_cnt() + currTask.getHr_cnt();
				} else if (TaskPredType.F2F.equals(type)) {//结束-结束
					//当前节点的最晚结束时间  = min(下一节点的最晚结束时间   - 逻辑关系的时间)
					tempVl = nextTask.getVl() - taskPred.getHr_cnt();
				} else {//结束-开始
						//当前节点的最晚结束时间   = min(下一节点的最晚结束时间  - 下一节点的工期  - 逻辑关系的时间)
					tempVl = nextTask.getVl() - nextTask.getHr_cnt() - taskPred.getHr_cnt();
				}

				if (tempVl < currVl) {
					currTask.setVl(tempVl);
				}
			}
		}
	}

	/**
	 * 验证对象是否封装正确
	 * 
	 * @param taskList
	 * @throws Exception
	 */
	public static void validateObj(List<Task> taskList) throws Exception {
		for (Task task : taskList) {
			String task_id = task.getTask_id();
			int i = 0;
			for (TaskPred taskPred = task.getTaskPred(); taskPred != null; taskPred = taskPred.getNextTaskPred()) {
				i++;
			}
			String count = Database.getUniqueStringValue("select count(1) from taskpred where task_id = ?", task_id);
			if (!count.equals(i + "")) {
				System.out.println(task_id + "：" + i + "=" + count);
				System.out.println();
				throw new Exception("对象封装错误！！！");
			}
		}
	}
}

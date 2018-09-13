package com.jianghu.web.springmvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jianghu.core.Tools;
import com.jianghu.core.tools.DateUtil;
import com.jianghu.domain.basic.Task;
import com.jianghu.service.basic.TaskServices;

/**
 * 任务相关action
 * 
 * @author jinlong
 *
 */
@Controller
public class TaskAction {

	@Autowired
	private TaskServices taskServices;

	/**
	 * 保存方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("taskAction/save.htm")
	public String save(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		Task task = new Task();

		//TODO 前台自动注入bean
		String task_id = request.getParameter("task_id");
		String task_content = request.getParameter("task_content");
		String task_sta = request.getParameter("task_sta");
		String task_res = request.getParameter("task_res");
		String task_url = request.getParameter("task_url");
		String begin_dtm_str = Tools.null2Empty(request.getParameter("begin_dtm"));
		String end_dtm_str = Tools.null2Empty(request.getParameter("end_dtm"));
		DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		if ("".equals(begin_dtm_str)) {
			task.setBegin_dtm(new Date());
		} else {
			Date begin_dtm = df.parse(begin_dtm_str);
			task.setBegin_dtm(begin_dtm);
		}

		if ("".equals(end_dtm_str)) {
			if ("on".equals(task_sta)) {//完成
				task.setEnd_dtm(new Date());
			} else {
				task.setEnd_dtm(null);
			}
		} else {
			task.setEnd_dtm(df.parse(end_dtm_str));
		}

		task.setTask_content(task_content);
		task.setTask_sta(task_sta);
		task.setTask_res(task_res);
		task.setTask_url(task_url);

		if ("".equals(task_id)) {//新建
			task_id = taskServices.save(task);
		} else {
			task.setTask_id(Long.parseLong(task_id));
			taskServices.update(task);
		}

		return "redirect:/taskAction/getTaskInfo.htm?task_id=" + task_id;
	}

	@RequestMapping("taskAction/getTaskInfo.htm")
	public ModelAndView getTaskInfo(String task_id) {
		ModelAndView mv = new ModelAndView("task/task_add");
		if (task_id != null && !"".equals(task_id)) {
			Task task = taskServices.findById(task_id);
			//TODO model与mv封装参数的区别
			//model.addAttribute("task", task);
			mv.addObject("task", task);
		}
		return mv;
	}

	@RequestMapping("taskAction/deleteTask.htm")
	public ModelAndView deleteTask(String task_id) {
		ModelAndView mv = new ModelAndView("task/task_list");
		if (task_id != null && !"".equals(task_id)) {
			taskServices.delete(task_id);
		}
		return mv;
	}

	@RequestMapping("taskAction/getAll.htm")
	@ResponseBody
	public String getAll(HttpServletRequest request, HttpServletResponse response) throws JSONException {
		String taskName = Tools.null2Empty(request.getParameter("taskName"));

		DetachedCriteria criteria = DetachedCriteria.forClass(Task.class);
		if (!"".equals(taskName)) {
			criteria.add(Restrictions.like("task_content", "%" + taskName + "%"));
		}

		criteria.addOrder(Order.asc("task_sta"));
		criteria.addOrder(Order.asc("begin_dtm"));
		List<Task> listTask = taskServices.findByCriteria(criteria);

		int totleRows = listTask.size();

		JSONObject obj = new JSONObject();
		obj.put("total", totleRows);

		// 分页
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		int startRow = (page - 1) * rows;
		int endRow = page * rows > totleRows ? totleRows : page * rows;

		listTask = listTask.subList(startRow, endRow);

		JSONArray arr = new JSONArray();
		for (Task task : listTask) {
			JSONObject roleObj = new JSONObject();
			roleObj.put("task_id", task.getTask_id());
			String taskSta = Tools.null2Empty(task.getTask_sta());
			if ("".equals(taskSta)) {
				taskSta = "未完成";
			} else {
				taskSta = "完成";
			}
			roleObj.put("task_sta", taskSta);
			roleObj.put("task_content", task.getTask_content());
			roleObj.put("begin_dtm", DateUtil.toY_M_D_H_M_S(task.getBegin_dtm()));
			roleObj.put("end_dtm", DateUtil.toY_M_D_H_M_S(task.getEnd_dtm()));
			arr.put(roleObj);
		}
		obj.put("rows", arr);

		return obj.toString();
	}
}

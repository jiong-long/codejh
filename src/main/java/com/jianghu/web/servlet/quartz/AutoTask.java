package com.jianghu.web.servlet.quartz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AutoTask extends HttpServlet {
	private static final long serialVersionUID = -1158221404164959136L;

	public void init() throws ServletException {
		TaskCronTriggerRunner cronTriggerRunner = new TaskCronTriggerRunner();
		cronTriggerRunner.task();
	}
}

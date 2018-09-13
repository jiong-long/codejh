package com.jianghu.web.servlet.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class TaskCronTriggerRunner {
	public void task() {
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			JobDetail jobDetail = JobBuilder.newJob(AutoTaskJob.class).build();
			CronTrigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 0 0-23 * * ?")).build();
			scheduler.scheduleJob(jobDetail, cronTrigger);
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//  过时
	//	public void task() {
	//		try {
	//			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	//			Scheduler scheduler = schedulerFactory.getScheduler();
	//			JobDetail jobDetail = new JobDetail("jobDetail2", "jobDetailGroup2", AutoDownLoadTaskJob.class);
	//			CronTrigger cronTrigger = new CronTrigger("cronTrigger", "triggerGroup2");
	//			CronExpression expression = new CronExpression("* * * 10,25 * ?");
	//			cronTrigger.setCronExpression(expression);
	//			scheduler.scheduleJob(jobDetail, cronTrigger);
	//			scheduler.start();
	//		} catch (Exception e) {
	//			Log.error(e);
	//		}
	//	}
}

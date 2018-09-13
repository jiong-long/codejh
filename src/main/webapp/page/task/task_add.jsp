<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.jianghu.domain.basic.Task"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/jiong/js/layui/layui/css/layui.css" media="all">
<style type="text/css">
	.layui-form-switch{
		width: 60px;
	}
	.layui-form-switch em{
		width: 44px;
	}
	.layui-form-onswitch i{
		left: 50px;
	}
	.layui-inline{
		width: 390px;
	}
</style>
<%
	//TODO 无法使用EL表达式
	Task task = (Task)request.getAttribute("task");
	String task_id = "";
	String task_content = "";
	String task_sta_ed = "";
	String task_res_ed = "";
	String task_url = "";
	String begin_dtm = "";
	String end_dtm = "";
	if(task != null){
		DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		task_id = task.getTask_id() + "";
		task_content = task.getTask_content();
		if("on".equals(task.getTask_sta())){
			task_sta_ed = "checked";
		}
		if("on".equals(task.getTask_res())){
			task_res_ed = "checked";
		}
		task_url = task.getTask_url();
		Date begin = task.getBegin_dtm();
		if(begin != null){
			begin_dtm = df.format(begin);
		}
		Date end = task.getEnd_dtm();
		if(end != null){
			end_dtm = df.format(end);
		}
	}
%>

<title>增加任务</title>
</head>
<body style="width: 60%;margin-left: auto;margin-right: auto;">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		<legend>增加任务</legend>
	</fieldset>

	<form class="layui-form" id="taskForm" method="post" action="/jiong/taskAction/save.htm">
		<input type="text" name="task_id" style="display: none;" value="<%=task_id %>" id="task_id">
		
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">任务内容</label>
			<div class="layui-input-block">
				<textarea class="layui-textarea layui-hide" name="task_content"
					lay-verify="content" id="LAY_demo_editor">
					<%=task_content %>	
				</textarea>
			</div>
		</div>
		
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">完成状态</label>
				<div class="layui-input-block">
					<input type="checkbox" name="task_sta" lay-skin="switch"
						lay-text="已完成|未完成" <%=task_sta_ed %>>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">成果</label>
				<div class="layui-input-block">
					<input type="checkbox" name="task_res" lay-skin="switch"
						lay-filter="switchTest" lay-text="有|无" <%=task_res_ed %>>
				</div>
			</div>
		</div>
	
		<div class="layui-form-item" id="url_link" style='display: <%=(task_res_ed == "checked" ? "": "none") %>;'>
			<label class="layui-form-label">成果地址</label>
			<div class="layui-input-block">
				<input type="text" name="task_url" lay-verify="title" value="<%=task_url %>" id="task_url";
					autocomplete="off" placeholder="请输入链接" class="layui-input" style="width: 90%;float: left;">
				<img src="/jiong/images/toDetail.png" onclick="gotoDemo()" title="查看Demo" 
					style="cursor: pointer;float: left;margin-top: 2px;margin-left: 10px;"/>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">下发日期</label>
				<div class="layui-input-block">
					<input type="text" name="begin_dtm"	autocomplete="off" class="layui-input" value="<%=begin_dtm %>" readonly="readonly">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">完成日期</label>
				<div class="layui-input-block">
					<input type="text" name="end_dtm"	autocomplete="off" class="layui-input" value="<%=end_dtm %>" readonly="readonly">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
				<button class="layui-btn layui-btn-primary" onclick="deleteTask()" type="button">删除</button>
			</div>
		</div>
	</form>

	<script src="/jiong/js/layui/layui/layui.js" charset="utf-8"></script>
	<script src="/jiong/js/jQuery/jquery.min.js" charset="utf-8"></script>
	<script>
		layui.use(['form', 'layedit', 'laydate'], function(){
		  var form = layui.form,
			  layer = layui.layer,
			  layedit = layui.layedit,
			  laydate = layui.laydate;
		  
		  //创建一个编辑器
		  var editIndex = layedit.build('LAY_demo_editor');
		 
		  //editIndex.getContent(index) //获取html
		  //editIndex.getText(index) // 获取纯文本
		  
		  //自定义验证规则
		  form.verify({
		    content: function(value){
		      layedit.sync(editIndex);
		    }
		  });
		  
		  //监听指定开关
		  form.on('switch(switchTest)', function(data){
		  	$("#url_link").toggle();
		  });
		  
		  //监听提交
		  form.on('submit(demo1)', function(data){
		  });
		});
		
		function gotoDemo(){
			var toHtml = $("#task_url").val();
			//打开一个新窗口
			window.open(toHtml);  
		}
		
		function deleteTask(){
			var task_id = $("#task_id").val();
			window.location.href = "/jiong/taskAction/deleteTask.htm?task_id="+task_id;
		}
</script>
</body>
</html>
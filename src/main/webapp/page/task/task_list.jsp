<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务列表</title>
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/demo.css">
	<script type="text/javascript" src="/jiong/js/easyUI/jquery.min.js"></script>
	<script type="text/javascript" src="/jiong/js/easyUI/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/jiong/js/easyUI/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="/jiong/js/layui/layer/layer.js"></script>
	<style type="text/css">
		.datagrid{
			margin-left: auto;
			margin-right: auto;
		}
	</style>
</head>
<body>
	<div style="margin:20px 0;"></div>
	
	<table id="dg" class="easyui-datagrid" title="任务列表" style="width:735px;height:auto;"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: false,
				toolbar: '#tb',
				url: '/jiong/taskAction/getAll.htm',
				method: 'get',
				pagination:true,
				onDblClickRow:onDblClickRow,
				rownumbers:true,
				rowStyler: function(index,row){
					if (row.task_sta == '未完成'){
						return 'font-weight:bold;';
					}
				}
			">
		<thead>
			<tr>
				<th data-options="field:'task_id',width:0,hidden:'true'">主键</th>
				<th data-options="field:'task_sta',width:80">是否完成</th>
				<th data-options="field:'task_content',width:300">任务内容</th>
				<th data-options="field:'begin_dtm',width:160">开始日期</th>
				<th data-options="field:'end_dtm',width:160">完成日期</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="height:30px;padding-left: 20px;">
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>
			<span style="margin-left: 20px;">
				任务内容: 
				<input class="easyui-textbox" type="text" name="taskName" style="width: 100px;" id="taskName" />
			</span>
			<a onclick="doSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" style="margin-left: 10px;">搜索</a>
		</div>
	</div>

	<script type="text/javascript">
		function doSearch(){
			$('#dg').datagrid('load',{
				taskName: $("#taskName").val()
			});
		}
	
		function onDblClickRow(rowIndex,rowData){
			window.open("/jiong/taskAction/getTaskInfo.htm?task_id="+rowData.task_id);   
		}
		
		
		function append(){
			window.open("/jiong/page/task/task_add.jsp");   
		}
	</script>
</body>
</html>
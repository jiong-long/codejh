<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>序列管理</title>
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/themes/icon.css">
	<script type="text/javascript" src="/jiong/js/easyUI/jquery.min.js"></script>
	<script type="text/javascript" src="/jiong/js/core/jianghu.js"></script>
	<script type="text/javascript" src="/jiong/js/easyUI/jquery.easyui.min.js"></script>
	<!-- 必须在jquery.easyui.min.js加载之后加载 -->
	<script type="text/javascript" src="/jiong/js/easyUI/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<center>
		<h2>序列管理</h2>
		<div style="margin:20px 0;"></div>
		<table id="seq_tab" class="easyui-datagrid" title="序列列表" style="width:400px;height:300px;"
				data-options="fitColumns:true,rownumbers:true,singleSelect:true,collapsible:true,url:'seq_getAllSeq.do',method:'post'">
			<thead>
				<tr>
					<th data-options="field:'seq_nam',width:200">名称</th>
					<th data-options="field:'curr_val',width:120">当前值</th>
				</tr>
			</thead>
		</table>
	</center>
	<script type="text/javascript">
		
	</script>
</body>
</html>
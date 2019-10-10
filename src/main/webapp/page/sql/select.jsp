<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查询</title>
<link rel="stylesheet" href="/jiong/js/layui/layui/css/layui.css" media="all">
</head>
<body>
	<div class="layui-form-item layui-form-text" style="margin-top: 20px;margin-right: 80px;">
    	<label class="layui-form-label">请输入SQL</label>
    	<div class="layui-input-block">
      		<textarea id="sql" name="desc" placeholder="请输入SQL" class="layui-textarea"></textarea>
    	</div>
  	</div>
  	<div class="layui-form-item">
    	<div class="layui-input-block">
      		<button class="layui-btn" lay-submit lay-filter="formDemo" onclick="query()">查询</button>
    	</div>
  	</div>

	<table class="layui-hide" id="test" lay-filter="test"></table>

	<script type="text/javascript" src="/jiong/js/BootStrap/jquery.min.js"></script>
	<script src="/jiong/js/layui/layui/layui.js" charset="utf-8"></script>
	<script>
		layui.use([ 'layer' ], function() {
			layer = layui.layer; // 独立版的layer无需执行这一句
		});
		
		var cols1 = "";
	
		function query(){
			var querySql = encodeURIComponent($("#sql").val());
			$.ajax({
		        type: "POST",
		        url:"/jiong/sqlAction/column.htm",
		        data:"sql=" + querySql,
		        datatype:"json",
		        error: function(msg) {
		        	layer.msg("出错了");
		        },
		        success: function(data) {
		        	layui.use('table', function(){
			  		  	var table = layui.table;
			  		  	table.render({
			  		    	elem: '#test'
			  		    	,cols: [eval(data)]
			  		    	,url:'/jiong/sqlAction/select.htm?sql=' + querySql
			  		    	,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
			  		    	,defaultToolbar: ['filter', 'exports']
			  		    	,title: '用户数据表'
			  		  	});
			  		});
		        }        
		    });
		}
	</script>
</body>
</html>
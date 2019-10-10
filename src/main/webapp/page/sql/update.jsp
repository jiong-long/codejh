<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>更新</title>
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
      		<button class="layui-btn" lay-submit lay-filter="formDemo" onclick="query()">更新</button>
    	</div>
  	</div>

	<script type="text/javascript" src="/jiong/js/BootStrap/jquery.min.js"></script>
	<script src="/jiong/js/layui/layui/layui.js" charset="utf-8"></script>
	<script>
		layui.use([ 'layer' ], function() {
			layer = layui.layer; // 独立版的layer无需执行这一句
		});
	
		function query(){
			var updateSql = encodeURIComponent($("#sql").val());
			$.ajax({
		        type: "POST",
		        url:"/jiong/sqlAction/update.htm",
		        data:"sql=" + updateSql,
		        datatype:"json",
		        error: function(msg) {
		        	layer.msg("出错了");
		        },
		        success: function(data) {
		        	layer.msg("成功：影响了 " + data + " 行");
		        }        
		    });
		}
	</script>
</body>
</html>
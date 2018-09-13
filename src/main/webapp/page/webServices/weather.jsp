<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>天气预报</title>
  <link rel="stylesheet" href="/jiong/js/layui/layui/css/layui.css"  media="all">
  <style type="text/css">
  .layui-form{
  	margin-left: auto;
  	margin-right: auto;
  }
  </style>
  <%
  	String localtion = request.getParameter("localtion");
  %>
</head>
<body>
<table class="layui-table" lay-data="{width:717, url:'wea_getWeatherInfo.do', even:true, where:{localtion:'<%=localtion %>'}}">
  <thead>
    <tr>
      <th lay-data="{field:'date', width:100, sort: true}">日期</th>
      <th lay-data="{field:'weather', width:120}">天气</th>
      <th lay-data="{field:'icon', width:90, templet: '#iconTpl'}">图例</th>
      <th lay-data="{field:'mintemper', width:100}">最低温度</th>
      <th lay-data="{field:'maxtemper', width:120, sort: true}">最高温度</th>
      <th lay-data="{field:'wind', width:180}">风力</th>
    </tr>
  </thead>
</table>

<script src="/jiong/js/layui/layui/layui.js" charset="utf-8"></script>
<script>
	layui.use('table', function(){
	  var table = layui.table;
	});
</script>
</body>
</html>
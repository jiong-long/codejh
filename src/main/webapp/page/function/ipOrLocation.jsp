<%@page import="com.jianghu.core.tools.NetUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>JavaScript获取客户端IP</title>
<script type="text/javascript" src="/jiong/js/BootStrap/jquery.min.js"></script>
</head>
	<!-- 方法1,正常的返回ip和城市 -->
	<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
	<script type="text/javascript">  
		document.write(returnCitySN["cip"]+','+returnCitySN["cname"]);
		console.log(returnCitySN["cip"]+','+returnCitySN["cname"]);
	</script>
	
	<h1>已实现从request中获取IP,地址的功能，不用调用第三方的了</h1>
	<!-- 方法2,需要jquery支持，返回的中文是拼音，可以获取IP -->
	<script>
		$.ajax({
		    url: 'http://freegeoip.net/json/',
		    success: function(data){
		       document.write(JSON.stringify(data));
		    },
		    type: 'GET',
		    dataType: 'JSON'
		});
	</script>

	<script>
	    $.getJSON('//freegeoip.net/json/', function(data) {
	    	document.write(JSON.stringify(data, null, 2));
	    });
	</script>
	
	<!-- 方法3,只能获取城市，无法获取到IP -->
	<script src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>
 	<script>
 		document.write(remote_ip_info.city);  
 		console.log(remote_ip_info);
	</script>   
	
	<%
		String ip = NetUtil.getIpFromRequest(request);
		System.out.println(ip);
	%>
<body>
</body>
</html>
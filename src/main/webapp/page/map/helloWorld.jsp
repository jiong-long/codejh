<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=pCuWDaRFnEawujVcwB2WnpEaZLuOiYGc"></script>
	<title>当前位置</title>
</head>
<body>
	<div id="allmap"></div>
</body>
<script type="text/javascript">
	//相关demo查看：http://lbsyun.baidu.com/jsdemo.htm
	//创建Map实例
	var map = new BMap.Map("allmap");    
	// 初始化地图,设置中心点坐标和地图级别
	map.centerAndZoom(new BMap.Point(116.331398,39.897445), 11);  
	//开启鼠标滚轮缩放
	map.enableScrollWheelZoom(true);
	
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			map.panTo(r.point);
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true});
</script>
</html>

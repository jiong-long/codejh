<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ECharts</title>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:600px"></div>
    <!-- ECharts单文件引入 -->
    <script src="/jiong/js/ECharts/dist/echarts.js"></script>
    <script src="/jiong/js/core/jianghu.js"></script>
	<script src="/jiong/js/jQuery/jquery.min.js"></script>
	<script src="js/map2.js"></script>
	<script type="text/javascript">
		createECharts();
	</script>
</body>
</html>
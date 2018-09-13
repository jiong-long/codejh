<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page Not Found</title>
<link rel="stylesheet" type="text/css" href="/jiong/page/comm/404.css" />
</head>
<body>
	<div id="container">
		<div id="stage" class="stage">
			<div id="clouds" class="stage"
				style="background-position: 516.599999999995px 0px;"></div>
		</div>
		<div id="ticket">
			<section id="ticket_left">
				<p class="text1_a">迷失在云端</p>
				<p class="text2_a">未找到航班</p>
				<p class="text3_a">错误404</p>
				<p class="text4_a">对不起!</p>
				<p class="text5_a">从</p>
				<p class="text6_a">地球</p>
				<p class="text7_a">到</p>
				<p class="text8_a">火星</p>
				<p class="text9_a">座</p>
				<p class="text10_a">404</p>
				<p class="text11_a">尝试另一次飞行</p>
				<nav class="text12_a">
					<ul>
						<li><a href="/jiong/index.html">网站首页</a></li>
						<li><a href="/jiong/index.html">关于我们</a></li>
						<li><a href="/jiong/index.html">服务中心</a></li>
						<li><a href="/jiong/index.html">新闻资讯</a></li>
						<li><a href="/jiong/index.html">联系我们</a></li>
					</ul>
				</nav>
			</section>

			<section id="ticket_right">
				<p class="text1_b">第一类</p>
				<p class="text2_b">迷失在云端</p>
				<p class="text3_b">从</p>
				<p class="text4_b">地球</p>
				<p class="text5_b">到</p>
				<p class="text6_b">火星</p>
				<p class="text7_b">座</p>
				<p class="text8_b">404</p>
				<p class="text9_b">1</p>
				<p class="text10_b">103076498</p>
			</section>
		</div>
	</div>

	<script src="/jiong/page/comm/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="/jiong/page/comm/jquery.spritely-0.5.js" type="text/javascript"></script>

	<script type="text/javascript">
			$(document).ready(function() {
				$('#clouds').pan({fps: 40, speed: 0.7, dir: 'right', depth: 10});
			});
	</script>
</body>
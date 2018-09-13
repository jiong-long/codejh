<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" href="/jiong/js/layui/layui/css/layui.css" />
<script type="text/javascript" src="/jiong/js/easyUI/jquery.min.js"></script>
<script type="text/javascript" src="/jiong/js/layui/layui/layui.js" charset="utf-8"></script>
</head>
<body style="width: 40%;margin-left: auto;margin-right: auto;">
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>用户注册</legend>
	</fieldset>

	<form class="layui-form" action="" id="registerForm">
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-block">
				<input type="text" name="username" lay-verify="required"
					placeholder="请输入真实姓名" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">手机号</label>
			<div class="layui-input-block">
				<input id="phone" type="tel" name="phone" lay-verify="phone" autocomplete="off" class="layui-input" style="width: 70%;float: left;">
				<div id="phoneLoca" class="layui-form-mid layui-word-aux" style="margin-left: 10px;">请输入手机号</div>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">验证码</label>
			<div class="layui-input-block">
				<input type="tel" name="msgcode" lay-verify="required"
					placeholder="请输入验证码" autocomplete="off" class="layui-input"
					style="width: 70%;float: left;">
				<button type="button" id="msgCode" class="layui-btn layui-btn-normal" style="margin-left: 10px;" onclick="getCode(this)">获取验证码</button>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">密码</label>
			<div class="layui-input-block">
				<input type="password" name="password" lay-verify="pass"
					placeholder="请填写4到12位密码" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<script>
		var layer;
		layui.use(['form', 'layer'], function(){
		  var form = layui.form;
		  layer = layui.layer; //独立版的layer无需执行这一句
		  
		  //自定义验证规则
		  form.verify({
		  	pass: [/(.+){4,12}$/, '密码必须4到12位']
		  });
		  
		  //监听提交
		  form.on('submit(demo1)', function(data){
			  $.ajax({
		        type: 'POST',
		        url:'user_register.do',
		        data: $("#registerForm").serialize(),
		        datatype:'json',
		        error: function(msg) {
		        	layer.alert('注册失败', {icon: 6});
		        },
		        success: function(data) {
		        	if(data.flag == 0){
		        		layer.alert(data.msg, {icon: 6});
		        	}else{
		        		layer.msg("注册成功",{offset: 'lb'});
		        	}
		        }
		    });
			//因为使用了ajax,不阻止的话会再提交一次，找不到就刷新页面
			return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
		  });
		});
		
		//获取验证码
		var clock = '';
		var nums = 10;
		var btn;
		function getCode(thisBtn) {
			//获取验证码
			var phoneNumber = $("#phone").val();
			if(phoneNumber==""||phoneNumber==null){
				layer.alert('请输入手机号', {icon: 6});
			}else{
				$.ajax({
			        type: 'POST',
			        url:'user_getMsgCode.do',
			        data: "phone="+phoneNumber,
			        datatype:'json',
			        error: function(msg) {
			        	layer.alert('获取失败', {icon: 6});
			        },
			        success: function(data) {
			        	layer.alert(data.msg, {icon: 6});
			        }
			    });
				
				btn = thisBtn;
				btn.disabled = true; //将按钮置为不可点击
				btn.innerHTML = nums + '秒后重试';
				clock = setInterval(doLoop, 1000); //一秒执行一次
				//设置按钮不可用
				$("#msgCode").removeClass("layui-btn layui-btn-normal");
				$("#msgCode").addClass("layui-btn layui-btn-disabled");
			}
		}
		
		function doLoop() {
			nums--;
			if (nums > 0) {
				btn.innerHTML = nums + '秒后重试';
				$("#msgCode").removeClass("layui-btn layui-btn-normal");
				$("#msgCode").addClass("layui-btn layui-btn-disabled");
			} else {
				clearInterval(clock); //清除js定时器
				btn.disabled = false;
				btn.innerHTML = '获取验证码';
				nums = 10; //重置时间
				$("#msgCode").removeClass("layui-btn layui-btn-disabled");
				$("#msgCode").addClass("layui-btn layui-btn-normal");
			}
		}
		
		//获取手机归属地
		$("#phone").blur(function(){
			var phoneNumber = $("#phone").val();
			$.ajax({
		        type: 'POST',
		        url:'user_getPhoneLoca.do',
		        data: "phone="+phoneNumber,
		        datatype:'json',
		        error: function(msg) {
		        	layer.alert('获取失败', {icon: 6});
		        },
		        success: function(data) {
		        	$("#phoneLoca").html(data.msg);
		        }
		    });
		});
	</script>
</body>
</html>
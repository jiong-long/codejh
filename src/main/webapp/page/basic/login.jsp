<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/demo.css">
	<script type="text/javascript" src="/jiong/js/easyUI/jquery.min.js"></script>
	<script type="text/javascript" src="/jiong/js/core/jianghu.js"></script>
	<script type="text/javascript" src="/jiong/js/easyUI/jquery.easyui.min.js"></script>
	<!-- 必须在jquery.easyui.min.js加载之后加载 -->
	<script type="text/javascript" src="/jiong/js/easyUI/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="/jiong/js/layui/layer/layer.js"></script>
	<style type="text/css">
		.panel-header{
			display: none;
		}
		.panel-body{
			border-style: none;
		}
		.textbox{
			margin-left: 0px; 
			margin-right: 0px; 
			padding-top: 0px; 
			padding-bottom: 0px; 
			height: 20px; 
			line-height: 20px; 
			width: 171px;
		}
	</style>
</head>
<!-- 用户登录界面 -->
<body>
	<div align="center">
		<div style="margin:20px 0;"></div>
		<div class="easyui-panel" title="用户登录" style="width:400px;">
			<div style="padding:10px 60px 20px 60px">
			    <form id="ff" method="post">
			    	<table cellpadding="5">
			    		<tr>
			    			<td>用户名:</td>
			    			<!-- 输入后变大写  -->
			    			<td><input class="textbox" type="text" name="username" style="text-transform: uppercase;padding-left: 5px;"/></td>
			    		</tr>
			    		<tr>
			    			<td>密码:</td>
			    			<!-- 支持enter键提交表单	 -->
			    			<td><input class="textbox" type="text" name="password" onkeydown="javascript:if(event.keyCode==13) submitForm();" style="padding-left: 5px;"/></td>
			    		</tr>
			    		<tr>
			    			<td>验证码:</td>
							<td>
								<input class="easyui-textbox" type="text" name="firstcode" id="firstcode" style="padding-left: 5px;"/>
							</td>
						</tr>
			    		<tr>
							<td colspan="2" align="center">
								<img title="点击更换" src="/jiong/user_validatePic.do" onclick="javascript:refresh(this);" style="cursor: pointer;"/>
							</td>
			    		</tr>
			    		<tr>
			    			<td colspan="2">
			    				<input type="checkbox" name="autoLogin" value="true" style="margin-left: 20px;"/>一周之内免登陆
			    			</td>
			    		</tr>
			    	</table>
			    </form>
			    <div style="text-align:center;padding:5px">
			    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;
			    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清除</a>
			    </div>
		    </div>
		</div>
	</div>
	<script>
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
		function submitForm(){
			//TODO 前台判断验证码是否相同
			//难点，前台隐式刷新验证码，导致页面的session未更新，所以每次获取的都是上次的验证码，导致验证不通过
			$.ajax({
		        type: 'POST',
		        url:'user_login.do',
		        data:$('#ff').serialize(),
		        datatype:'json',
		        error: function(msg) {
		        	layer.alert("登录失败");
		        },
		        success: function(data) {
		        	if(data.flag == 1){
		        		//异步的时候使用该方法，由于需要刷新页面（加载layim）所以不能使用异步的方式
		        		//改变父页面的值
		        		//parent.$("#login").text(data.username);
		        		//关闭父页面
		        		//parent.layer.close(index);
		        		parent.toIndexPage();
		        	}else{
		        		layer.alert(data.msg);
		        	}
		        }
		    });
		}
		
		function clearForm(){
			$('#ff').form('clear');
		}
		
		//刷新验证码
		function refresh(obj) {
			//需要传递不同的参数，否则图片不能刷新
	        obj.src = "/jiong/user_validatePic.do?params="+Math.random();
	    }
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>form表单</title>
<script src="/jiong/js/Vue/vue.js"></script>
</head>
<body>
	<div id="app">
		<p>单个复选框：</p>
		<input type="checkbox" id="checkbox" v-model="checked"> <label
			for="checkbox">{{ checked }}</label>

		<p>多个复选框：</p>
		<input type="checkbox" id="runoob" value="Runoob"
			v-model="checkedNames"> <label for="runoob">Runoob</label> <input
			type="checkbox" id="google" value="Google" v-model="checkedNames">
		<label for="google">Google</label> <input type="checkbox" id="taobao"
			value="Taobao" v-model="checkedNames"> <label for="taobao">taobao</label>
		<br> <span>选择的值为: {{ checkedNames }}</span>
	</div>

	<script>
		new Vue({
		  	el: '#app',
		  	data: {
		    	checked : false,
		    	checkedNames: []
		  	}
		})
	</script>
</body>
</html>
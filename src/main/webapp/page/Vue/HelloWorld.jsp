<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vue</title>
<script src="/jiong/js/Vue/vue.js"></script>
</head>
<body>
	<div id="app">
        {{ message }}
    </div>
    
    <div id="app-2">
		<span v-bind:title="message">
	    	鼠标悬停几秒钟查看此处动态绑定的提示信息！
	  	</span>
	</div>
	
	<div id="app-3">
	  	<p v-if="seen">现在你看到我了</p>
	</div>
	
	<div id="app-4">
	  	<ol>
		    <li v-for="todo in todos">
		      	{{ todo.text }}
		    </li>
		</ol>
	</div>
    
    <script type="text/javascript">
    	var app4 = new Vue({
	   	  	el: '#app-4',
	   	  	data: {
	   	    	todos: [
	   	      		{ text: '学习 JavaScript' },
	   	      		{ text: '学习 Vue' },
	   	      		{ text: '整个牛项目' }
	   	    	]
	   	  	}
    	})
    
    	var app3 = new Vue({
    	  	el: '#app-3',
    	  	data: {
    	    	seen: true
    	  	}
    	})
    
	    var app2 = new Vue({
		  	el: '#app-2',
		  	data: {
		    	message: '页面加载于 ' + new Date().toLocaleString()
		  	}
		})
    
	    var app = new Vue({
	        el: '#app',
	        data: {
	            message: 'Hello Vue!'
	        }
	    });
    </script>
</body>
</html>
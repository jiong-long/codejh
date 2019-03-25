<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基础</title>
<script src="/jiong/js/Vue/vue.js"></script>
</head>
<body>
	<!-- v-once 只允许赋值一次，后续不允许改变 -->
	<div id="app" v-once>
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
	
	<div id="app-5">
  		<p>{{ message }}</p>
  		<button v-on:click="reverseMessage">逆转消息</button>
	</div>
	
	<div id="app-6">
  		<p>{{ message }}</p>
  		<input v-model="message">
	</div>
	
	
	<div id="app-7">
  		<ol>
		    <!--现在我们为每个 todo-item 提供 todo对象  todo对象是变量，即其内容可以是动态的。
		      	我们也需要为每个组件提供一个“key”，稍后再 作详细解释。 -->
	    	<todo-item v-for="item in groceryList" v-bind:todo="item" v-bind:key="item.id"></todo-item>
  		</ol>
	</div>
    
    <script type="text/javascript">
    	Vue.component('todo-item', {
    		// todo-item 组件现在接受一个 "prop"，类似于一个自定义特性。
   		  	// 这个 prop 名为 todo。
   		 	props: ['todo'],
   		  	template: '<li>{{ todo.text }}</li>'
    	})
    	
    	var app7 = new Vue({
			el: '#app-7',
		  	data: {
		    	groceryList: [
		      		{ id: 0, text: '蔬菜' },
		      		{ id: 1, text: '奶酪' },
		      		{ id: 2, text: '随便其它什么人吃的东西' }
		    	]
		  	}
		})
    
    	var app6 = new Vue({
    	  	el: '#app-6',
    	  	data: {
    	    	message: 'Hello Vue!'
    	  	}
    	})
    
    	var app5 = new Vue({
    	  	el: '#app-5',
    	  	data: {
    	   		message: 'Hello Vue.js!'
    	  	},
    	  	methods: {
    	    	reverseMessage: function () {
    	      		this.message = this.message.split('').reverse().join('')
    	    	}
    	  	}
    	})
    	
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
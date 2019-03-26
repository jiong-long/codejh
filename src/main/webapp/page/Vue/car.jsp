<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<script src="/jiong/js/Vue/vue.js"></script>
<style type="text/css">
table {
	border: 1px solid black;
}

table {
	width: 100%;
}

th {
	height: 50px;
}

th, td {
	border-bottom: 1px solid #ddd;
}
</style>
</head>
<body>
	<div id="app">
		<table>
			<tr>
				<th>序号</th>
				<th>商品名称</th>
				<th>商品价格</th>
				<th>购买数量</th>
				<th>操作</th>
			</tr>
			<tr v-for="iphone in Ip_Json">
				<td>{{ iphone.id }}</td>
				<td>{{ iphone.name }}</td>
				<td>{{ iphone.price }}</td>
				<td>
					<button v-bind:disabled="iphone.count === 0" v-on:click="iphone.count-=1">-</button> 
						{{ iphone.count }}
					<button v-on:click="iphone.count+=1">+</button>
				</td>
				<td>
					<button v-on:click="iphone.count=0">移除</button>
				</td>
			</tr>
		</table>
		总价：{{ totalPrice() }}
	</div>

	<script type="text/javascript">
		var app = new Vue({
		  	el: '#app',
		  	data: {
		   		Ip_Json: [
		   			{
		   				id: 1,
				    	name: 'iphone 8',
				    	price: 5099,
				   		count: 1
				   	},
	              	{
		                id: 2,
		                name: 'iphone xs',
		                price: 8699,
		                count: 1
	              	},
	              	{
		                id: 3,
		                name: 'iphone xr',
		                price: 6499,
		                count: 1
              		}
	            ]
		  	},
		  	methods:{
		    	totalPrice : function(){
		      	var totalP = 0;
		      	for (var i = 0,len = this.Ip_Json.length;i<len;i++) {
		        	totalP += this.Ip_Json[i].price * this.Ip_Json[i].count;
		      	}
		      	return totalP;
		    	}
		  	}
		})
	</script>
</body>
</html>
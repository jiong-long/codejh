<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
    <head>
        <script src="/jiong/js/AngularJS/angular-1.5.8.min.js"></script>
    </head>
    <body>
        Your name: <input type="text" ng-model="yourname" placeholder="World"><br/>
        Hello {{yourname || 'World'}}!
        <hr/>
        
        <div ng-init="firstName='John'">
		<p>姓名为 <span ng-bind="firstName"></span></p>
		</div>
        <hr/>
		
		<!-- ng表达式 -->
		<p>我的第一个表达式： {{ 5 + 5 }}</p>
		
        <div ng-controller="getSum">
                        单价：<input type="text" ng-model="cost"/><br/>
                        数量：<input type="text" ng-model="quantity"/><br/>
		<p>总价： {{ quantity * cost }}</p>
		<p>总价： <span ng-bind="quantity * cost"></span></p>
		</div>
        <hr/>
		
		<div ng-controller="myCtrl">
		姓：<input type="text" ng-model="firstName"/><br/>
		名：<input type="text" ng-model="lastName"/><br/>
		<br/>
		姓名：{{firstName+lastName}}
		</div>
		<script>
			var app = angular.module('myApp',[]);
			app.controller('myCtrl',function($scope){
				$scope.firstName = "江";
				$scope.lastName = "湖";
			});
			app.controller("getSum",function($scope){
				$scope.quantity = 1;
				$scope.cost = 5;
			});
		</script>
		
		<!-- ng对象和数组 -->
		<div ng-init="person={firstName:'江',lastName:'湖'}">
			<p>姓名：{{person.firstName+person.lastName}}</p>
		</div>
		<div ng-init="arr=[2,4,6,7,8]">
			<div ng-repeat="x in arr">{{ x }}</div>
		</div>
        <hr/>
		
		<!-- 输入校验 -->
		<form name="myForm">
			邮箱：<input type="email" name="myAddress" ng-model="text" required/>
			<span ng-show="myForm.myAddress.$error.email" style="color: red;">不是一个合法的邮箱地址</span>
			<h1>状态</h1>
		    <p>Valid: {{myForm.myAddress.$valid}} (如果输入的值是合法的则为 true)。</p>
			<p>Dirty: {{myForm.myAddress.$dirty}} (如果值改变则为 true)。</p>
			<p>Touched: {{myForm.myAddress.$touched}} (如果通过触屏点击则为 true)。</p>
		</form>
		
    </body>
</html>
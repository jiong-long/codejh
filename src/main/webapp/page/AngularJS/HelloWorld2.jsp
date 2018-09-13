<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp" ng-controller="myCtrl"
>
    <head>
        <script src="/jiong/js/AngularJS/angular-1.5.8.min.js"></script>
        <script src="/jiong/js/AngularJS/angular-animate.min.js"></script>
    </head>
    <style>
		div {
		    transition: all linear 0.5s;
		    background-color: lightblue;
		    height: 100px;
		}
		.ng-hide {
		    height: 0;
		}
	</style>
    <body>
    	<!-- 
    		AngularJS作用域、过滤器
    	-->
    	<input type="text" ng-model = "first">
    	<input type="text" ng-model = "last">
    	<h1>{{fullname() | currency}}</h1>
    	<ul>
    		<li ng-repeat="x in names | filter:first | orderBy:'country'">
    			{{x.name+' '+x.country}}
    		</li>
		</ul>
		
		<!-- 
			AngularJS服务 
		-->
		<!-- 返回当前页面的 URL 地址  -->
		{{myUrl}}<br/><br/><br/>
		
		<!-- 当前时间 -->
		{{theTime}}<br/><br/><br/>
		
		<!-- 自定义服务 -->
		{{to16 | myFilter}}<br/><br/><br/>
		
		<!-- http服务 -->
		<ul>
 			<li ng-repeat="x in httpNames | filter:last">
    			{{ x.Name + ', ' + x.Country }}
  			</li>
		</ul>
		
		<!-- 下拉框select -->
		<!-- ng-options 的选项的一个对象， ng-repeat 是一个字符串。 -->
		<select ng-model="selectedNames" ng-options="x for x in selectNames"></select>
		<select>
			<option ng-repeat="x in selectNames2">{{x}}</option>
		</select>
		<h3>你选择的值是: {{selectedNames}}</h3>
		
		隐藏 DIV: <input type="checkbox" ng-model="myCheck">
		<div ng-hide="myCheck"></div>
		
		<script src="personController.js"></script>		
    </body>
</html>
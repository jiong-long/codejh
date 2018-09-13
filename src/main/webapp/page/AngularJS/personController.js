var app = angular.module('myApp', ['ngAnimate']);
app.controller('myCtrl', function($scope,$location,$interval,jianghu,$http) {
    $scope.first = "";
    $scope.last = "";
    $scope.fullname = function(){
    	return $scope.first+$scope.last;
    };
    $scope.names = [{"name":"Jani","country":"Norway"},{"name":"Hege","country":"Sweden"},{"name":"Kai","country":"Denmark"}];
    //它可以返回当前页面的 URL 地址。
    $scope.myUrl = $location.absUrl();
    
    $interval(function () {
        $scope.theTime = new Date().toLocaleString();
    }, 1000);
    
    $scope.to16 = jianghu.myFunc(255);
    
    //http 服务
    $http.get("angularJS_getAlldata.do").success(
    	function (response) {
    		$scope.httpNames = response.sites;
    	}
   	);
    
    //下拉框select
    $scope.selectNames = ["Google", "Runoob", "Taobao"];
    $scope.selectNames2 = ["Google", "Runoob", "Taobao"];
});

//创建自定义服务
app.service("jianghu",function(){
	this.myFunc = function(x){
		return x.toString(16);
	};
});

//自定义过滤器，并使用自定义服务
app.filter("myFilter",["jianghu",function(jianghu){
	return function(x){
		return jianghu.myFunc(x);
	};
}]);
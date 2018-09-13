var aa = new zturn({
	id : "zturn",
	opacity : 0.9,
	width : 382,
	Awidth : 1024,
	scale : 0.9
});
var ab = new zturn({
	id : "zturn2",
	opacity : 0.8,
	width : 382,
	Awidth : 1024,
	scale : 0.6
});

//返回首页
function toIndexPage() {
	window.location.href = "/jiong/index.html";
}

// 搜索
function searchClick(){
	var params = $("#titleContent").val();
	// 解决get方式传中文到后台乱码的问题
	// 当使用URLwriter的时候，URLwriter会解码一次，所以前台需要编码三次
	// No1.浏览器解码一次 No2.URLWriter会解码一次 No3.应用程序解码一次
	window.location.href = "/jiong/index_" + encodeURI(encodeURI(encodeURI(params))) + ".html";
	// window.location.href =
	// "/jiong/index.html?params="+encodeURI(encodeURI(params));
}

// 登录
$("#login").click(function() {
	var text = $("#login").text();
	if (text == '登录') {
		openLogin();
	}
});

// 点击地址，查询天气
$("#location").click(function() {
	var localtion = $("#location").html();
	window.location.href = "/jiong/page/webServices/weather.html?localtion="+localtion;
});

// 登录
function openLogin() {
	layui.use([ 'layer' ], function() {
		layer = layui.layer; // 独立版的layer无需执行这一句
		layer.open({
			type : 2,
			title : '登录',
			area : [ '430px', '350px' ], // 区域大小
			offset : [ '100px' ], // 具体位置
			fixed : true, // 固定
			content : '/jiong/page/basic/login.jsp',
			cancel : function(index, layero) {
				layer.confirm('您还没有登录，确定要关闭么？', {
					btn : [ '登录', '关闭' ],
					offset : [ '130px' ]
				}, function() {
					layer.closeAll();
					openLogin();
				}, function() {
				});
			}
		});
	});
}

// 增加案例
$("#add").click(function() {
	window.location.href = "/jiong/page/basic/addItem.html";
});

// 查看案例详情
function gotoItem(id) {
	window.location.href = "/jiong/page/basic/addItem_" + id + ".html";
}

// 返回顶部
$(".btn_top").hide();
$(".btn_top").on("click", function() {
	$('html, body').animate({
		scrollTop : 0
	}, 100);
	return false;
});
$(window).bind('scroll resize', function() {
	if ($(window).scrollTop() <= 100) {
		$(".btn_top").hide();
	} else {
		$(".btn_top").show();
	}
});

// layim
layui.use('layim', function(layim) {
	// 基础配置
	layim.config({
		title : "LayIM",
		min : true,
		notice : true,
		init : {//初始化设置，获取用户信息、好友、群组
			url : 'layIMAction/getBasicInfo.htm'
		},uploadImage: {//上传图片
			url: 'layIMAction/uploadImg.htm'
		},uploadFile: {//上传文件
			url: 'layIMAction/uploadFile.htm'
		},members: {//获取群组成员
			url: 'layIMAction/getGroupPer.htm'
		},tool: [{//扩展工具栏，可同时配置多个
		    alias: 'code', //工具别名
		    title: '代码', //工具名称
		    icon: '&#xe64e;' //工具图标，参考图标文档
		}],
		msgbox: layui.cache.dir + 'css/modules/layim/html/msgbox.html', //消息盒子页面地址，若不开启，剔除该项即可
		find: layui.cache.dir + 'css/modules/layim/html/find.html', //发现页面地址，若不开启，剔除该项即可
		chatLog: layui.cache.dir + 'css/modules/layim/html/chatlog.html' //聊天记录
	});
	
	// 监听自定义工具栏点击，以添加代码为例
	layim.on('tool(code)', function(insert, send, obj) { // 事件中的tool为固定字符，而code则为过滤器，对应的是工具别名（alias）
		layer.prompt({
			title : '插入代码',
			formType : 2,
			shade : 0
		}, function(text, index) {
			layer.close(index);
			insert('[pre class=layui-code]' + text + '[/pre]'); // 将内容插入到编辑器，主要由insert完成
			// send(); //自动发送
		});
		console.log(this); // 获取当前工具的DOM对象
		console.log(obj); // 获得当前会话窗口的DOM对象、基础信息
	});   
	
	//加载完成
	layim.on('ready', function(options){
		console.log(options);
		layim.msgbox(5); //模拟消息盒子有新消息，实际使用时，一般是动态获得
	});
	
	//在线状态监听
	layim.on('online', function(status){
		$.ajax({
	        type: 'POST',
	        url: 'layIMAction/changeState.htm',
	        data: 'status=' + status,//获得online或者hide
	        datatype:'json'
	    });
	});  
	
	//更换签名
	layim.on('sign', function(value){
		$.ajax({
	        type: 'POST',
	        url: 'layIMAction/changeSign.htm',
	        data: 'value=' + value,//获得online或者hide
	        datatype:'json'
	    });
	});
	
	//监听发送的消息
	layim.on('sendMessage', function(res){
		var mine = res.mine; //包含我发送的消息及我的信息
		var to = res.to; //对方的信息
		//会调用WebsocketEndPoint.java中的handleTextMessage方法
		socket.send(JSON.stringify({
			type: 'chatMessage',
		    data: res
		})); 
	});
	
	//websocket相关配置
	var socket = null;
	//判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		//建立WebSocket通讯
		socket = new WebSocket("ws://localhost:8080/jiong/websocket.htm");
	}else {
		//可以使用socketjs模拟websocket的连接
		layer.alert('当前浏览器不支持 websocket,请联系管理员使用socketjs');
	}
	
	//连接成功时触发
	socket.onopen = function(){
	    //socket.send('XXX连接成功'); 
	};
	
	//监听收到的消息
	socket.onmessage = function(res){
		res = JSON.parse(res.data);
		if(res.type == 'chatMessage'){
			layim.getMessage(res.data); //res.data即你发送消息传递的数据（阅读：监听发送的消息）
		}
	};
	
	//连接发生错误的回调方法
	socket.onerror = function () {
		layer.alert("WebSocket连接发生错误");
	};
});
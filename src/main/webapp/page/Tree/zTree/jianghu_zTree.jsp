<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>目录结构</title>
	<!-- EasyUI -->
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/demo/demo.css">
	<script type="text/javascript" src="/jiong/js/easyUI/jquery.min.js"></script>
	<script type="text/javascript" src="/jiong/js/easyUI/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/jiong/js/easyUI/locale/easyui-lang-zh_CN.js"></script>
	<!-- zTree -->
	<link rel="stylesheet" href="/jiong/js/zTree/css/demo.css" type="text/css">
  	<link rel="stylesheet" href="/jiong/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
  	<script type="text/javascript" src="/jiong/js/zTree/js/jquery.ztree.all-3.5.js"></script>
  	<!-- jianghu -->
  	<script type="text/javascript" src="/jiong/js/core/jianghu.js"></script>
  	<!-- 右键菜单样式 -->
  	<style type="text/css">  
	    div#rMenu {
	    	position:absolute; 
	    	visibility:hidden; 
	    	top:0; 
	    	background-color: #F0F0F0;
	    	text-align: left;
	    	padding: 2px;
	    	width:100px;
	    }  
	    div#rMenu a{  
	        cursor: pointer;  
	        list-style: none outside none;  
	    }  
	</style>  
</head>
<body>
	<div style="margin-top: 5px;"></div>
	<div class="easyui-layout" style="width: 100%;height: 600px;">
		<div id="p" data-options="region:'west'" title="目录" style="width:30%;padding:10px;">
			<ul id="treeDemo" class="ztree" style="border: none;background-color: white;overflow: auto; margin-left: 40px;height: 95%;"></ul>
		</div>
		<div data-options="region:'center'" title="维护">
			<iframe src="jianghu_zTree_iFrame.jsp" id="treeFrame" style="width: 100%;height: 100%;"></iframe>		   
		</div>
	</div>
	
	<div id="rMenu">  
		<img src="/jiong/images/deleteNode.png" style="padding-top: 2px;"/>
	    <a href="#" class="list-group-item" onclick="deleteTreeNode()">删除节点</a>  
	</div> 
	
	<!-- 为了提高页面加载速度，建议将js放在body底部 -->
	<script language="JavaScript">
	    var setting = {
	    	view : {
	    		dblClickExpand: true,
	    		expandSpeed: "fast",
	    		showIcon: true,
	    		showLine: true
	    	},
		    data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pid",
					rootPId: 0,
				}
			},
			//zTree异步加载
			async:{
				enable: true,
				url:"zTree_getDownZtree.do",
				//每次请求后台，自动将id作为参数
				autoParam: ["id"]
			},
			edit: {
				enable: true,
				//拖动排序
				drag :{
					isCopy: false, //所有拖拽操作都是 move
					isMove: true,
					prev: true,
					next: true,
					inner: true //常见的不允许拖到节点内部
				},
				showRenameBtn: false,
				showRemoveBtn: false
			},
			callback:{
				onClick:zTreeOnClick,
				onAsyncSuccess: zTreeOnAsyncSuccess,
				onDrop: zTreeOnDrop,
				onRightClick: zTreeOnRightClick
			}
	    }; 
	    
	    //节点点击事件
	    function zTreeOnClick(event,treeId,treeNode){
	    	//调用iFrame中的setValue()方法
	    	document.getElementById("treeFrame").contentWindow.setValue(treeNode);
	    }
	    
	    //异步加载成功
	    function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	    	//如果需要选中的节点不为空，则选中该节点
			if(needSelectNodeId!=null&&needSelectNodeId!=""&&needSelectNodeId!=undefined){
				var treeObj = getTreeObject();
				var node = treeObj.getNodeByParam("id", needSelectNodeId, null);
				//选中该节点
				treeObj.selectNode(node);
				//刷新iframe
				document.getElementById("treeFrame").contentWindow.setValue(node);
			}
	    };
	    
	    //拖动排序
	    function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
	    	if(treeNodes.length>1){
	    		addPageInfo("","1","请选择一个节点进行拖拽");
	    		return;
	    	}
	    	
	    	$.ajax({
		        type: 'POST',
		        url:'zTree_zTreeOnDrop.do',
		        //只需要三个参数,移动方式，移动节点id，目标节点id
		        data: "params=" + moveType + "@" + treeNodes[0].id + "@" + targetNode.id,
		        datatype:'json',
		        error: function(msg) {
		            alert("拖动失败！");
		        },
		        success: function(data) {
		        	//如果不显示排序字段，可以不刷新
		        	refreshNode(data);
		        	$.messager.show({
		        		title:'我的消息',
		        		msg:'拖动成功！',
		        		timeout:3000,
		        		showType:'slide'
		        	});
		        }
		    });
	    }
	    
	    function getTreeObject() {
	    	return $.fn.zTree.getZTreeObj("treeDemo");
	    }
	    
		// 基本功能菜单加载,默认加载第一层
		$.ajax({
			url : 'zTree_getInitZtree.do',
			type : 'POST',
			dataType : 'text',
			success : function(data) {
				var zNodes = eval("(" + data + ")");
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			},
			error : function(msg) {
				alert('菜单加载异常!');
			}
		});
		
		var needSelectNodeId;
		//保存后，刷新父节点，并选中
		function refreshNode(data){
			needSelectNodeId = data.id;
			var pid = data.pid;
			var moveType = data.moveType;//移动方式
			
			var treeObj = getTreeObject();
			
			var refreshNode;
			if(moveType!=null&&moveType!=""&&moveType!=undefined){//moveType不为空，是拖动排序
				if(moveType == "inner"){//如果移动到内部，需要异步加载目标节点
					refreshNode = treeObj.getNodeByParam("id", pid, null);
				}else{//否则需要异步加载，当前节点父节点或者目标节点父节点
					var node = treeObj.getNodeByParam("id", pid, null);
					refreshNode = node.getParentNode();
				}
			}else{//否则改变排序字段的值
				refreshNode = treeObj.getNodeByParam("id", pid, null);
			}
			
			//设置当前节点为父节点，否则不会异步刷新
			if(!refreshNode.isParent){
				refreshNode.isParent = true;
				//异步刷新后该节点需要展开
				refreshNode.icon = "/jiong/images/floder_open.png";
			}
			
			treeObj.reAsyncChildNodes(refreshNode, "refresh");
		}
		
		 //右键菜单
		var deleteNode;
	    function zTreeOnRightClick(event, treeId, treeNode) {
			if(treeNode==null||treeNode==""||treeNode==undefined){
				return;
			}
			deleteNode = treeNode;
	    	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {  
	            showRMenu("root", event.clientX, event.clientY);  
	        } else if (treeNode && !treeNode.noR) {  
	            showRMenu("node", event.clientX, event.clientY);  
	        }  
	    }
	    
	  	//显示右键菜单  
	    function showRMenu(type, x, y) {  
	        $("#rMenu ul").show();  
	        $("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"}); //设置右键菜单的位置、可见  
	        $("body").bind("mousedown", onBodyMouseDown);  
	    }  
	    //隐藏右键菜单  
	    function hideRMenu() {  
	        if (rMenu){
	        	$("#rMenu").css({"visibility": "hidden"}); //设置右键菜单不可见  
	        } 
	        $("body").unbind("mousedown", onBodyMouseDown);  
	    }  
	    
	    //鼠标按下事件  
	    function onBodyMouseDown(event){  
	        if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {  
	        	$("#rMenu").css({"visibility" : "hidden"});  
	        }  
	    }  
	    
	    function deleteTreeNode(){
	    	$.messager.confirm("确认", "确认删除", function (r) {  
    	        if (r) {  
    	        	//根节点不允许删除，所以可以直接获取父节点
    		    	var pNode = deleteNode.getParentNode();
    		    	needSelectNodeId = pNode.id;
    		    	$.ajax({
    			        type: 'POST',
    			        url:'zTree_deleteNode.do',
    			        //只需要三个参数,移动方式，移动节点id，目标节点id
    			        data: "params=" + deleteNode.id,
    			        datatype:'json',
    			        error: function(msg) {
    			            alert("删除失败！");
    			        },
    			        success: function(data) {
    			        	//删除成功后，隐藏右键菜单
    			        	hideRMenu();
    			        	var treeObj = getTreeObject();
    			        	
    			        	var childNode = pNode.children;
    			        	//如果第一个子节点为空，则删除后该节点为文件
    			        	if(childNode.id==null||childNode.id==""||childNode.id==undefined){
    			        		pNode.icon = "/jiong/images/file.png";
    			        	}
    			        	
    			        	treeObj.reAsyncChildNodes(pNode, "refresh");
    			        	$.messager.show({
    			        		title:'我的消息',
    			        		msg:'删除成功！',
    			        		timeout:3000,
    			        		showType:'slide'
    			        	});
    			        }
    			    });
    	        }  
    	    });  
	    }
  	</script>
</body>
</html>
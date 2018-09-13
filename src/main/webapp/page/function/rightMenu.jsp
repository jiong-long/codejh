<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>右键菜单</title>
<style type="text/css">
body {
	height: 100%;
	text-align: center;
}

.group-status-info {
	width: 800px;
	border: 1px solid #ccc;
	text-decoration: none;
	border-radius: 4px;
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
	margin: 0 auto;
	border-radius: 4px;
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
	margin-top: 100px;
}

.group-status-table {
	width: 95%;
	margin: 0 auto;
	text-align: left;
}

.group-status-table tr td {
	padding-top: 5px;
	padding-bottom: 5px;
	word-break: keep-all;
	white-space: nowrap;
	width: 126px;
}

.group-status-table label {
	cursor: pointer;
}

.menu {
	width: 55px;
	border: 5px solid #bfbfbf;
	background: #bfbfbf;
	visibility: hidden;
	position: absolute;
}

.menu input {
	width: 50px;
	margin: 1px;
}
</style>
</head>
<body>
	<!-- 
 oncontextmenu="return false"  屏蔽原来的右键菜单
 onmousedown  在鼠标按钮在元素上按下时触发。
 event.button == 2 右键  0 左键  1 滑轮按下

 -->
	<div class="group-status-info" id="gsInfo" oncontextmenu="return false"
		onmousedown="if(event.button == 2) showGRMenu()"
		onclick="hideGRMenu()">
		<table class="group-status-table">
			<tr>
				<td><label><input name="groupstates" type="checkbox"
						value="1" />正在登记</label></td>
				<td><label><input name="groupstates" type="checkbox"
						value="2" />等待检查</label></td>
				<td><label><input name="groupstates" type="checkbox"
						value="3" />正在检查</label></td>
				<td><label><input name="groupstates" type="checkbox"
						value="4" />等待报告</label></td>
				<td><label><input name="groupstates" type="checkbox"
						value="5" />正在报告</label></td>
				<td><label><input name="groupstates" type="checkbox"
						value="6" />等待审核</label></td>
			</tr>
		</table>
	</div>
	<div id='menuGS' class="menu">
		<input name="check-all" type="button" value="全选"
			onclick="check_All('groupstates','menuGS')" /><br> <input
			name="clear-all" type="button" value="全清"
			onclick="clear_All('groupstates','menuGS')" />
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript">
function showGRMenu() {
        var sInfo=g('gsInfo');
        var menu = g('menuGS');

        var evt = window.event || arguments[0];
        /*获取当前鼠标右键按下后的位置，据此定义菜单显示的位置*/
        var rightEdge = sInfo.clientWidth-evt.clientX;
        var bottomEdge = sInfo.clientHeight-evt.clientY;
        /*如果从鼠标位置到容器右边的空间小于菜单的宽度，就定位菜单的左坐标（Left）为当前鼠标位置向左一个菜单宽度*/
        if (rightEdge < menu.offsetWidth) 
            menu.style.left = sInfo.scrollLeft + evt.clientX - menu.offsetWidth + "px"; 
        else
            /*否则，就定位菜单的左坐标为当前鼠标位置*/
            menu.style.left = sInfo.scrollLeft + evt.clientX + "px";

        /*如果从鼠标位置到容器下边的空间小于菜单的高度，就定位菜单的上坐标（Top）为当前鼠标位置向上一个菜单高度*/
        if (bottomEdge < menu.offsetHeight)
            menu.style.top = sInfo.scrollTop + evt.clientY - menu.offsetHeight + "px";
        else
            /*否则，就定位菜单的上坐标为当前鼠标位置*/
            menu.style.top = sInfo.scrollTop + evt.clientY + "px";

        /*设置菜单可见*/
        menu.style.visibility = "visible"; 
};
function hideGRMenu() {
    if (g('menuGS').style.visibility = 'visible'){
        g('menuGS').style.visibility = 'hidden';
    }
};
function check_All(name,menuId){
        var arr=gn(name);
        var menu=g(menuId);
        var i;
        for(i=0;i<arr.length;i++){
            arr[i].checked=true;
        }
        menu.style.visibility = 'hidden';
};
function clear_All(name,menuId){
        var arr=gn(name);
        var menu=g(menuId);
        var i;
        for(i=0;i<arr.length;i++){
            arr[i].checked=false;
        }
        menu.style.visibility = 'hidden';
};  
	function g(id) {
        return document.getElementById(id);
    };

    function gn(name){
        return document.getElementsByName(name);
    };
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>zTree</title>
<!-- EasyUI -->
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/demo/demo.css">
	<script type="text/javascript" src="/jiong/js/easyUI/jquery.min.js"></script>
	<script type="text/javascript" src="/jiong/js/easyUI/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/jiong/js/easyUI/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div style="margin-top: 50px;" align="center">
		<form id="ff" method="post" action="/jiong/zTree_saveOrUpdate.do">
		   	<table cellpadding="5">
		   		<tr>
	    			<td><input type="hidden" id="id" name="id"/></td>
		   		</tr>
		   		<tr>
	    			<td>名称:</td>
	    			<td><input class="easyui-textbox" type="text" id="name" name="name" data-options="required:true"/></td>
	    			<td>排序:</td>
	    			<td><input class="easyui-textbox" type="text" id="seq" name="seq"/></td>
	    		</tr>
	    		<tr>
	    			<td>上级主键:</td>
	    			<td><input class="easyui-textbox" type="text" id="pid" name="pid" data-options="required:true"/></td>
	    		</tr>
	    		<tr>
	    			<td>描述:</td>
	    			<td colspan="3">
	    				<input class="easyui-textbox" name="intro" id="intro" data-options="multiline:true" style="height:60px;width: 347px;"/>
	    			</td>
	    		</tr>
	    	</table>
    	</form>
	</div>
    <div style="text-align:center;padding:5px">
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addForm()" style="margin-right: 25px;">新增</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="margin-left: 25px;">保存</a>
    </div>
	<script>
		function submitForm(){
			$.ajax({
		        type: 'POST',
		        url:'zTree_saveOrUpdate.do',
		        data:$('#ff').serialize(),
		        datatype:'json',
		        error: function(msg) {
		            alert("保存或修改错误！");
		        },
		        success: function(data) {
		        	parent.refreshNode(data);
		        	$.messager.show({
		        		title:'我的消息',
		        		msg:'保存成功！',
		        		timeout:3000,
		        		showType:'slide'
		        	});
		        }
		    });
		}
		function addForm(){
			var id = document.getElementById("id").value;
			document.getElementById("id").value = '';
			$("#name").textbox('setValue','');
			$("#pid").textbox('setValue',id);
			$("#pid").textbox('readonly',true);
			$("#intro").textbox('setValue','');
			$("#seq").textbox('setValue','');
		}
		function setValue(treeNode){
			document.getElementById("id").value = treeNode.id;
			$("#name").textbox('setValue',treeNode.name);
			$("#pid").textbox('setValue',treeNode.pid);
			$("#pid").textbox('readonly',true);
			$("#intro").textbox('setValue',treeNode.intro);
			$("#seq").textbox('setValue',treeNode.seq);
		}
	</script>
</body>
</html>
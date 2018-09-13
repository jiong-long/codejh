<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>爬虫</title>
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/jiong/js/easyUI/demo.css">
	<script type="text/javascript" src="/jiong/js/easyUI/jquery.min.js"></script>
	<script type="text/javascript" src="/jiong/js/easyUI/jquery.easyui.min.js"></script>
</head>
<body>
	<a href="http://tieba.baidu.com/p/4806816968" style="font-size: 10pt">http://tieba.baidu.com/p/4806816968</a> 
	<br/>
	<!-- 页面布局啊！到底该怎么布局比较好，样式要修改啊!!! -->
	<!-- 表单三要素：action、post、enctype -->
	<form action="pcAndMail_excelImport.do" method="post" enctype="multipart/form-data" style="margin-left: 2px;">
		<!-- input name与后台对应 -->
		<input type="file" name="myUpload"/>
		<input type="submit" name="导入Excel数据" value="导入Excel数据"/>
	</form>
	
	<center>
	<h2>爬  虫</h2>
	<div style="margin:20px 0;"></div>
	<input class="easyui-searchbox" data-options="prompt:'请输入网址',searcher:doSearch" style="width:450px;height: 30px;"/>
	<div style="margin:20px 0;"></div>
	
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="sendMails()">发送邮件</a>
	
	<a href="pcAndMail_excelExport.do" class="easyui-linkbutton" style="margin-left: 20px;">
		<div style="padding-top: 3px;float: left;">
			<img src="/jiong/images/excelExport.png"/>
		</div>
		导出EXCEL
	</a>
	
	<div style="margin:20px 0;"></div>
	<div region="center" border="false">
    	<table id="grid" width="30%"></table>
	</div>
	</center>
	<script>
		//搜索所有的emaile并保存到数据库
		function doSearch(value){
			 $.ajax({
	            	type:"post",
	            	url:"pcAndMail_saveMailtoDB.do",
	            	datatype:"json",
	            	data:"params=" + value,
	            	error:function(msg){
	            		$.messager.alert('警告','查询出错！！！','warning'); 
	            	},
	            	success:function(data){
	        			$('#grid').datagrid( {
	        				title:"邮箱列表", 
	        				url : "pcAndMail_getMailfromDB.do",
	        				columns : columns,
	        				pagination : true, //分页控件
	        				rownumbers : true, //行号 
	        				//fit: true//自动大小
	        				//单击事件   
	        				//onClickRow:function(rowIndex,rowData){
	        				//  	alert("单击");
	        				//},
	        				//双击事件
	        				//onDblClickRow :function(rowIndex,rowData){
	        				//	alert("双击");
	        				//}
	        			});
	            		
	        			//设置分页控件 
	        		    var p = $('#grid').datagrid('getPager'); 
	        		    $(p).pagination({ 
	        		        pageSize: 10,//每页显示的记录条数，默认为10 
	        		        pageList: [10,20,30],//可以设置每页记录条数的列表 
	        		        beforePageText: '第',//页数文本框前显示的汉字 
	        		        afterPageText: '页    共 {pages} 页', 
	        		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	        		    }); 
	            	}
			 });
		}
		
		// 定义列
		var columns = [ [ {
			field : 'id',
			checkbox : true
		},{
			field : "mails",
			title : "邮箱",
			width : 344,
			align : 'center'
		} ] ];
		
		//发送邮件
		function sendMails(){
			var selections = $('#grid').datagrid('getSelections');//返回所有被选中的行
			var mails = "";
			for(var i = 0;i<selections.length;i++){
				mails = mails + "," + selections[i].mails;
			}
			$.ajax({
            	type:"post",
            	url:"pcAndMail_sendMail.do",
            	datatype:"json",
            	data:"params=" + mails,
            	error:function(msg){
            		$.messager.alert('警告','发送失败！','warning'); 
            	},
            	success:function(data){
            		$.messager.show({
            			title:'通知',
            			msg:'发送成功！',
            			timeout:2000,
            			showType:'slide'
            		});
            	}
			});
		}
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>增加案列</title>

	<script language="javascript" src="/jiong/js/ckeditor_4.7.1/ckeditor_standard/ckeditor.js" charset="utf-8"></script>

	<script type="text/javascript" src="/jiong/js/BootStrap/jquery.min.js"></script>
	<link rel="stylesheet" href="/jiong/js/BootStrap/css/bootstrap.min.css" />
	<script type="text/javascript" src="/jiong/js/BootStrap/js/bootstrap.min.js"></script>	
	
	<link rel="stylesheet" href="/jiong/js/BootStrap/fileinput/css/fileinput.css"/>
	<script type="text/javascript" src="/jiong/js/BootStrap/fileinput/js/fileinput.js"></script>
	<script type="text/javascript" src="/jiong/js/BootStrap/fileinput/js/locales/zh.js"></script>
	
	<script type="text/javascript" src="/jiong/js/layui/layer/layer.js"></script>
	<%
		String id = request.getParameter("id");
	%>
</head>
<body>
	<div style="width: 90%;margin-top: 30px;" align="center">
		<div style="float: left;width: 20%">
			<img src="/jiong/images/back.png" onclick="gotoIndex()" title="返回首页" style="cursor: pointer;"/>
		</div>
		<div style="float: left;width: 60%">
			<form class="form-horizontal" method="post" id="itemFrom">
				<div class="form-group" style="display: none;">
					<label for="id" class="col-sm-2 control-label">主键</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="id" name="id"/>
					</div>
				</div>
				<div class="form-group">
					<label for="itemName" class="col-sm-2 control-label">名称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="itemName" name="itemName"/>
					</div>
				</div>
				<div class="form-group">
					<label for="itemArr" class="col-sm-2 control-label">地址</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="itemArr" name="itemArr"/>
					</div>
					<img src="/jiong/images/toDetail.png" onclick="gotoDemo()" title="查看Demo" 
						style="cursor: pointer;display: inline;float: left;margin-top: 2px;"/>
				</div>
				<div class="form-group">
					<label for="itemDsc" class="col-sm-2 control-label">简单描述</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="itemDsc" name="itemDsc"/>
					</div>
				</div>
				<div class="form-group">
					<label for="itemDsc" class="col-sm-2 control-label">图片</label>
					<div class="col-sm-10">
						<input id="myUpload" name="myUpload" type="file" />  
						<p class="help-block" style="float: left;">请上传336 x 210的图片</p>
					</div>
				</div>
				<input type="hidden" id="img_path" name="img_path"/>
				<div class="form-group">
					<label for="creatTime" class="col-sm-2 control-label">建立日期</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="creatTime" name="creatTime" readonly/>
					</div>
					<label for="updateTime" class="col-sm-2 control-label">修改日期</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="updateTime" name="updateTime" readonly/>
					</div>
				</div>
				<div class="form-group">
				    <label for="itemTxt" class="col-sm-2 control-label">详细描述</label>
				    <div class="col-sm-10">
				    	<textarea class="ckeditor" id="itemTxt" name="itemTxt"></textarea>
				    </div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-default" id="save">保存</button>
						<%
							if("".equals(id)||id==null){//如果主键值不存在显示清空
						%>		
							<button type="reset" class="btn btn-default" style="margin-left: 30px;" id="reset">清空</button>
						<%
							}else{//否则显示删除
						%>
							<button type="button" class="btn btn-default" style="margin-left: 30px;" id="delete">删除</button>
						<%
							}
						%>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){ 
			var id = <%=id %>;
			if(id!=null && id!="" && id!=undefined){
				$.ajax({
			        type: "POST",
			        url:"item_showData.do",
			        data:"id=" + id,
			        datatype:"json",
			        error: function(msg) {
			        	layer.msg("出错了");
			        },
			        success: function(data) {
			        	showData(data);
			        }
			    });
			}else{
				showPhotos("");//初始化显示控件
			}
		}); 
		
		//处理CKEDITOR的值
		function CKupdate() {
	        for (instance in CKEDITOR.instances){
		        CKEDITOR.instances.itemTxt.updateElement();
	        }
	    }
		 
		//重置
		$("#reset").click(function() {
			CKEDITOR.instances.itemTxt.setData('');//清空ckeditor中内容
		});
		
		//删除
		$("#delete").click(function() {
			layer.confirm("您真的确定要删除吗？\n\n请确认！", {
			  	btn: ["确认","取消"] //按钮
			}, function(){
				var id = <%=id %>;
				$.ajax({
			        type: "POST",
			        url:"item_deleteItem.do",
			        data:"id=" + id,
			        datatype:"json",
			        error: function(msg) {
			            layer.alert("删除失败！", {
			        		skin: 'layui-layer-molv', //样式类名
			        		closeBtn: 0
			        	});
			        },
			        success: function(data) {
			        	layer.msg("删除成功",{offset: 'lb'});
			        	window.location.href = "/jiong/index.jsp";
			        }
			    });
			});
		});
		
		//保存
		$("#save").click(function() {
			//解决异步提交时，使用$("#itemFrom").serialize()，无法获取CKeditor的值的问题
			CKupdate();
			$.ajax({
		        type: "POST",
		        url:"item_saveOrUpdate.do",
		        data:$("#itemFrom").serialize(),
		        datatype:"json",
		        error: function(msg) {
		        	layer.alert("保存或修改失败", {
		        		skin: "layui-layer-molv", //样式类名
		        		closeBtn: 0
		        	});
		        },
		        success: function(data) {
		        	layer.msg("保存成功",{offset: 'lb'});
		        	showData(data);
		        }
		    });
		});
		
		//富文本的内容
		var htmlData;
		//显示后台返回的信息
		function showData(data){
			htmlData = data.ITEMTXT;
			//这个时候可能控件还没有加载完，所以需要在控件加载完再设置一次值
			CKEDITOR.instances.itemTxt.setData(htmlData);
			document.getElementById("id").value = data.ID;
			document.getElementById("itemName").value = data.ITEMNAME;
			document.getElementById("itemArr").value = data.ITEMARR;
			document.getElementById("itemDsc").value = data.ITEMDSC;
			document.getElementById("creatTime").value = data.CREATTIME;
			document.getElementById("updateTime").value = data.UPDATETIME;
			document.getElementById("img_path").value = data.IMG_PATH;
			showPhotos(data.FULL_PATH)
		}
		
		//控件加载完成
		CKEDITOR.on("instanceReady", function (e) {
			if(htmlData != undefined){//如果是undefined说明先加载了控件，那么就在showData里面设值
				//设置CKeditor的值,该方法必须在CKEDITOR加载完成后设置，否则没有用
				CKEDITOR.instances.itemTxt.setData(htmlData);
			}
		});
		
		//返回到首页
		function gotoIndex(){
			//返回到之前的页面
			//window.history.back(-1); 
			window.location.href = "/jiong/index.jsp";
		}
		
		function gotoDemo(){
			var add = document.getElementById("itemArr").value;
			//打开一个新窗口
			window.open(add);  
		}
		
		function showPhotos(jsonStr) {
			var previewJson = "";
			if(jsonStr != ""){
				previewJson = "<img src='" + jsonStr + "' class='file-preview-image' style='width:340px;height:210px;'>";
			}
			// 具体参数自行查询
			$('#myUpload').fileinput({
				language : 'zh', // 设置语言
				uploadUrl : 'item_upload.do',
				uploadAsync : true,
				showUpload : false,// 是否显示上传按钮
				showRemove : false,// 是否显示删除按钮
				showCaption : true,// 是否显示输入框
				showPreview : true,
				showCancel : true,
				dropZoneEnabled : false,
				maxFileCount : 1,
				initialPreviewShowDelete : true,
				msgFilesTooMany : "选择上传的文件数量 超过允许的最大数值！",
				initialPreview : previewJson,
				previewFileIcon : '<i class="fa fa-file"></i>',
				enctype: 'multipart/form-data',
				method: 'post',
				allowedPreviewTypes : [ 'image' ]
			});
		};
		
		//选择文件后执行方法
		$("#myUpload").on("filebatchselected", function(event, files) {
			$(this).fileinput("upload");//自动上传
		});
		
		$("#myUpload").on("fileuploaded", function(event, data, previewId, index) {
			$("#img_path").val(data.response.fileName);
		});
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<button type="button" id="ocUpload" name="upload">上传</button>

<script type="text/javascript" src="/jiong/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" src="/jiong/page/upload/js/jquery_ocupload.js"></script>
<script type="text/javascript">
	$("#ocUpload").upload({
	    name: "upload",
	    action: "/jiong/ocupload.servlet?method=ocUpload",
	    enctype: "multipart/form-data", // 编码格式
	    autoSubmit: true, // 选中文件提交表单
	    onComplete: function(date) {// 请求完成时 调用函数
	    	// TODO IE上传成功后，没有调用该方法，且显示下载servlet,其他浏览器正常
	        if(date=="success"){
	            alert("数据导入成功！");
	        }
	    }
	});
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lucene</title>
<script type="text/javascript" src="/jiong/js/easyUI/jquery.min.js"></script>
</head>
<body>
<button onclick="updateLucene()">更新索引</button>
<script type="text/javascript">
	function updateLucene(){
		$.ajax({
	        type: "POST",
	        url:"item_updateLucene.do",
	        error: function(msg) {
	            alert("更新失败！");
	        },
	        success: function(data) {
	        	alert("更新成功！");
	        }
	    });
	}
</script>
</body>
</html>
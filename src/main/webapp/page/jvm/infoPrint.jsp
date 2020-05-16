<%@page import="com.jianghu.other.jvm.JavaClassExecuter"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>信息打印</title>
</head>
<body>
<%
	InputStream is = new FileInputStream("class文件位置");
	byte[] b = new byte[is.available()];
	is.read(b);
	is.close();
	
	out.println("<textarea style='width:1000px;height=800px'>");
	out.println(JavaClassExecuter.execute(b));
	out.println("</textarea>");
	
%>
</body>
</html>
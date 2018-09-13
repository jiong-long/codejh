<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线用户</title>
</head>
<body>
<h1>当前在线人数列表</h1>
<h3>当前用户:${user.infactname }</h3>
<c:forEach items="${map }" var="entry">
	${entry.key.username }
	<c:if test="${user.username == 'sys'}">
		<!-- servlet 方式访问后台，需要在前面加上contextPath -->
		<!-- 由于struts2的存在，所以需要后缀，否则会被struts2拦截 -->
		<a href="/jiong/removePerson.servlet?username=${entry.key.username }">下线</a><br/>
	</c:if>
</c:forEach>
</body>
</html>
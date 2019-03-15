<%@page import="com.jianghu.core.func.single.Location"%>
<%@page import="com.jianghu.domain.lucene.LuceneField"%>
<%@page import="com.jianghu.core.tools.LuceneUtil"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.jianghu.core.Tools"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="com.jianghu.domain.basic.User"%>
<%@page import="com.jianghu.core.Database"%>
<%@page import="java.sql.ResultSet"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>江湖</title>
	<script type="text/javascript" src="/jiong/js/BootStrap/jquery.min.js"></script>
	
	<link rel="stylesheet" href="/jiong/js/BootStrap/css/bootstrap.min.css"/>
	<script type="text/javascript" src="/jiong/js/BootStrap/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="/jiong/js/layui/layim/build/layui.js"></script>
  	<link rel="stylesheet" href="/jiong/js/layui/layim/build/css/layui.css">
	
	<link rel="stylesheet" type="text/css" href="/jiong/index.css"/>

	<link rel="stylesheet" href="/jiong/js/JqueryUI/jquery-ui.css">
  	<link rel="stylesheet" href="/jiong/js/JqueryUI/style.css">
  	<script src="/jiong/js/JqueryUI/jquery-ui.js"></script>
  	
	<link rel="stylesheet" href="/jiong/page/3D/css/index.css" />

	<script src="/jiong/js/core/jianghu.js"></script>
  	
  	<%
  		String getNameSql = "select itemname from bc_item";
  		ResultSet nameRs = Database.executeQuery(getNameSql);
  		JSONArray array = new JSONArray();
  		while(nameRs.next()){
  			array.put(nameRs.getString("itemname"));
  		}
  		
  		//获取搜索条件
  		String params = Tools.null2Empty(request.getParameter("params"));
		// 解决get方式传中文到后台乱码的问题
		params = URLDecoder.decode(params,"UTF-8");
		
		String webPath = Tools.getWebPath(request);
		
		//获取客户端所在的市
		String location = Location.getLocationFromRequest(request);
  	%>
  	<script>
	  $(function() {
		//输入补全
	    var availableTags = <%=array %>;
	    $("#titleContent").autocomplete({
	      	source: availableTags
	    });
	  });
  	</script>
</head>
<body style="background: url(/jiong/page/3D/images/bg_3d.png);">
	<div class="jumbotron1">
		<div class="pageTop">
			<div class="topLogo">
				<span class="topTitle" onclick="toIndexPage()" title="返回首页">江湖</span>
				<span class="topLocation" id="location" title="查看天气"><%=location %></span>
			</div>
			<div class="topToolBar">
				<ul class="nav navbar-nav navbar-right">
					<li class="liClass" id="add">增加</li>
					<%
						//获取session中的用户
						User user = (User)request.getSession().getAttribute("user");
						String showText = "登录";
						if(user != null){
							showText = user.getInfactname();
						}else{//未登录，变相实现AutoLoginFilter功能
							Cookie cookie = Tools.findCookieByName(request.getCookies(), "autologin");
							if (cookie != null) {
								String username = cookie.getValue().split("\\.")[0];
								username = URLDecoder.decode(username, "utf-8");
								// cookie中存放的是密文
								String password = cookie.getValue().split("\\.")[1];
								String sql = "select * from bc_user where username = ? and password = ?";
								try {
									List<User> userList = Database.executeQuery(sql, User.class, username, password);
									if (userList.size() > 0) {
										showText = userList.get(0).getInfactname();
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					%>
					<li class="liClass" id="login"><%=showText %></li>
				</ul>
			</div>
		</div>
		<div class="input-group">
			<!-- 触发键盘enter点击事件 -->
	   		<input type="text" class="form-control input-lg" id="titleContent" value="<%=params %>" onkeydown="javascript:if(event.keyCode==13) searchClick();"/>
	   		<span class="input-group-addon btn btn-primary" onclick="searchClick()">搜索</span>
		</div>
	</div>
	
	<%
		StringBuffer buffer = new StringBuffer();
		buffer.append("<div class='lb_gl'><ul id='zturn' class='poster-list1'>");
		String idStr = "";
		if(!"".equals(params)){//有条件使用lucene搜索
			LuceneUtil luceneUtil = LuceneUtil.instance();
			List<LuceneField> list = luceneUtil.search(params);
			for(LuceneField luceneField : list){
				idStr += "," + luceneField.getNo() ;
			}
		}
		//sqlite 日期装换 strftime('%Y-%m-%d', updatetime,'localtime') updatetime
		String getAllSql = "select id, itemname, itemdsc, seecount, date_format(updatetime,'%Y-%m-%d') updatetime, img_path from bc_item ";
		if("".equals(idStr)){
			getAllSql += " order by updateTime desc";
		}else{
			getAllSql += " where id in("+idStr.substring(1)+") order by updateTime desc";
		}
		ResultSet rs = Database.executeQuery(getAllSql);
		while(rs.next()){
			String id = rs.getString("id");
			String itemname = rs.getString("itemname");
			String itemdsc = Tools.null2Empty(rs.getString("itemdsc"));
			String seecount = rs.getString("seecount");
			String updatetime = rs.getString("updatetime");
			String img_path = Tools.null2Empty(rs.getString("img_path"));
			if("".equals(img_path)){
				img_path = "/jiong/page/3D/images/a2.png";
			}else{
				img_path = "/jiong/images/item/" + img_path;
			}
			buffer.append("<li class='poster-item1 zturn-item'><p class='xxgy' onclick='gotoItem("+id+")'>"+itemname+
					"</p><div class='for_btn1'>"+
				"<img src='"+img_path+"' width='100%'></div><div class='students_star'><p class='cell_list1'>"+
				"<span class='lf'>更新日期：<span class='darks'>"+updatetime+"</span></span></p><p "+
				"class='cell_list1'><span class=''>查看次数：<span class='darks'>"+seecount+
				"</span></span></p><div class='zwjs'>"+itemdsc+"</div></div></li>");
		}
		Database.closeresouce(rs);
		buffer.append("</ul></div>");
		out.println(buffer.toString());
	%>
	
	<script type="text/javascript" src="/jiong/index.js"></script>
</body>
</html>

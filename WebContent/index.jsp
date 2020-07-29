<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>SCM</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	

  </head>
  
  <frameset rows="23,10,*" cols="*" frameborder="NO" border="0" framespacing="0" id="controlRv">
  <frame src="htm/title.htm" name="topFrame" scrolling="NO" noresize >
  <frame src="htm/dynamic_bar_h.htm" scrolling="no" name="sidebar_r" noresize>
  <frameset cols="120,10,*" frameborder="NO" border="0" framespacing="0"  id="controlFv">
    <frame src="htm/catalog.htm" name="leftFrame" scrolling="NO" noresize>
	<frame src="htm/dynamic_bar_v.htm" scrolling="no"  name="sidebar_v" noresize>
    <frame src="login.jsp" name="mainFrame" scrolling="auto">
  </frameset>
</frameset>
<noframes></noframes>
</html>

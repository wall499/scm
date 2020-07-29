<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>${month}产品库存</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="js/common.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <table width="100%"  border="0" cellpadding="0" cellspacing="0">
 	 <tr>
    <td nowrap class="title1">您的位置：工作台面－－${month}入库报表</td>
 	 </tr>
  </table>
  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="120px" nowrap class="toolbar">你好，${user.name}</td>
	<td nowrap class="toolbar">&nbsp;</td>
    <td width="20px" class="toolbar">&nbsp;</td>
  </tr>
</table>
	<input type="hidden" id="productCode" value="${productCode}">
	<table width="100%"  border="0" align="center" cellspacing="1">
	  <tr>
	    <td class="title2"></td>
	  </tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="2">
	<tr><td colspan="7" align=center>${month}库存详细列表</td></tr>
	<tr>
    <td class="title1">产品编号</td>
    <td class="title1">类别序列号</td>
	<td class="title1">产品名称</td>
	<td class="title1">数量单位</td>
    <td class="title1">产品单价</td>
    <td class="title1">创建日期</td>
    <td class="title1">描述</td>
	</tr>
	<c:forEach items="${products}" var="item">
	<tr>
	<td align="center">${item.productCode}</td>
	<td align="center">${item.categoryID}</td>
	<td align="center">${item.name}</td>
	<td align="center">${item.unitName}</td>
	<td align="center">${item.price}</td>
	<td align="center">${item.createDate}</td>
	<td align="center">${item.remark}</td>
	</tr>
	</c:forEach>
	</table><br/>
	<table width="100%"  border="0" align="center" cellspacing="1">
	  <tr>
	    <td class="title2"></td>
	  </tr>
	</table><br/>
	<div align="center">
    <input type="button" id="print" value="打印" onclick="window.print()"/>
    <input type="button" id="close" value="关闭" onclick="window.close()"/>
	</div>
  </body>
</html>

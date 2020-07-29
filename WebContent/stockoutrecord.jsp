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
    
    <title>${month}出库报表</title>
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
    <td nowrap class="title1">您的位置：工作台面－－${month}出库报表</td>
 	 </tr>
  </table>
  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="120px" nowrap class="toolbar">你好，${user.name}</td>
	<td nowrap class="toolbar">&nbsp;</td>
    <td width="20px" class="toolbar">&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="2">
	<tr><td colspan="3" align=center>${month}出库主信息</td></tr>
	<tr>
    <td class="title1">出库单据数</td>
    <td class="title1">产品总数量</td>
	<td class="title1">出库产品总金额</td>
	</tr>
	<tr>
	<td align="center">${recordMain["orderNum"]}</td>
	<td align="center">${recordMain["totalNum"]}</td>
	<td align="center">${recordMain["totalPrice"]}</td>
	</tr>
	</table><br/>
	<input type="hidden" id="productCode" value="${productCode}">
	<table width="100%"  border="0" align="center" cellspacing="1">
	  <tr>
	    <td class="title2"></td>
	  </tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="2">
	<tr><td colspan="6" align=center>${month}出库详细列表</td></tr>
	<tr>
    <td class="title1">销售单号</td>
    <td class="title1">出库日期</td>
	<td class="title1">产品编号</td>
	<td class="title1">产品名称</td>
    <td class="title1">产品数</td>
    <td class="title1">产品总金额</td>
	</tr>
	<c:forEach items="${recordDetails}" var="item">
	<tr>
	<td align="center">${item.orderCode}</td>
	<td align="center">${item.stockTime}</td>
	<td align="center">${item.productCode}</td>
	<td align="center">${item.productName}</td>
	<td align="center">${item.num}</td>
	<td align="center">${item.itemPrice}</td>
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

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
    
    <title>产品库存记录</title>
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
	<script type="text/javascript">
		function ingo(){
			var productCode=document.getElementById("productCode").value;
			var text=document.getElementById("inPageNow");
			var inPageNow=text.value;
			location.href="/200429SCM_BS/servlet/StockMessage?inPageNow="+inPageNow+"&productCode="+productCode;
		}
		function outgo(){
			var productCode=document.getElementById("productCode").value;
			var text=document.getElementById("outPageNow");
			var outPageNow=text.value;
			location.href="/200429SCM_BS/servlet/StockMessage?outPageNow="+outPageNow+"&productCode="+productCode;
		}
	</script>
  </head>
  
  <body>
     <table width="100%"  border="0" cellpadding="0" cellspacing="0">
 	 <tr>
    <td nowrap class="title1">您的位置：工作台面－－库存查询－－产品库存变更记录</td>
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
	<tr><td colspan="5" align=center>入库记录</td></tr>
	<tr>
    <td class="title1">入库时间</td>
    <td class="title1">相关采购单号</td>
	<td class="title1">入库经手人</td>
	<td class="title1">入库数量</td>
    <td class="title1">入库类型</td>
	</tr>
	<c:forEach items="${inStockRecords}" var="item">
	<tr>
	<td align="center">${item.stockTime}</td>
	<td align="center">${item.orderCode}</td>
	<td align="center">${item.createUser}</td>
	<td align="center">${item.stockNum}</td>
	<td align="center"><c:choose><c:when test="${item.stockType==0}">盘点入库</c:when>
	<c:when test="${item.stockType==1}">采购入库</c:when></c:choose></td>
	</tr>
	</c:forEach>
	</table><br/>
	<input type="hidden" id="productCode" value="${productCode}">
	<center>
	<a href="servlet/StockMessage?inPageNow=1&productCode=${productCode}">首页</a>
	<a href="servlet/StockMessage?inPageNow=${inPageNow-1}&productCode=${productCode}">上一页</a>
	<a href="servlet/StockMessage?inPageNow=${inPageNow+1}&productCode=${productCode}">下一页</a>
	<a href="servlet/StockMessage?inPageNow=${inPageCount}&productCode=${productCode}">末页</a>
	第${inPageNow}页/共${inPageCount}页
	<input size="2" id="inPageNow"/>
	<input type="button" value="GO" onclick="ingo()"/>
	</center><br/>
	<table width="100%"  border="0" align="center" cellspacing="1">
	  <tr>
	    <td class="title2"></td>
	  </tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="2">
	<tr><td colspan="5" align=center>出库记录</td></tr>
	<tr>
    <td class="title1">出库时间</td>
    <td class="title1">相关销售单号</td>
	<td class="title1">出库经手人</td>
	<td class="title1">出库数量</td>
    <td class="title1">出库类型</td>
	</tr>
	<c:forEach items="${outStockRecords}" var="item">
	<tr>
	<td align="center">${item.stockTime}</td>
	<td align="center">${item.orderCode}</td>
	<td align="center">${item.createUser}</td>
	<td align="center">${item.stockNum}</td>
	<td align="center"><c:choose><c:when test="${item.stockType==0}">盘点出库</c:when>
	<c:when test="${item.stockType==1}">销售出库</c:when></c:choose></td>
	</tr>
	</c:forEach>
	</table><br/>
	<center>
	<a href="servlet/StockMessage?outPageNow=1&productCode=${productCode}">首页</a>
	<a href="servlet/StockMessage?outPageNow=${outPageNow-1}&productCode=${productCode}">上一页</a>
	<a href="servlet/StockMessage?outPageNow=${outPageNow+1}&productCode=${productCode}">下一页</a>
	<a href="servlet/StockMessage?outPageNow=${outPageCount}&productCode=${productCode}">末页</a>
	第${outPageNow}页/共${outPageCount}页
	<input size="2" id="outPageNow"/>
	<input type="button" value="GO" onclick="outgo()"/>
	</center>
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

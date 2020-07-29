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
    
    <title>库存查询</title>
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
		function go(){
			var text=document.getElementById("pageNow");
			var pageNow=text.value;
			location.href="/200429SCM_BS/servlet/StockServlet?pageNow="+pageNow;
		}
		function click2(){
			document.getElementById("keywords").readOnly=true;
			document.getElementById("min").readOnly=false;
			document.getElementById("max").readOnly=false;
		}
		function click1(){
			document.getElementById("keywords").readOnly=false;
			document.getElementById("min").readOnly=true;
			document.getElementById("max").readOnly=true;
		}
		function search(){
			//var conditions=document.getElementsByName("condition");
			var text=document.getElementById("text").value;
			var min=document.getElementById("min").value;
			min=min==""?0:min;
			var max=document.getElementById("max").value;
			max=max==""?"infinity":max;
			var productCode=document.getElementById("productCode");
			var productName=document.getElementById("productName");
			var range=document.getElementById("range");
			if(range.checked==true){
				if((max!="infinity"&&isNaN(max))||isNaN(min)){
					alert("请输入数字!");
					return;
				}else if(min<0||max<0){
					alert("请输入正整数！");
					return;
				}else if(parseInt(min)>parseInt(max)){
					alert("min="+min+"  max="+max);
					alert("范围输入有误！");
					return;
				}
				location.href="/200429SCM_BS/servlet/StockServlet?min="+min+"&max="+max+"&condition=range";
			}else if(productCode.checked==true){
				document.getElementById("keywords").value=text;
				document.getElementById("condition").value="productCode";
				document.getElementById("form1").submit();
			}else if(productName.checked==true){
				document.getElementById("keywords").value=text;
				document.getElementById("condition").value="productName";
				document.getElementById("form1").submit();
			}
			
		}
	</script>
  </head>
  
  <body>
    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
 	 <tr>
    <td nowrap class="title1">您的位置：工作台面－－库存查询</td>
 	 </tr>
  </table>
  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="120px" nowrap class="toolbar">你好，${user.name}</td>
	<td nowrap class="toolbar">&nbsp;</td>
    <td width="20px" class="toolbar">&nbsp;</td>
  </tr>
</table>
<form id="form1" action="/200429SCM_BS/servlet/StockServlet" method="post">
	<input type="hidden" id="keywords" name="keywords">
	<input type="hidden" id="condition" name="condition">
</form>
<table width="100%" border="0" cellpadding="0" cellspacing="2">
	<tr>
    <td class="title1">产品编号</td>
    <td class="title1">产品名称</td>
	<td class="title1">库存数量</td>
	<td class="title1">数量单位</td>
    <td class="title1">采购在途数</td>
    <td class="title1">预销售数</td>
	</tr>
	<c:forEach items="${stockInfos}" var="item">
	<tr>
	<td align="center"><a href="servlet/StockMessage?productCode=${item.productCode}" target="_blank">${item.productCode}</a></td>
	<td align="center">${item.name}</td>
	<td align="center">${item.num}</td>
	<td align="center">${item.unitName}</td>
	<td align="center">${item.poNum}</td>
	<td align="center">${item.soNum}</td>
	</tr>
	</c:forEach>
	</table><br/>
	<center>
	<a href="servlet/StockServlet?pageNow=1&condition=${condition}&keywords=${keywords}&min=${min}&max=${max}">首页</a>
	<a href="servlet/StockServlet?pageNow=${pageNow-1}&condition=${condition}&min=${min}&max=${max}">上一页</a>
	<a href="servlet/StockServlet?pageNow=${pageNow+1}&condition=${condition}&keywords=${keywords}&min=${min}&max=${max}">下一页</a>
	<a href="servlet/StockServlet?pageNow=${pageCount}&condition=${condition}&keywords=${keywords}&min=${min}&max=${max}">末页</a>
	第${pageNow}页/共${pageCount}页
	<input size="2" id="pageNow"/>
	<input type="button" value="GO" onclick="go()"/>
	</center>
	
	<table width="100%"  border="0" align="center" cellspacing="1">
	  <tr>
	    <td class="title2"></td>
	  </tr>
	</table><br/>
	<table width="100%"  border="0" align="center" cellspacing="1">
	  <tr>
	    <td rowspan="2" align="center" valign="middle">库存查询:</td>
	    <td><input type="text" id="text">
	    <input type="radio" name="radio" id="productCode" checked onclick="click1()">产品编号
    	<input type="radio" name="radio" id="productName" onclick="click1()">产品名称</td>
	  </tr>
	  <tr>
	  	<td><input type="text" size="6" id="min" readOnly>
	  	— <input type="text" size="6" id="max" readOnly>
	  	<input type="radio" name="radio" id="range" onclick="click2()">库存数量范围
	  </tr>
	</table><br/>
	<div align="center">
	<input type="button" name="search" value="查询" onclick="search()"/>
    <input type="button" id="print" value="打印" onclick="window.print()"/>
    <input type="button" id="close" value="关闭" onclick="window.close()"/>
	</div>
  </body>
</html>

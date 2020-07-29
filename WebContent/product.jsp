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
    
    <title>产品管理</title>
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
	

	//新增产品
		function add(){
			document.getElementById("mess").submit();
			//location.href="/200429SCM_BS/th_detail.jsp";
		}
		function del(obj){
			var flag=window.confirm("确认删除?");
			if(flag){
				var delR=obj.parentNode.parentNode;
				var productCode=delR.cells[0].innerHTML;
				location.href="/200429SCM_BS/servlet/ProductDelete?productCode="+productCode;
			}
		}
		
		//分页
		function go(){
			var text=document.getElementById("pageNow");
			var pageNow=text.value;
			location.href="/200429SCM_BS/servlet/ProductUpdate?pageNow="+pageNow;
		}
		
		function update(obj){
			var str='';
			var h=document.getElementById("proMess");
			
			var tr=obj.parentNode.parentNode;
			for(var i=0;i<tr.cells.length-1;i++){
				//产品信息
				if(i==0)
					str=tr.cells[i].innerHTML;
				else
					str+=","+tr.cells[i].innerHTML;			
			}
			h.value=str;
			document.getElementById("mess").submit();
		}
	</script>
  </head>
  
  <body >
    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td nowrap class="title1">您的位置：工作台面－－产品管理</td>
  </tr>
  </table>
  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="120px" nowrap class="toolbar">你好，${user.name}</td>
    <td width="30px" nowrap class="toolbar">&nbsp;</td>
	<td nowrap class="toolbar">&nbsp;</td>
    <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="add()"><img src="images/new.gif">新增</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="2">
	<tr>
	<td class="title1">产品编号</td>
    <td class="title1">类别序列号</td>
    <td class="title1">名称</td>
	<td class="title1">数量单位</td>
	<td class="title1">销售价</td>
	<td class="title1">添加日期</td>
	<td class="title1">产品描述</td>
	<td class="title1">库存数</td>
	<td class="title1">采购在途数</td>
	<td class="title1">销售待发数</td>
	<td class="title1">操作</td>
	</tr>
	<c:forEach  items="${products}" var="item">
	<tr>
	<td align="center">${item.productCode}</td>
	<td align="center">${item.categoryName}</td>
	<td align="center">${item.name}</td>
	<td align="center">${item.unitName}</td>
	<td align="center">${item.price}</td>
	<td align="center">${item.createDate}</td>
	<td align="center">${item.remark}</td>
	<td align="center">${item.num}</td>
	<td align="center">${item.poNum}</td>
	<td align="center">${item.soNum}</td>
	<td align="center">
	<input type="button" value="删除" onclick="del(this)">
	<input type="button" value="修改" onclick="update(this)"/>
	</td>
	</tr>
	</c:forEach>
	</table><br/>
	<center>
	<a href="servlet/ProductUpdate?pageNow=1">首页</a>
	<a href="servlet/ProductUpdate?pageNow=${pageNow-1}">上一页</a>
	<a href="servlet/ProductUpdate?pageNow=${pageNow+1}">下一页</a>
	<a href="servlet/ProductUpdate?pageNow=${pageCount}">末页</a>
	第${pageNow}页/共${pageCount}页
	<input size="2" id="pageNow"/>
	<input type="button" value="GO" onclick="go()"/>
	</center>
	
	<form action="/200429SCM_BS/servlet/ProductUpdate" method="post" id="mess">
		<input type="hidden" id="proMess" name="proMess">
	</form>
	<table width="100%"  border="0" align="center" cellspacing="1">
	  <tr>
	    <td class="title2"></td>
	  </tr>
	</table>

  </body>
</html>

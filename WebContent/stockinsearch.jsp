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
    
    <title>入库报表</title>
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
		function sele(){
			var month='';
			var sel=document.getElementById("method");
			for(var i=0;i<sel.options.length;i++){
				if(sel.options[i].selected){
					month=sel.options[i].innerHTML;
					location.href="/200429SCM_BS/servlet/StockInReport?month="+month;
					return;
				}
			}
		}
	</script>
  </head>
  
   <body>
     <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td nowrap class="title1">您的位置：工作台面－－入库报表</td>
  </tr>
  </table>
  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="120px" nowrap class="toolbar">你好，${user.name}</td>
    <td width="30px" nowrap class="toolbar">&nbsp;</td>
    <td width="20px" nowrap class="toolbar"></td>
	<td nowrap class="toolbar">&nbsp;</td>
    <td width="20px" class="toolbar">&nbsp;</td>
  </tr>
</table><br/><br/>
  <table width="375" border="1" align="center" cellpadding="1" cellspacing="0" bordercolor="#FF9999">
  <tr>
    <td width="369" align="center" class="style9">按入库月份查询</td>
  </tr>
  <tr>
    <td height="30" align="center">
      <table width="350" border="0" cellpadding="1" cellspacing="0">
        <tr>
          <td width="103" height="21" align="right" valign="middle" class="code">请输选择月份:</td>
          <td width="163" align="center" valign="middle"><label>
            <select id="method" name="select" id="select">
    		<option >入库月份</option>
    		<c:forEach items="${months}" var="month">
    		<option value="${month}" >${month}</option>
    		</c:forEach>
    		</select>
          </label></td>
          <td width="78" align="center"><label>
            <input name="query" type="button" class="buttonstyle" value="查询" onclick="sele()">
          </label></td>
        </tr>
      </table>
    	</td>
  </tr>
</table>
  </body>
</html>

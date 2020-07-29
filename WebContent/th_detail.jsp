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
    
    <title>更新产品</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="js/common.js" ></script>
<!-- 	<script type="text/javascript" src="js/productDiv.js" ></script> -->
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  
  <body onload="load()">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td nowrap class="title1">您的位置：工作台面－－产品管理</td>
  </tr>
</table>
<div class="formVisiblitly" id="formDiv"></div>
<form action="/200429SCM_BS/servlet/ProductAdd" name="mainFrm">
<input type="hidden" id="type" name="type">
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="30px" nowrap class="toolbar">&nbsp;</td>
    <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="document.mainFrm.bc.click()"><img src="images/save.gif">保存</td>
    <td width="20px" nowrap class="toolbar">|</td>
    <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="document.mainFrm.dy.click()"><img src="images/search.gif">打印</td>
    <td width="20px" nowrap class="toolbar">|</td>
    <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="document.mainFrm.cz.click()"><img src="images/reset.gif">重置</td>
    <td width="20px" nowrap class="toolbar">|</td>
    <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="document.mainFrm.gb.click()"><img src="images/cancel.gif">关闭</td>
	<td nowrap class="toolbar">&nbsp;</td>
  </tr>
</table>

<table id="headTable" width="100%"  border="0" align="center" class="a1">
  <tr align="justify">
    <td>产品编号</td>
    <td><input type="text" id="productCode" name="productCode" value="${message[0]}"  size="15" readOnly/>
      <span class="requred_symbol">*</span></td>
    <td> 类别序列号</td>
    <td>
     <select id="method" name="categoryName" id="select">
    		<option >选择产品类别</option>
    		<c:forEach items="${categoryNames}" var="categoryName">
    		<option value="${categoryName}">${categoryName}</option>
    		</c:forEach>
      <span class="requred_symbol">*</span></td>
    <td>产品名称</td>
    <td><input type="text" name="name" value="${message[2]}" size="15"/>
      <span class="requred_symbol">*</span></td>
    <td>数量单位</td>
    <td><input  name="unitName" value="${message[3]}" type="text" size="15" >
        <span class="requred_symbol">*</span></td>
  </tr>
  <tr align="justify" id="redundancy0">
    <td>销售价</td>
    <td><input type="text" name="price" value="${message[4]}" size="15" onblur="isFloat(this)"/>
        <span class="requred_symbol">*</span></td>
    <td> 添加日期</td>
    <td><input type="text" name="createDate" value="${message[5]}" size="15"  readonly="readonly">
      <a href="javascript:void(0)" onclick="gfPop.fPopCalendar(document.all.createDate);return false;">
      <img name="popcal"  src="images/selectDate.gif" width="15" height="12" border="0" alt=""></a>
	</td>
    <td >库存数</td>
    <td  ><input name="num" value="${message[7]}" type="text" size="15" onblur="isInteger(this)"></td>
   <td >采购在途数</td>
    <td ><input name="poNum" value="${message[8]}" type="text" size="15" onblur="isInteger(this)"></td>
   </tr>
  <tr align="justify" id="redundancy">
    <td>销售待发数</td>
    <td><input name="soNum" value="${message[9]}" type="text" size="15" onblur="isInteger(this)"/></td>
   
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr align="justify">
    <td>产品描述</td>
    <td colspan="7"><input name="remark" value="${message[6]}" size="120"></td>
  </tr>
  <tr>
  	<td class="title2"></td>
  </tr>
</table>

<table width="100%"  border="0" align="center" cellspacing="1">
  <tr>
    <td class="title2"></td>
  </tr>
</table>
<br>
<div align="center">
    
    <input type="button" id="bc" value="保存" onClick="save()"/>
    <input type="button" id="dy" value="打印" onClick="window.print()"/>
    <input name="reset"  id="cz" value="重置" type="reset"/>
    <input type="button" id="gb" value="关闭" onClick="window.close()"/>
</div>
</form>
<iframe width=174 height=189 name="gToday:normal:agenda.js" id="gToday:normal:agenda.js" src="common/calendar/ipopeng.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"></iframe>
  </body>
</html>
<script type="text/javascript">
		//初始页面为新增页面，productcode可修改
		function load(){
			var text=document.getElementById("productCode");
			if(text.value==""){
				document.getElementById("type").value="add";
				text.readOnly=false;
				//库存数、采购在途数、销售在途数
				/* document.getElementsByName("num")[0].style.display="none";
				document.getElementsByName("poNum")[0].style.display="none";
				document.getElementsByName("soNum")[0].style.display="none"; */
				document.getElementById("redundancy0").cells[4].style.display="none";
				document.getElementById("redundancy0").cells[5].style.display="none";
				document.getElementById("redundancy0").cells[6].style.display="none";
				document.getElementById("redundancy0").cells[7].style.display="none";
				document.getElementById("redundancy").style.display="none";
			}else{
				document.getElementById("type").value="update";
				document.getElementById("redundancy0").cells[4].style.display="none";
				document.getElementById("redundancy0").cells[5].style.display="none";
				document.getElementById("redundancy0").cells[6].style.display="none";
				document.getElementById("redundancy0").cells[7].style.display="none";
				document.getElementById("redundancy").style.display="none";
			}
		}
		//判断price 是否为float
		function isFloat(obj)
		{
	//判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
		var reg= /^[0-9]+.?[0-9]*$/; 
		if (!reg.test(obj.value))
		{
		alert("请输入浮点数");
		}
		}
		
		//判断是否是正整数num,ponum,sonum
		function isInteger( obj ){
			var reg = /^\+?[0-9][0-9]*$/;
			if (!reg.test(obj.value))
			{
			alert("请输入整数");
			}
			}
		
		//判断input内是否有值
		function required(){
			var CategoryID=document.getElementsByName("categoryName")[0].value;
			var name=document.getElementsByName("name")[0].value;
			var unitName=document.getElementsByName("unitName")[0].value;
			var price=document.getElementsByName("price")[0].value;
			if(CategoryID==""||name==""||unitName==""||price==""){
				alert("带*的空格必须有值");
				return false;
			}
		return true;
			}
		//确定新增
		function save(){
			var flag;
			flag=required();
			if(flag){
				document.getElementsByName("mainFrm")[0].submit();
			}
			
		}
		
		
	</script>

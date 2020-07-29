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
    
    <title>产品类别总界面</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="js/common.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
    //分页
		function go(){
			var text=document.getElementById("pageNow");
			var pageNow=text.value;
			location.href="/200429SCM_BS/servlet/CategoryServlet?pageNow="+pageNow;
		}
    //新增产品类别界面开启
		function addItem() {
	    var flag=document.getElementById("flag");
	    flag.value="add";
		var d=document.getElementById("addCategory");
		d.style.display="block";
		}
		//新增产品类别界面关闭
		function closeItem(){
			var d=document.getElementById("addCategory");
			d.style.display="none";
			}
		//判断是否是正整数
		function isInteger( str ){
			var reg = /^\+?[0-9][0-9]*$/;
			return reg.test(str);
			}
		
		//判断input内是否有值
		function required(){
			var categoryID=document.getElementById("categoryID").value;
			var name=document.getElementById("name").value;
			var remark=document.getElementById("remark").value;
			if(categoryID==""||name==""||remark==""){
				alert("选项不能为空");
				return false;
			}
		return true;
			}
		//确定新增
		function makeSure(){
			var flag;
			var flag1;
			var categoryID=document.getElementById("categoryID").value;
			//id是整数
			flag=isInteger(categoryID);
			if(flag){
				//空格中都有值
				flag1=required();
				if(flag1)
				document.getElementsByName("addform")[0].submit();
			}else{
				alert("categoryID必须为数字");
			}
			
		}
		//修改产品类别
		function update(obj){
			addItem();
			var categoryID=document.getElementById("categoryID");
			var name=document.getElementById("name");
			var remark=document.getElementById("remark");
			var flag=document.getElementById("flag");
			var tr=obj.parentNode.parentNode;
			categoryID.value=tr.cells[0].innerHTML;
			categoryID.readOnly=true;
			name.value=tr.cells[1].innerHTML;
			remark.value=tr.cells[2].innerHTML;
			flag.value="update";
		}
		//删除产品类别
		function del(obj){
			var flag=window.confirm("确认删除?");
			if(flag){
				var delR=obj.parentNode.parentNode;
				var categoryID=delR.cells[0].innerHTML;
				location.href="/200429SCM_BS/servlet/CategoryDelete?categoryID="+categoryID;
			}
		}
		
	</script>
  </head>
  
  <body>
  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td nowrap class="title1">您的位置：工作台面－－产品类别</td>
  </tr>
  </table>
  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="120px" nowrap class="toolbar" >你好，${user.name}</td>
    <td width="800px" nowrap class="toolbar">&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="2">
	<tr>
    <td class="title1" width="20%">类别序列号</td>
    <td class="title1" width="20%">名称</td>
	<td class="title1" width="50%">描述</td>
	<td class="title1" width="10%">操作</td>
	</tr>
	<c:forEach items="${categories}" var="item">
	<tr>
	<td width="20%" align="center">${item.categoryID}</td>
	<td width="20%" align="center">${item.name}</td>
	<td width="50%" align="center">${item.remark}</td>
	<td width="10%" align="center">
	<input type="button" value="删除" onclick="del(this)">
	<input type="button" value="修改" onclick="update(this)"/>
	</td>
	</tr>
	</c:forEach>
	</table><br/>
	<center>
	<a href="servlet/CategoryServlet?pageNow=1">首页</a>
	<a href="servlet/CategoryServlet?pageNow=${pageNow-1}">上一页</a>
	<a href="servlet/CategoryServlet?pageNow=${pageNow+1}">下一页</a>
	<a href="servlet/CategoryServlet?pageNow=${pageCount}">末页</a>
	第${pageNow}页/共${pageCount}页
	<input size="2" id="pageNow"/>
	<input type="button" value="GO" onclick="go()"/>
	</center>
	<form action="servlet/CategoryUpdate" name="addform" method="post">
	<div align="center" style="display:none" id="addCategory">
	<table width="100%"  border="0" align="center" cellspacing="1" id="detailTable">
	  <tr>
	    <td width="20%" class="toolbar"> 类别序列号 </td>
	    <td width="20%" class="toolbar"> 名称 </td>
	    <td width="55%" class="toolbar"> 描述 </td>
	    <td width="5%" class="toolbar">&nbsp;</td>
	  </tr>
	  <tr>
	  	<td width="20%" class="toolbar"><input type='text' name="categoryID"  id='categoryID' size='25' required  ></td>
	  	<td width="20%" class="toolbar"><input type='text' name="name"  id='name' size='25' required></td>
	    <td width="55%" class="toolbar"><input type='text' name="remark"  id='remark' size='90' required> </td>
	    <td width="5%" class="toolbar"><input type='hidden' name="flag"  id='flag' size='90' required></td>
	  </tr>
	</table>
	</div>
	<table width="100%"  border="0" align="center" cellspacing="1">
	  <tr>
	    <td class="title2"></td>
	  </tr>
	</table><br/>
	<div align="center">
    <input type="button" id="mx" value="添加" onclick="addItem()"/>
    <input type="button" id="qd" value="确定" onclick="makeSure()"/>
    <input type="button" id="dy" value="打印" onclick="window.print()"/>
    <input name="reset"  id="cz" value="重置" type="reset"/>
    <input type="button" id="gb" value="关闭" onclick="closeItem()"/>
	</div>
	</form>
  </body>
</html>

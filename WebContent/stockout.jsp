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
    
    <title>出库登记</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="js/common.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		function go(){
			var text=document.getElementById("pageNow");
			var pageNow=text.value;
			location.href="/200429SCM_BS/servlet/StockOutServlet?pageNow="+pageNow;
		}
		function selectAll(){
			var checkboxes=document.getElementsByName("checkbox");
			for(var i=0;i<checkboxes.length;i++){
				checkboxes[i].checked=true;
			}
		}
		function inverseSelect(){
			var checkboxes=document.getElementsByName("checkbox");
			for(var i=0;i<checkboxes.length;i++){
				checkboxes[i].checked=!checkboxes[i].checked;
			}
		}
		function showDetail(){
			var soID="";
			var strSOID="";
			var checkboxes=document.getElementsByName("checkbox");
			for(var i=0;i<checkboxes.length;i++){
				if(checkboxes[i].checked){
					soID=checkboxes[i].value;
					if(strSOID==""){
						strSOID=soID;
					}else{
						strSOID+=","+soID;
					}
				}
			}
			window.open('/200429SCM_BS/servlet/StockOutDetail?strSOID='+strSOID,'_blank');
		}
// 		出库
		function stockOut(){
			var SOID="";
			var strSOID="";
			//是否勾选了复选框
			checkboxnum();
			var flag=window.confirm("确定出库吗？");
			var checkboxes=document.getElementsByName("checkbox");
				for(var i=0;i<checkboxes.length;i++){
					if(checkboxes[i].checked){
						SOID=checkboxes[i].value;
						
							if(flag){
								
								if(strSOID==""){
									strSOID=SOID;
								}else{
									//选择多个销售单时
									strSOID+=","+SOID;
								}

									
							}
							
						}
					}
				location.href="/200429SCM_BS/servlet/StockOutSave?strSOID="+strSOID;
		}
	
		//确定是否选中了销售单的复选框
		function checkboxnum(){
			var checkNum=0;
			var checkboxes=document.getElementsByName("checkbox");
			for(var i=0;i<checkboxes.length;i++){
				if(checkboxes[i].checked){
					checkNum++;
				}	
			}
			if(checkNum==0){
				alert("请选择要出库的销售单");
			}
		}
	</script>
  </head>
  
   <body>
     <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td nowrap class="title1">您的位置：工作台面－－出库登记</td>
  </tr>
  </table>
  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="120px" nowrap class="toolbar">你好，${user.name}</td>
    <td width="30px" nowrap class="toolbar">&nbsp;</td>
    <td width="20px" nowrap class="toolbar"></td>
	<td nowrap class="toolbar">&nbsp;</td>
    <td width="150px" nowrap class="toolbar" >筛选
    	<select id="method" name="select" onchange="if (this.options[this.selectedIndex].value!='') location.href=this.options[this.selectedIndex].value">
    	<option >付款方式</option>
    	<option id="deliveryWithPayment" value="/200429SCM_BS/servlet/StockOutServlet?payType=1">货到付款</option>
    	<option id="deliveryWithAcceptance" value="/200429SCM_BS/servlet/StockOutServlet?payType=2" >款到发货</option>
    	<option id="prepay" value="/200429SCM_BS/servlet/StockOutServlet?payType=3" >预付款到发货</option>
    	<option id="all" value="/200429SCM_BS/servlet/StockOutServlet?payType=all">所有采购单</option>
    	</select>
    </td>
    <td width="20px" class="toolbar">&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="2">
	<tr>
    <td class="title1">&nbsp;</td>
    <td class="title1">销售单编号</td>
    <td class="title1">创建时间</td>
	<td class="title1">客户编号</td>
	<td class="title1">创建用户</td>
    <td class="title1">附加费用</td>
    <td class="title1">销售产品总价</td>
    <td class="title1">销售单总价</td>
    <td class="title1">付款方式</td>
    <td class="title1">最低预付款金额</td>
    <td class="title1">处理状态</td>
	</tr>
	<c:forEach items="${somains}" var="item">
	<tr>
	<td align="center"><input type="checkbox" name="checkbox" value="${item.soID}"></td>
	<td align="center">${item.soID}</td>
	<td align="center">${item.createTime}</td>
	<td align="center">${item.customerCode}</td>
	<td align="center">${item.account}</td>
	<td align="center">${item.tipFee}</td>
	<td align="center">${item.productTotal}</td>
	<td align="center">${item.soTotal}</td>	
	<td align="center"><c:choose><c:when test="${item.payType==1}">货到付款</c:when>
	<c:when test="${item.payType==2}">款到发货</c:when><c:when test="${item.payType==3}">预付款到发货</c:when>
	</c:choose></td>	
	<td align="center">${item.prePayFee}</td>	
	<td align="center"><c:choose><c:when test="${item.status==1}">新增的销售单</c:when>
	<c:when test="${item.status==2}">已付款的销售单</c:when>
	<c:when test="${item.status==3}">已经入库的销售单</c:when>
	</c:choose></td>	
	</tr>
	</c:forEach>
	</table><br/>
	<center>
	<a href="servlet/StockOutServlet?pageNow=1&payType=${payType}">首页</a>
	<a href="servlet/StockOutServlet?pageNow=${pageNow-1}&payType=${payType}">上一页</a>
	<a href="servlet/StockOutServlet?pageNow=${pageNow+1}&payType=${payType}">下一页</a>
	<a href="servlet/StockOutServlet?pageNow=${pageCount}&payType=${payType}">末页</a>
	第${pageNow}页/共${pageCount}页
	<input size="2" id="pageNow"/>
	<input type="button" value="GO" onclick="go()"/>
	</center>
	
	<table width="100%"  border="0" align="center" cellspacing="1">
	  <tr>
	    <td class="title2"></td>
	  </tr>
	</table><br/>
	<div align="center">
    <input type="button" id="sel" value="全选" onclick="selectAll()"/>
    <input type="button" id="inverse" value="反选" onclick="inverseSelect()"/>
    <input type="button" id="detail" value="查看明细" onclick="showDetail()"/>
    <input type="button" id="stockOut" value="出库" onclick="stockOut()"/>
    <input type="button" id="print" value="打印" onclick="window.print()"/>
    <input type="button" id="close" value="关闭" onclick="window.close()"/>
	</div>
    
  </body>
</html>

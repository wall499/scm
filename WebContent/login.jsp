<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>用户登录</title>
		<link href="css/User_Login.css" type=text/css rel=stylesheet>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
	</head>

	<body id=userlogin_body style="background:url('images/user_all_bg.gif')">
		<form action="/200429SCM_BS/LoginCheckServlet" method="post">
		<div id=user_login>
		  <dl>
		   <dd id=user_top>
			  <ul>
				<li class=user_top_l style="background:url('images/user_top_l.gif')"></li>
				<li class=user_top_c style="background:url('images/user_top_c.gif')"></li>
				<li class=user_top_r style="background:url('images/user_top_r.gif')"></li>
			  </ul>
				<dd id=user_main><ul>
					<li class=user_main_l style="background:url('images/user_main_l.gif')"></li>
					<li class=user_main_c style="background:url('images/user_main_c.gif')">
						<div class=user_main_box>
						  <ul>
							 <li class=user_main_text>用户名：</li>
							 <li class=user_main_input>
								<input class=TxtUserNameCssClass id=TxtUserName maxLength=20 name=TxtUserName style="background:url('images/user_login_name.gif')">
							 </li>
						  </ul>
						  <ul>
						    <li class=user_main_text>密 码：</li>
							<li class=user_main_input>
							  <input class=TxtPasswordCssClass id=TxtPassword type=password name=TxtPassword style="background:url('images/user_login_password.gif')">
							</li>
						  </ul>
						  
				</div>
			</li>
			<li class=user_main_r style="background: url(images/user_main_r.gif)">
				<input class=IbtnEnterCssClass id=IbtnEnter style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
				 type=image src="images/user_botton.gif" name=IbtnEnter>
			</li>
		</ul>
			<dd id=user_bottom><ul>
			 <li class=user_bottom_l style="background: url(images/user_bottom_l.gif)"></li>
			 <li class=user_bottom_r style="background: url(images/user_bottom_r.gif)"></li>
			</ul>
			</dd>
		</dl>
	</div>
			<span id=ValrUserName style="DISPLAY: none; COLOR: red"></span>
			<span id=ValrPassword style="DISPLAY: none; COLOR: red"></span>
			<span id=ValrValidateCode style="DISPLAY: none; COLOR: red"></span>
			<div id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></div>
		</form>
	</body>
</html>

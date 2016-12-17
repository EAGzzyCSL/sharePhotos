<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="bean.MyUser"%>
<jsp:useBean id="userMan" class="man.UserMan" />
<%
	MyUser myUser = null;
	if (session.getAttribute("user") == null) {
		response.sendRedirect("signUpIn.jsp");
	} else {
		myUser = (MyUser) session.getAttribute("user");
	}
%>
<!-- 用户信息查看和修改页面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户信息</title>
<link type="text/css" href="style/header.css" rel="stylesheet">
</head>
<style type="text/css">
#div_info {
	margin: 12em auto;
	height: 20em;
	width: 36em;
	display: flex;
	overflow: hidden;
	box-shadow: 0px 2px 6px gray;
}

#div_info #div_top {
	height: 12em;
	margin: 1em;
	width: 34em;
}

#div_info #div_bottom {
	margin: 1em;
}

#div_getimg {
	display: inline-block;
	overflow: hidden;
	height: 8em;
	width: 8em;
	border: 5px dashed rgb(100, 100, 100);
	background-image: url(style/img/icon_add.svg);
	background-repeat: no-repeat;
	background-position: center;
	margin: 1em;
	background-color: rgb(230, 230, 230);
}

#div_getimg:hover {
	border: 5px dashed rgb(200, 200, 200);
}

#div_getimg:hover>#p_tip {
	opacity: 0.4;
}

#div_getimg.dragover {
	border: 5px dashed rgb(30, 144, 255);
}

#input_getimg {
	z-index: 1;
	position: relative;
	height: 100%;
	width: 100%;
	opacity: 0;
}

#img_info_avatar {
	position: relative;
	top: -100%;
	height: 100%;
	width: 100%;
}

#input_username {
	display: inline-block;
	vertical-align: top;
	margin: 1em;
	border: none;
	padding: 0.2em;
	font-size: 1.5em;
	padding: 0.2em;
}

#text_intro {
	height: 4em;
	width: 28em;
	margin: 1em;
	vertical-align: middle;
	border: none;
}

#input_submit {
	vertical-align: bottom;
	border: none;
	font-size: 2em;
	display: inline-block;
	margin-bottom: 0.4em;
}
</style>
<body>
	<div id="div_head">
		<span id="span_title"><a href="index.jsp">share photos</a></span> <input
			id="input_search" placeholder="search">
		<div id="div_head_userinfo">
			<%
				out.println("<a href='userInfo.jsp'>");
				if (myUser != null) {
					out.println("<img id='img_avatar' src='fun/getAvatar.jsp?id="
							+ myUser.getId() + "'></a>");
					out.println("<span id='span_username'>" + myUser.getUsername()
							+ "</span>");
				}
			%>
			<span id="span_logout"><a href="afterLogout.jsp">注销</a></span>
		</div>
	</div>

	<div id="div_info">
		<form method="post" enctype="multipart/form-data" action="userInfor">
			<div id="div_top">
				<div id="div_getimg">
					<input type="file" id="input_getimg" name="img">
					<%
						if (myUser != null) {
							out.println("<img id='img_info_avatar' src='fun/getAvatar.jsp?id="
									+ myUser.getId() + "'>");
						}
					%>

				</div>
				<%
					if (myUser != null) {
						out.println("<input id='input_username' placeholder='用户名' readonly='readonly' value='"
								+ myUser.getUsername() + "'>");
					}
				%>

			</div>
			<div id="div_bottom">
				<%
					if (myUser != null) {
						out.println("<textarea id='text_intro' placeholder='自我介绍' name='des'>"
								+ myUser.getDes() + "</textarea>");
					}
				%>

				<input id="input_submit" type="submit" value="提交">
			</div>

		</form>

	</div>
	<script type="text/javascript" src="script/uploadImg.js"></script>
	<script type="text/javascript">
		var div_getimg = document.getElementById("div_getimg");
		var img_info_avatar = document.getElementById("img_info_avatar");
		var input_getimg = document.getElementById("input_getimg");
		uploadImg(div_getimg, input_getimg, img_info_avatar);
	</script>

</body>
</html>

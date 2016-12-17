<%@ page contentType="text/html;charset=utf-8"%>
<%
	if (session.getAttribute("user") != null) {
		response.sendRedirect("index.jsp");
	} else {
	}
%>
<!-- 登陆注册页面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SharePhotos</title>
<style type="text/css">
html, body {
	margin: 0;
}

#div_head {
	background-color: rgb(30, 144, 255);
	width: 100%;
	text-align: center;
	height: 32em;
	overflow: hidden;
}

#div_head>span {
	font-size: 12em;
	font-family: cursive;
	display: block;
	margin-top: 0.5em;
	color: white;
}

#div_form {
	box-shadow: 0px 1px 2px gray;
	height: 16em;
	width: 30em;
	margin: 0 auto;
	position: relative;
	top: -4em;
	background-color: white;
	overflow: hidden;
	text-align: center;
}

.input_text {
	font-size: 2em;
	display: block;
	margin: 1em auto;
}

.input_submit {
	font-size: 2em;
	border: none;
	display: inline-block;
	margin: 0 1.8em;
}

#div_about {
	text-align: center;
	margin: 0 auto;
	height: 5em;
	overflow: hidden;
}

#div_about>span {
	display: block;
	margin: 2em;
	color: chocolate;
}
</style>
</head>
<body>
	<div id="div_head">
		<span>Share Photos</span>
	</div>
	<div id="div_form">
		<form method="post">
			<input class="input_text" name="username" placeholder="username:"
				required="required"> <input class="input_text"
				name="password" placeholder="password:" required="required"
				type="password"> <input class="input_submit" type="submit"
				formaction="signUper" value="sign up"> <input
				class="input_submit" type="submit" formaction="signIner"
				value="sign in">
		</form>
	</div>
	<div id="div_about">
		<span>powered by EAGzzyCSL</span>
	</div>
</body>
</html>

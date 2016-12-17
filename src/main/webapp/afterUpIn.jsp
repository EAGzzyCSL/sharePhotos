<%@ page contentType="text/html;charset=utf-8"%>
<!-- 登陆注册后的提示页面 -->
<html>
<head>
<meta charset="utf-8">
<title>服务器提示</title>

</head>
<style type="text/css">
body {
	display: flex;
	justify-content: center;
	flex-direction: column;
}

#p_tip {
	font-size: 1.5em;
	margin: 0 auto;
}
</style>
<body>
	<p id="p_tip">
		<%
			//根据不同的状态提示不同的信息
			final String TIP_goSignIn = "注册成功，将为您跳转到登陆页面……";
			final String TIP_exist = "用户名已存在……";
			final String TIP_signInOK = "登陆成功……";
			final String TIP_noUsername = "用户名不存在……";
			final String TIP_errPassword = "密码错误请重试……";
			String state = request.getParameter("state");
			boolean redirect_signIn = false;
			boolean redirect_share = false;
			if (state != null) {
				switch (state) {
					case "goSignIn" : {
						out.println(TIP_goSignIn);
						redirect_signIn = true;
						break;
					}
					case "exist" : {
						out.println(TIP_exist);
						break;
					}
					case "signInOK" : {
						out.println(TIP_signInOK);
						redirect_share = true;
						break;
					}
					case "noUsername" : {
						out.println(TIP_noUsername);
						break;
					}
					case "errPassword" : {
						out.println(TIP_errPassword);
						break;
					}
				}
			}
		%>
	</p>
	<script type="text/javascript">
		//在某些情况下页面需要跳转
		var redirect = function(url) {
			setTimeout(function() {
				window.location = url;
			}, 1200);
		};
	<%if (redirect_share) {
				out.println("redirect('index.jsp');");
			}
			if (redirect_signIn) {
				out.println("redirect('signUpIn.jsp');");
			}%>
		
	</script>

</body>
</html>
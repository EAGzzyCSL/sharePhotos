<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 负责注销的页面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>服务器提示</title>
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
</head>

<body>
	<%
		session.invalidate();
	%>
	<p id="p_tip">注销成功，即将为您跳转到登陆页面</p>
	<script type="text/javascript">
		//注销完毕后跳转到登陆页面
		var redirect = function(url) {
			setTimeout(function() {
				window.location = url;
			}, 1200);
		};
		redirect('signUpIn.jsp');
	</script>
</body>
</html>

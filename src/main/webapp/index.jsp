
<%@page import="bean.MyPhoto"%>
<%@page import="bean.Support"%>
<%@page import="bean.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="bean.MyUser"%>
<%
	//检查是否有用户登陆，如果没有则跳转到登陆页面
	MyUser myUser = null;
	if (session.getAttribute("user") == null) {
		response.sendRedirect("signUpIn.jsp");
	} else {
		myUser = (MyUser) session.getAttribute("user");
	}
%>

<jsp:useBean id="photoMan" class="man.PhotoMan" />
<jsp:useBean id="supportMan" class="man.SupportMan" />
<jsp:useBean id="commentMan" class="man.CommentMan" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<title>share photos</title>
<link type="text/css" href="style/header.css" rel="stylesheet">
<style type="text/css">
/*content*/
#div_content {
	
}

.div_one {
	margin: 1em 4em;
	display: flex;
}

.img_avatar {
	height: 4em;
	border-radius: 4em;
	border: white solid 0.4em;
	display: block;
	margin: 2em 1em 0 2em;
	box-shadow: 1px 1px rgba(0, 0, 0, 0.22);
}

.div_card {
	border-radius: 1em;
	border: white solid 0.4em;
	box-shadow: 0px 2px 6px gray;
	padding: 1em;
	margin: 1em;
	width: 80em;
	display: flex;
}

.div_img {
	height: 27em;
	width: 48em;
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.img_photo {
	margin: 0 auto;
}

.div_divider {
	border: dashed black 0.1em;
	margin: 1em;
}

.div_other {
	flex: 1 0 0;
}

.span_describe {
	text-indent: 2em;
	display: block;
	font-size: 1.5em;
}
/*foot*/
#div_foot {
	text-align: center;
	margin: 0 auto;
	height: 5em;
	overflow: hidden;
}

#div_foot>p {
	display: block;
	margin: 2em;
	color: chocolate;
}

/*add*/
#div_add {
	position: fixed;
	height: 0;
	width: 0;
	background-color: red;
	bottom: 0;
	right: 0;
	background-color: rgba(200, 200, 200, 0.6);
	display: flex;
	flex-direction: column;
	justify-content: center;
	transition-property: height width;
	transition-duration: 0.2s;
	transition-timing-function: ease-in-out;
	transition-delay: initial;
}

#div_add.show {
	height: 100%;
	width: 100%;
}

#form_add {
	height: 30em;
	width: 36em;
	background-color: white;
	margin: 0 auto;
	text-align: right;
	box-shadow: 0px 2px 6px #795548;
}

#div_getimg {
	height: 18em;
	width: 32em;
	border: 5px dashed rgb(100, 100, 100);
	background-image: url(style/img/icon_add.svg);
	background-repeat: no-repeat;
	background-position: center;
	margin: 1em auto;
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

#img_preview {
	position: relative;
	top: -100%;
	height: 100%;
	width: 100%;
}

#text_des {
	width: 32em;
	display: block;
	margin: 0 auto;
	font-size: 16px;
	border: none;
}

#input_submit {
	border: none;
	font-size: 2em;
	margin: 1em;
}

#div_fab_add {
	position: fixed;
	height: 4em;
	width: 4em;
	border-radius: 2em;
	background-color: #FF4081;
	background-image: url(style/img/icon_add.svg);
	background-repeat: no-repeat;
	background-position: center;
	box-shadow: 0 3px 10px rgba(0, 0, 0, 0.23), 0 3px 10px
		rgba(0, 0, 0, 0.16);
	right: 2em;
	bottom: 2em;
	background-size: 2.5em;
}

#div_fab_add.clicked {
	background-image: url(style/img/icon_back.svg);
}
/*comment*/
.div_support {
	margin: 1em;
	display: flex;
	border: solid 0.2em rgb(200, 200, 200);
	border-radius: 1em;
}

.input_support {
	height: 1.2em;
	width: 1.2em;
	background-image: url(style/img/icon_heart.svg);
	margin: 0.3em;
	border: none;
	background-repeat: no-repeat;
	background-size: 100%;
	cursor: pointer;
	background-color: transparent;
}

.div_comment {
	min-height: 3em;
	margin: 0 1em;
	display: flex;
}

.div_comment:before {
	content: "";
	height: 0;
	width: 0;
	top: 1.3em;
	position: relative;
	border-width: 0.4em;
	border-style: dashed solid dashed dashed;
	border-color: transparent rgb(200, 200, 200) transparent transparent;
}

.span_comment, .form_comment {
	display: inline-block;
	border-radius: 0.2em;
	background-color: rgb(200, 200, 200);
	margin: 0.5em 0;
	padding: 0.2em;
}

.input_comment {
	font-size: 1.5em;
	border: none;
}

.input_comment_submit {
	font-size: 1.5em;
	border: none;
	margin: 0.5em 0 0 9em;
}
</style>
</head>
<body>

	<div id="div_head">
		<span id="span_title"><a href="index.jsp">share photos</a></span> <input
			id="input_search" placeholder="search">
		<div id="div_head_userinfo">
			<%
				/*根据当前登陆的用户输出用户的用户名和头像*/
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
	<div id="div_content">
		<%
			/*输出全部的用户上传照片和评论点赞*/
			ArrayList<MyPhoto> photos=photoMan.getAllPhotos();
			for(MyPhoto p:photos){
				out.println("<div class='div_one'>");
			out.println("<img class='img_avatar' src='fun/getAvatar.jsp?id="+p.getUserId()+"'>");
			out.println("<div class='div_card'>");
				out.println("<div class='div_img'>");
					out.println("<img class='img_photo' src='fun/getPhoto.jsp?id="+p.getId()+"'>");
				out.println("</div>");
				out.println("<div class='div_divider'></div>");
				out.println("<div class='div_other'>");
					out.println("<span class='span_describe'>"+p.getDes()+"</span>");
					out.println("<div class='div_support'>");
					out.println("<form method='post' action='supportor'>");
					out.println("<input type='hidden' value='"+p.getId()+"' name='photo_id'>");
					out.println("<input type='submit' class='input_support' value=''>");
					out.println("</form>");
					out.println("<p class='p_supportor'>");
					ArrayList<Support> supports=supportMan.getSupports(p.getId());
						for(Support s:supports){
							out.println(s.getUsername());
						}
					out.println("</p>");
					out.println("</div>");
					ArrayList<Comment> comments=commentMan.getComments(p.getId());
						for(Comment c:comments){
							out.println("<div class='div_comment'>");
								out.println("<span class='span_comment'>"+c.getUserName()+":"+c.getContent()+"</span>");
							out.println("</div>");
						}
					out.println("<div class='div_comment'>");
						out.println("<form class='form_comment' method='post' action='commenter'>");
							out.println("<input type='hidden' name='photo_id' value='"+p.getId()+"'>");
							out.println("<input class='input_comment' name='content' placeholder='评论'>");
							out.println("<input class='input_comment_submit' type='submit' value='评论'>");
						out.println("</form>");
					out.println("</div>");
				out.println("</div>");
			out.println("</div>");
				out.println("</div>");
			}
		%>
	</div>


	<div id="div_foot">
		<p>powered by EAGzzyCSL</p>
	</div>
	<!-- add，上传照片时的添加框-->
	<div id="div_add">
		<form id="form_add" method="post" enctype="multipart/form-data"
			action="uploader">
			<div id="div_getimg">
				<input type="file" id="input_getimg" required="required" name="img">
				<img id="img_preview">
			</div>
			<textarea id="text_des" required="required" placeholder="请输入对照片的描述"
				name="des"></textarea>
			<input type="submit" id="input_submit" value="上传">
		</form>
	</div>
	<div id="div_fab_add"></div>
	<script type="text/javascript" src="script/uploadImg.js"></script>
	<script type="text/javascript">
		/*fab and mask*/
		/*负责添加框的显示和隐藏*/
		var div_fab_add = document.getElementById("div_fab_add");
		var div_add = document.getElementById("div_add");
		div_fab_add.onclick = function() {
			if (div_fab_add.classList.contains("clicked")) {
				div_add.classList.remove("show");
				div_fab_add.classList.remove("clicked");
			} else {

				div_add.classList.add("show");
				div_fab_add.classList.add("clicked");
			}
		};
		/*form*/
		var div_getimg = document.getElementById("div_getimg");
		var img_preview = document.getElementById("img_preview");
		var input_getimg = document.getElementById("input_getimg");
		uploadImg(div_getimg, input_getimg, img_preview);
	</script>
</body>
</html>

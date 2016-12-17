package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import man.CommentMan;
import bean.Comment;
import bean.MyUser;

/**
 * Servlet implementation class Commenter 负责处理提交的评论
 */
@WebServlet("/commenter")
public class Commenter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentMan commentMan;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Commenter() {
		super();
		commentMan = new CommentMan();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* 设置utf-8 */
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		/* 获取请求中的参数 */
		String content = request.getParameter("content");
		String photo_id = request.getParameter("photo_id");
		/* 新建一个评论 */
		MyUser user = (MyUser) request.getSession().getAttribute("user");
		Comment comment = new Comment(user.getId(), Integer.valueOf(photo_id),
				content);
		/* 在评论表中插入评论，如果成功跳回首页，否则显示评论失败 */
		if (commentMan.addComment(comment)) {
			response.sendRedirect("index.jsp");
		} else {
			response.getWriter().println("评论失败……");
		}
	}

}

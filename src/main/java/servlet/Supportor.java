package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MyUser;
import bean.Support;
import man.SupportMan;

/**
 * Servlet implementation class Supportor 负责处理点赞请求
 */
@WebServlet("/supportor")
public class Supportor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private SupportMan supportMan;

	public Supportor() {
		super();
		supportMan = new SupportMan();
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
		String photo_id = request.getParameter("photo_id");
		MyUser user = (MyUser) request.getSession().getAttribute("user");
		Support support = new Support(Integer.valueOf(photo_id), user.getId());
		/* 尝试新增点赞并根据不同的结果显示不同的信息 */
		if (supportMan.addSupport(support)) {
			response.sendRedirect("index.jsp");
		} else {
			response.getWriter().println("点赞失败……");
		}
	}

}

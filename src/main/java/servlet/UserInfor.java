package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import man.UserMan;
import bean.MyUser;

/**
 * Servlet implementation class UserInfor 处理用户信息更新请求
 */
@WebServlet("/userInfor")
@MultipartConfig
public class UserInfor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserMan userMan;

	public UserInfor() {
		super();
		userMan = new UserMan();
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
		/* 设置utf-8 */
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		/* 获取请求中的参数 */
		String des = request.getParameter("des");
		Part imgPart = request.getPart("img");
		MyUser user = (MyUser) request.getSession().getAttribute("user");
		if (des != null) {
			user.setDes(des);
		}
		if (imgPart != null) {

			user.setAvatar(imgPart.getInputStream());
		}
		/* 尝试更新用户信息并根据不同的结果显示不同的信息 */
		if (userMan.updateUserInfo(user)) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("userInfo.jsp");
		} else {
			response.getWriter().println("更新用户信息失败");
		}
	}

}

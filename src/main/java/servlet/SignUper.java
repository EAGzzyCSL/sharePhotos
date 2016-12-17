package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import man.UserMan;
import bean.MyUser;

/**
 * Servlet implementation class SignUper
 * 负责注册登陆请求
 */
@WebServlet("/signUper")
public class SignUper extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public UserMan userMan;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUper() {
		super();
		userMan = new UserMan();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*设置utf-8*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		/*获取请求中的参数*/
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		MyUser user = new MyUser(username, password);
		/*尝试注册并根据不同的结果显示不同的信息*/
		StringBuffer url = new StringBuffer("afterUpIn.jsp?state=");
		if (userMan.existUser(user)) {
			url.append("exist");
		} else if (userMan.createUser(user)) {
			url.append("goSignIn");
		}
		response.sendRedirect(url.toString());
	}

}

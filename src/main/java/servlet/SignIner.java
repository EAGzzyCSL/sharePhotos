package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bean.MyUser;
import man.UserMan;
@WebServlet("/signIner")
public class SignIner extends HttpServlet {

	/**
	 * 负责处理登陆请求
	 */
	private static final long serialVersionUID = 1L;
	public UserMan userMan;
	public SignIner(){
		super();
		userMan = new UserMan();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*设置utf-8*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		/*获取请求中的参数*/
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		MyUser user = new MyUser(username, password);
		StringBuffer url = new StringBuffer("afterUpIn.jsp?state=");
		/*尝试登陆并根据不同的结果显示不同的信息*/
		if (!userMan.existUser(user)) {
			url.append("noUsername");
		} else if (userMan.matchUser(user)) {
			url.append("signInOK");
			request.getSession().setAttribute("user", user);
		}else{
			url.append("errPassword");
		}
		response.sendRedirect(url.toString());
	}
}

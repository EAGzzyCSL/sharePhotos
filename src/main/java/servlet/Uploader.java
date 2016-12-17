package servlet;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.MyPhoto;
import bean.MyUser;
import man.PhotoMan;

@WebServlet("/uploader")
@MultipartConfig
public class Uploader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet() 处理图片上传请求
	 */
	public PhotoMan photoMan;

	public Uploader() {
		super();
		photoMan = new PhotoMan();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		int userId = ((MyUser) request.getSession().getAttribute("user"))
				.getId();
		imgPart.write("test.tmp");
		MyPhoto photo = new MyPhoto(des, imgPart.getInputStream(), userId);
		/* 尝试上传图片并根据不同的结果显示不同的信息 */
		if (photoMan.uploadPhoto(photo)) {
			response.sendRedirect("index.jsp");
		} else {
			response.getWriter().println("上传失败……");
		}
	}

}

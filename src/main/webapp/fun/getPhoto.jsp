<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%
	//根据id获取用户上传的照片并输出
	String DB_url = "jdbc:mysql://localhost:3306/sharephotos?useUnicode=true&characterEncoding=UTF-8";
	String DB_username = "sharephotos";
	String DB_password = "12345678";
	Connection con = null;
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		con = DriverManager.getConnection(DB_url, DB_username,
				DB_password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String sql_getPhoto = "SELECT `img` FROM `photo` WHERE `id`=?";
	String id = request.getParameter("id");
	try {
		PreparedStatement preS = con.prepareStatement(sql_getPhoto);
		preS.setInt(1, Integer.valueOf(id));
		ResultSet rs = preS.executeQuery();
		rs.next();
		Blob b = rs.getBlob("img");
		response.setContentType("image/jpeg");
		response.setContentLength((int) b.length());
		InputStream is = b.getBinaryStream();

		OutputStream os = response.getOutputStream();
		byte buf[] = new byte[(int) b.length()];
		is.read(buf);
		os.write(buf);
		out.clear();  
		out = pageContext.pushBody();  
		os.close();
		rs.close();
		preS.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
%>
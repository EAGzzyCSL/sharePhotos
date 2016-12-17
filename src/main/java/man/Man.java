package man;

import java.io.Serializable;
import java.sql.*;

public abstract class Man implements Serializable {
	/**
	 * 负责数据库交互的类的子类
	 */
	private static final long serialVersionUID = 1L;
	//数据库连接的相关参数
	private final String DB_url = "jdbc:mysql://localhost:3306/sharephotos?useUnicode=true&characterEncoding=UTF-8";
	private final String DB_username = "sharephotos";
	private final String DB_password = "12345678";
	private static Connection con;

	public Man() {
		//加载数据库驱动
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		//获得数据库的连接
		if (con == null) {
			try {
				con = DriverManager.getConnection(DB_url, DB_username,
						DB_password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;
	}

}

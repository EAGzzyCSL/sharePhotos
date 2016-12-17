package man;

import java.sql.*;

import bean.MyUser;

public class UserMan extends Man {

	/**
	 * 用户的处理
	 */

	private static final long serialVersionUID = 1L;
	/* 判断用户是否存在 */
	private String sql_existUser = "SELECT * FROM `userinfo` WHERE `username`=?";

	public boolean existUser(MyUser user) {
		boolean r = false;
		try {
			getConnection().toString();
			PreparedStatement preS = getConnection().prepareStatement(
					sql_existUser);

			preS.setString(1, user.getUsername());
			ResultSet rs = preS.executeQuery();
			r = rs.next();
			rs.close();
			preS.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	/* 根据用户名和密码匹配用户 */
	private String sql_matchUser = "SELECT `id` FROM `userinfo` WHERE `username`=? and `password`=?";

	public boolean matchUser(MyUser user) {
		boolean r = false;
		try {
			PreparedStatement preS = getConnection().prepareStatement(
					sql_matchUser);
			preS.setString(1, user.getUsername());
			preS.setString(2, user.getPassword());
			ResultSet rs = preS.executeQuery();
			r = rs.next();
			user.setId(rs.getInt("id"));
			rs.close();
			preS.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	/* 新增一个用户 */
	private String sql_createUser = "INSERT INTO `userinfo`(`username`, `password`) VALUES (?,?)";

	public boolean createUser(MyUser user) {
		try {
			PreparedStatement preS = getConnection().prepareStatement(
					sql_createUser);
			preS.setString(1, user.getUsername());
			preS.setString(2, user.getPassword());
			preS.execute();
			preS.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/* 更新用户信息 */
	private final String sql_updateUserInfo = "UPDATE `userinfo` SET `des`=?,`avatar`=? WHERE `id`=?";
	private final String sql_updateUserInfo_noAvatar = "UPDATE `userinfo` SET `des`=? WHERE `id`=?";

	public boolean updateUserInfo(MyUser user) {
		try {
			PreparedStatement preS = null;
			if (user.getAvatar() != null) {
				preS = getConnection().prepareStatement(sql_updateUserInfo);
				preS.setString(1, user.getDes());
				preS.setBinaryStream(2, user.getAvatar());
				preS.setInt(3, user.getId());
			} else {
				preS = getConnection().prepareStatement(
						sql_updateUserInfo_noAvatar);
				preS.setBinaryStream(1, user.getAvatar());
				preS.setInt(2, user.getId());
			}
			preS.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/*根据用户id获得用户信息*/
	private final String sql_getUser = "SELECT * FROM `userinfo` WHERE `id`=?";

	public MyUser getUser(int userId) {
		try {
			PreparedStatement preS = getConnection().prepareStatement(
					sql_getUser);
			preS.setInt(1, userId);
			ResultSet rs = preS.executeQuery();
			if (rs.next()) {
				MyUser user = new MyUser(rs.getInt("id"),
						rs.getString("username"), rs.getString("des"));
				rs.close();
				preS.close();
				return user;
			} else {
				preS.close();
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

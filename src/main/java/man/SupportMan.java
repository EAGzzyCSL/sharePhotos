package man;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Support;

public class SupportMan extends Man {

	/**
	 * 点赞的处理
	 */
	private static final long serialVersionUID = 1L;
	/* 为某张照片新增一个赞 */
	private final String sql_addSupport = "INSERT INTO `support`(`photo_id`, `user_id`) VALUES (?,?)";

	public boolean addSupport(Support support) {
		try {
			PreparedStatement preS = getConnection().prepareStatement(
					sql_addSupport);
			preS.setInt(1, support.getPhotoId());
			preS.setInt(2, support.getUserId());
			preS.execute();
			preS.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/* 获得照片下全部的赞 */
	private final String sql_getSupports = "SELECT `userinfo`.`username` FROM `support`,`userinfo` WHERE `support`.`user_id`=`userinfo`.`id` and `support`.`photo_id`=?";

	public ArrayList<Support> getSupports(int photoId) {
		ArrayList<Support> supports = new ArrayList<>();
		try {
			PreparedStatement preS = getConnection().prepareStatement(
					sql_getSupports);
			preS.setInt(1, photoId);
			ResultSet rs = preS.executeQuery();
			while (rs.next()) {
				String username = rs.getString("username");
				Support s = new Support(username);
				supports.add(s);
			}
			preS.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return supports;
	}

}

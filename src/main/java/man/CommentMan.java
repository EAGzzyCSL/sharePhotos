package man;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Comment;

public class CommentMan extends Man {

	/**
	 * 负责评论的处理
	 */
	private static final long serialVersionUID = 1L;
	/*新增一条评论*/
	private final String sql_addComment="INSERT INTO `comments`(`photo_id`, `user_id`, `content`) VALUES (?,?,?)";
	public boolean addComment(Comment comment){
		try {
			PreparedStatement preS = getConnection().prepareStatement(
					sql_addComment);
			preS.setInt(1, comment.getPhotoId());
			preS.setInt(2, comment.getUserId());
			preS.setString(3, comment.getContent());
			preS.execute();
			preS.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/*获取全部的评论*/
	private final String sql_getComments="SELECT  `userinfo`.`username`, `comments`.`content` FROM `comments`,`userinfo` WHERE `userinfo`.`id`=`comments`.`user_id` and `comments`.`photo_id`=?";
	public ArrayList<Comment> getComments(int photoId){
		ArrayList<Comment> comments=new ArrayList<Comment>();
		try {
			PreparedStatement preS = getConnection().prepareStatement(
					sql_getComments);
			preS.setInt(1, photoId);
			ResultSet rs=preS.executeQuery();
			while(rs.next()){
				String username=rs.getString("username");
				String content=rs.getString("content");
				Comment c=new Comment(username,content);
				comments.add(c);
			}
			preS.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}

}

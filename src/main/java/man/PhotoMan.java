package man;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.MyPhoto;

public class PhotoMan extends Man {

    /**
     * 负责照片的处理
     */
    private static final long serialVersionUID = 1L;

    /* 新增一张照片 */
    private final String sql_uploadPhoto = "INSERT INTO `photo`(`user_id`,`des`, `img`) VALUES (?,?,?)";

    public boolean uploadPhoto(MyPhoto photo) {
        try {
            PreparedStatement preS = getConnection().prepareStatement(
                    sql_uploadPhoto);
            preS.setInt(1, photo.getUserId());
            preS.setString(2, photo.getDes());
            preS.setBinaryStream(3, photo.getImg());
            preS.execute();
            preS.close();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /* 获取全部的照片 */
    private final String sql_getAllPhotos = "SELECT * FROM `photo` WHERE 1";

    public ArrayList<MyPhoto> getAllPhotos() {
        ArrayList<MyPhoto> photos = new ArrayList<MyPhoto>();
        try {
            PreparedStatement preS = getConnection().prepareStatement(
                    sql_getAllPhotos);
            ResultSet rs = preS.executeQuery();

            while (rs.next()) {
                MyPhoto p = new MyPhoto(rs.getInt("id"), rs.getString("des"),
                        rs.getInt("user_id"));
                photos.add(p);
            }
            rs.close();
            preS.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return photos;
    }
}

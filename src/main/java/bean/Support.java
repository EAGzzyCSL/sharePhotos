package bean;

import java.io.Serializable;

public class Support implements Serializable {

	/**
	 * 点赞
	 */
	private static final long serialVersionUID = 1L;
	private int userId;//点赞的用户id
	private int photoId;//被点赞的照片id
	private String username;//点赞的用户名
	/*不同情境下的构造方法*/
	public Support() {

	}

	public Support(int photoId, int userId) {
		this.userId = userId;
		this.photoId = photoId;
	}

	public Support(String username) {
		this.username = username;
	}
	/*获取Support属性的get函数*/
	public String getUsername() {
		return this.username;
	}

	public int getUserId() {
		return this.userId;
	}

	public int getPhotoId() {
		return this.photoId;
	}

}

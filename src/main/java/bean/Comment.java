package bean;

import java.io.Serializable;

public class Comment implements Serializable {

	/**
	 * 评论
	 */
	private static final long serialVersionUID = 1L;
	private String content;//评论内容
	private int userId;//评论撰写者的用户id
	private String username;//评论撰写者的用户名
	private int photoId;//被评论的照片id
	/*不同情境下的构造方法*/
	public Comment() {

	}
	public Comment(int userId, int photoId, String content) {
		this.userId = userId;
		this.photoId = photoId;
		this.content = content;
	}

	public Comment(String username, String content) {
		this.username = username;
		this.content = content;
	}
	/*获得评论的属性的get函数*/
	public String getUserName() {
		return this.username;
	}

	public int getUserId() {
		return this.userId;
	}

	public int getPhotoId() {
		return this.photoId;
	}

	public String getContent() {
		return this.content;
	}

}

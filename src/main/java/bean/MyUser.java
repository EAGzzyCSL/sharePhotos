package bean;

import java.io.InputStream;
import java.io.Serializable;

public class MyUser implements Serializable {

	/**
	 * 网站的用户
	 */
	private static final long serialVersionUID = 1L;
	private String username;// 用户名
	private String password;// 密码
	private InputStream avatar;// 头像
	private String des;// 用户自我介绍

	private int id;// 用户的id

	/* 不同情境下的构造方法 */
	public MyUser() {

	}

	public MyUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public MyUser(int id, String username, String des) {
		this.id = id;
		this.username = username;
		this.des = des;
	}

	/* 获取MyUser属性的get函数 */
	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public String getDes() {
		return this.des;
	}

	public InputStream getAvatar() {
		return this.avatar;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public void setAvatar(InputStream avatar) {
		this.avatar = avatar;
	}

}

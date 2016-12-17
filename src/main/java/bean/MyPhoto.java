package bean;

import java.io.InputStream;
import java.io.Serializable;

public class MyPhoto implements Serializable {
	/**
	 * 照片类，表示每一张被上传的照片
	 */
	private static final long serialVersionUID = 1L;
	private String des;//照片的描述
	private InputStream img;//输入流格式的照片
	private int id;//照片的id
	private int userId;//照片发布者的userId
	/*不同情境下的构造方法*/
	public MyPhoto() {

	}

	public MyPhoto(String des, InputStream img) {
		this.des = des;
		this.img = img;

	}

	public MyPhoto(String des, InputStream img, int userId) {
		this(des, img);
		this.userId = userId;
	}

	public MyPhoto(int id, String des, int userId) {
		this.id = id;
		this.des = des;
		this.userId = userId;
	}
	/*获取MyPhoto属性的get函数*/
	public String getDes() {
		return this.des;
	}

	public InputStream getImg() {
		return this.img;
	}

	public int getId() {
		return this.id;

	}

	public int getUserId() {
		return this.userId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}

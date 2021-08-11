package DTO;

public class UserDTO {

	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_category;
	private int user_point;
	private String user_pic;
	private int user_age;
	private String user_tel;
	private String user_gender;
	
	// 생성자
	public UserDTO(String user_id, String user_pw, String user_name, String user_category, int user_point,
			String user_pic, int user_age, String user_gender, String user_tel) {
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_category = user_category;
		this.user_point = user_point;
		this.user_pic = user_pic;
		this.user_age = user_age;
		this.user_gender = user_gender;
		this.user_tel = user_tel;
	}
	
	// 회원가입(ID, PW, NAME, CATEGORY, AGE, GENDER, TEL)
	public UserDTO(String user_id, String user_pw, String user_name, String user_category, int user_age,
			String user_gender, String user_tel) {
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_category = user_category;
		this.user_age = user_age;
		this.user_gender = user_gender;
		this.user_tel = user_tel;
	}
	
	
	// 정보수정(ID, PW, NAME, CATEGORY, AGE, TEL)
	public UserDTO(String user_id, String user_pw, String user_name, String user_category, int user_age,
			String user_tel) {
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_category = user_category;
		this.user_age = user_age;
		this.user_tel = user_tel;
	}
	
	// 로그인 (ID, PW)
	public UserDTO(String user_id, String user_pw) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_category() {
		return user_category;
	}
	public void setUser_category(String user_category) {
		this.user_category = user_category;
	}
	public int getUser_point() {
		return user_point;
	}
	public void setUser_point(int user_point) {
		this.user_point = user_point;
	}
	public String getUser_pic() {
		return user_pic;
	}
	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}
	public int getUser_age() {
		return user_age;
	}
	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	
}

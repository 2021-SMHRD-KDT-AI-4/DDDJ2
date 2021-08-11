package DTO;

public class SNSDTO {

	private int sns_code;
	private String user_id;
	private String sns_content;
	private String sns_img;
	
	// 생성자
	public SNSDTO(int sns_code, String user_id, String sns_content, String sns_img) {
		this.sns_code = sns_code;
		this.user_id = user_id;
		this.sns_content = sns_content;
		this.sns_img = sns_img;
	}

	// SNS 등록(ID, CONTENT, IMG)
	public SNSDTO(String user_id, String sns_content, String sns_img) {
		this.user_id = user_id;
		this.sns_content = sns_content;
		this.sns_img = sns_img;
	}
	
	public int getSns_code() {
		return sns_code;
	}
	public void setSns_code(int sns_code) {
		this.sns_code = sns_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSns_content() {
		return sns_content;
	}
	public void setSns_content(String sns_content) {
		this.sns_content = sns_content;
	}
	public String getSns_img() {
		return sns_img;
	}
	public void setSns_img(String sns_img) {
		this.sns_img = sns_img;
	}
	
	
	
	
}

package com.example.projectvegan.DTO;

public class UserDTO {

    private String user_id;
    private String user_pw;
    private String user_name;
    private String category;
    private int point;
    private String pic;
    private int age;
    private String gender;
    private String tel;

    // userInfo(아이디, 비밀번호, 이름, 단계, 나이, 성별, 전화번호)
    public UserDTO(String user_id, String user_pw, String user_name, String category, int age, String gender, String tel) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.category = category;
        this.age = age;
        this.gender = gender;
        this.tel = tel;
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

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
}

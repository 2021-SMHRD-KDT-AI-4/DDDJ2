package com.example.projectvegan.DTO;

import java.io.BufferedInputStream;
import java.io.Serializable;

public class SNSDTO implements Serializable {

    private int sns_code;
    private String user_id;
    private String sns_title;
    private String sns_content;
    private BufferedInputStream sns_img;
    private String user_name;


    public SNSDTO(String user_id, String sns_title, String sns_content, BufferedInputStream sns_img, String user_name) {
        this.user_id = user_id;
        this.sns_title = sns_title;
        this.sns_content = sns_content;
        this.sns_img = sns_img;
        this.user_name = user_name;
    }

    public SNSDTO(int sns_code, String user_id, String sns_title, String sns_content, BufferedInputStream sns_img, String user_name) {
        this.sns_code = sns_code;
        this.user_id = user_id;
        this.sns_title = sns_title;
        this.sns_content = sns_content;
        this.sns_img = sns_img;
        this.user_name = user_name;
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
    public String getSns_title() {
        return sns_title;
    }
    public void setSns_title(String sns_title) {
        this.sns_title = sns_title;
    }
    public String getSns_content() {
        return sns_content;
    }
    public void setSns_content(String sns_content) {
        this.sns_content = sns_content;
    }
    public BufferedInputStream getSns_img() {
        return sns_img;
    }
    public void setSns_img(BufferedInputStream sns_img) {
        this.sns_img = sns_img;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}

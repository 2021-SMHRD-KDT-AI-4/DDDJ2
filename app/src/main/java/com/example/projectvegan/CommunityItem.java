package com.example.projectvegan;

import android.widget.ImageView;

public class CommunityItem {
    private int userIcon,comImg;
    private String userName;
    private String userEmail;
    private String comTitle;



    private String userText;

    public CommunityItem(int userIcon,  String userName, String userEmail, String comTitle, int comImg, String userText) {
        this.userIcon = userIcon;
        this.userName = userName;
        this.userEmail = userEmail;
        this.comTitle = comTitle;
        this.comImg = comImg;
        this.userText = userText;
    }

    public int getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(int userIcon) {
        this.userIcon = userIcon;
    }

    public int getComImg() {
        return comImg;
    }

    public void setComImg(int comImg) {
        this.comImg = comImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getComTitle() {
        return comTitle;
    }

    public void setComTitle(String comTitle) {
        this.comTitle = comTitle;
    }

    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }
}

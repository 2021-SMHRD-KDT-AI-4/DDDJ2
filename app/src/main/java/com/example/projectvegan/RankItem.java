package com.example.projectvegan;

public class RankItem {
    private String num;
    private String name;
    private String point;

    public RankItem(String num, String name, String point) {
        this.num = num;
        this.name = name;
        this.point = point;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}

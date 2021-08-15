package com.example.projectvegan;

import java.io.Serializable;

public class RankItem {
    private int num;
    private String id;
    private String name;
    private int point;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RankItem(String id, String name, int point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public RankItem(int num, String name, int point) {
        this.num = num;
        this.name = name;
        this.point = point;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}

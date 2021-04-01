package com.wx.exam.pojo.vo;

public class TypesVO {

    String id; //试卷上已存在的题目id
    String type;//对应题目类型
    String point;//添加时传来的分数

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

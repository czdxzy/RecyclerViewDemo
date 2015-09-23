package com.example.myweatherdemo.model;

/**
 * Created by Administrator on 2015/9/17.
 */
public class Province {
    private int ProID;
    private String name;
    private int ProSort;
    private String ProRemark;
    public int getId() {
        return ProID;
    }

    public String getProRemark() {
        return ProRemark;
    }

    public void setProRemark(String proRemark) {
        ProRemark = proRemark;
    }

    public void setId(int id) {
        this.ProID = id;
    }

    public int getProSort() {
        return ProSort;
    }

    public void setProSort(int proSort) {
        ProSort = proSort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

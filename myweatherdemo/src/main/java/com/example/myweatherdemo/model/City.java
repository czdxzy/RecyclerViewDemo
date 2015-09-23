package com.example.myweatherdemo.model;

/**
 * Created by Administrator on 2015/9/17.
 */
public class City {
    private int CityID;
    private String name;
    private int ProID;
    private int CitySort;

    public int getId(){
        return CityID;
    }

    public void setId(int id) {
        this.CityID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProId() {
        return ProID;
    }

    public void setProId(int proId) {
        this.ProID = proId;
    }

    public int getCitySort() {
        return CitySort;
    }

    public void setCitySort(int citySort) {
        this.CitySort = citySort;
    }

}

package com.example.myweatherdemo.model;

/**
 * Created by Administrator on 2015/9/17.
 */
public class City {
    private int Id;
    private String name;
    private int ProId;
    private int CitySort;

    public int getId(){
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProId() {
        return ProId;
    }

    public void setProId(int proId) {
        this.ProId = proId;
    }

    public int getCitySort() {
        return CitySort;
    }

    public void setCitySort(int citySort) {
        this.CitySort = citySort;
    }
    public String subName(){
        if (name.substring(name.length()-1,name.length()).equals("市"))
            return name.substring(0,name.length()-1);
        else
            return name;
    }
}

package com.example.myweatherdemo.model;

/**
 * Created by Administrator on 2015/9/17.
 */
public class County {
    private int Id;
    private String DisName;
    private int CityID;
    private int DisSort;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getDisName() {
        return DisName;
    }

    public void setDisName(String disName) {
        this.DisName = disName;
    }

    public int getCityId() {
        return CityID;
    }

    public void setCityId(int cityId) {
        this.CityID = cityId;
    }

    public int getDisSort() {
        return DisSort;
    }

    public void setDisSort(int disSort) {
        this.DisSort = disSort;
    }
    public String subName(){
        if (DisName.substring(DisName.length()-1,DisName.length()).equals("市"))
            return DisName.substring(0,DisName.length()-1);
        else if (DisName.substring(DisName.length()-1,DisName.length()).equals("县"))
            return DisName.substring(0,DisName.length()-1);
        else
            return DisName;
    }
}

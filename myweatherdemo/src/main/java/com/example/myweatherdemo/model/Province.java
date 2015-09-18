package com.example.myweatherdemo.model;

/**
 * Created by Administrator on 2015/9/17.
 */
public class Province {
    private int provinceId;
    private String provinceName;
    private String provinceCode;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int id) {
        this.provinceId = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}

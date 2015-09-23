package com.example.myweatherdemo.model;

/**
 * Created by Administrator on 2015/9/23.
 */
public class Weather {
    private String desc;
    private int status;
    private String wendu;
    private String ganmao;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    @Override
    public String toString() {
        return wendu+","+ganmao;
    }
}

package com.example.myweatherdemo.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.myweatherdemo.activity.MainActivity;
import com.example.myweatherdemo.model.City;
import com.example.myweatherdemo.model.County;
import com.example.myweatherdemo.model.Province;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/22.
 */
public class Utils {
    public static List<Province> getProvinceList(Context context){
        InputStream is = null;
        List<Province> provinceList = new ArrayList<>();
        AssetManager manager = context.getAssets();
        try {
            is = manager.open("Province.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                response.append(line);
            }
            Gson gson = new Gson();
             provinceList= gson.fromJson(response.toString(),new TypeToken<List<Province>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (is !=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return provinceList;
    }
    public static List<City> getCityList(Context context){
        InputStream is = null;
        List<City> cityList = new ArrayList<>();
        AssetManager manager = context.getAssets();
        try {
            is = manager.open("City.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                response.append(line);
            }
            Gson gson = new Gson();
            cityList= gson.fromJson(response.toString(),new TypeToken<List<City>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (is !=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return cityList;
    }
    public static List<County> getCountyList(Context context){
        InputStream is = null;
        List<County> countyList = new ArrayList<>();
        AssetManager manager = context.getAssets();
        try {
            is = manager.open("County.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                response.append(line);
            }
            Gson gson = new Gson();
            countyList= gson.fromJson(response.toString(),new TypeToken<List<County>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (is !=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return countyList;
    }
    public static String formatString(String s){
        if (s !=null){
            s = s.replaceAll(" ","");
        }
        return s;
    }
    public static String subName(String s){
        if (s.substring(s.length()-1,s.length()).equals("市")) {
            return s.substring(0, s.length() - 1);
        }
        else if (s.substring(s.length()-1,s.length()).equals("县"))
            return s.substring(0,s.length()-1);
        else
            return s;
    }
    public static String weather(String s){
        String str = new String();
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject object = jsonObject.getJSONObject("data");
            String wendu = object.getString("wendu");
            String ganmao = object.getString("ganmao");
            str = wendu+","+ganmao;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}

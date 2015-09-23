package com.example.myweatherdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myweatherdemo.model.City;
import com.example.myweatherdemo.model.County;
import com.example.myweatherdemo.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/17.
 */
public class MyWeatherDB {
    public static String DB_NAME = "my_weather";
    public static final int version = 1;
    private static MyWeatherDB myWeatherDB;
    private SQLiteDatabase db;
    private MyWeatherDB(Context context){
        MyWeatherOpenHelper dbHelper = new MyWeatherOpenHelper(context,DB_NAME,null, version);
        db = dbHelper.getWritableDatabase();
    }
    public synchronized static MyWeatherDB getInstance(Context context){
        if (myWeatherDB == null){
            myWeatherDB = new MyWeatherDB(context);
        }
        return myWeatherDB;
    }
    public void saveProvince(Province province){
        if (province != null){
            ContentValues values = new ContentValues();
            values.put("id",province.getId());
            values.put("name",province.getName());
            values.put("pro_sort",province.getProSort());
            values.put("pro_remark",province.getProRemark());
            db.insert("Province",null,values);
        }
    }
    public void saveCity(City city){
        if (city != null){
            ContentValues values = new ContentValues();
            values.put("id",city.getId());
            values.put("name",city.getName());
            values.put("pro_id",city.getProId());
            values.put("city_sort",city.getCitySort());
            db.insert("City",null,values);
        }
    }
    public void saveCounty(County county){
        if (county != null){
            ContentValues values = new ContentValues();
            values.put("id",county.getId());
            values.put("dis_name",county.getDisName());
            values.put("city_id",county.getCityId());
            values.put("dis_sort",county.getDisSort());
            db.insert("County",null,values);
        }
    }
//    public List<Province> loadProvince(){
//        List<Province> provinceList = new ArrayList<>();
//        Cursor cursor = db.query("Province",null,null,null,null,null,null);
//        if (cursor.moveToFirst()){
//            do {
//                Province province = new Province();
//                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
//                province.setName(cursor.getString(cursor.getColumnIndex("name")));
//                province.setProSort(cursor.getInt(cursor.getColumnIndex("pro_sort")));
//                province.setProRemark(cursor.getString(cursor.getColumnIndex("pro_remark")));
//                provinceList.add(province);
//            }while (cursor.moveToNext());
//        }
//        return provinceList;
//    }
    public List<City> loadCity(){
        List<City> cityList = new ArrayList<>();
        Cursor cursor = db.query("City",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setName(cursor.getString(cursor.getColumnIndex("name")));
                city.setProId(cursor.getInt(cursor.getColumnIndex("pro_id")));
                city.setCitySort(cursor.getInt(cursor.getColumnIndex("city_sort")));
                cityList.add(city);
            }while (cursor.moveToNext());
        }
        return cityList;
    }
    public List<County> loadCounty(){
        List<County> countyList = new ArrayList<>();
        Cursor cursor = db.query("County",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setDisName(cursor.getString(cursor.getColumnIndex("dis_name")));
                county.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
                county.setDisSort(cursor.getInt(cursor.getColumnIndex("dis_sort")));
                countyList.add(county);
            }while (cursor.moveToNext());
        }
        return countyList;
    }
}

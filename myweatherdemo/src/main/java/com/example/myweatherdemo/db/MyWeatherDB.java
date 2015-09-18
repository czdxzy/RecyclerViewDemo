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
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }
    public void saveCity(City city){
        if (city != null){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            db.insert("City",null,values);
        }
    }
    public void saveCounty(County county){
        if (county != null){
            ContentValues values = new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountyCode());
            db.insert("County",null,values);
        }
    }
    public List<Province> loadProvince(){
        List<Province> provinceList = new ArrayList<>();
        Cursor cursor = db.query("Province",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Province province = new Province();
                province.setProvinceId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                provinceList.add(province);
            }while (cursor.moveToNext());
        }
        return provinceList;
    }
    public List<City> loadCity(){
        List<City> cityList = new ArrayList<>();
        Cursor cursor = db.query("City",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                City city = new City();
                city.setCityId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
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
                county.setCountyId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                countyList.add(county);
            }while (cursor.moveToNext());
        }
        return countyList;
    }
}

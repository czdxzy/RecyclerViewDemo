package com.example.myweatherdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/9/17.
 */
public class MyWeatherOpenHelper extends SQLiteOpenHelper{
    public static final String CREATE_PROVINCE = "create table Province ("+
            "id integer primary key, "+
            "name text, "+
            "pro_sort integer, "+
            "pro_remark text)";
    public static final String CREATE_CITY = "create table City ("+
            "id integer primary key, "+
            "name text, "+
            "pro_id integer, "+
            "city_sort integer)";
    public static final String CREATE_COUNTY = "create table County ("+
            "id integer primary key, "+
            "dis_name text, "+
            "city_id integer, "+
            "dis_sort integer)";
    public MyWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.example.myweatherdemo.activity;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.myweatherdemo.R;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * Created by Administrator on 2015/9/17.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 测试json读取
         */
        InputStream is = null;
        AssetManager manager = getAssets();
        try {
            is = manager.open("area-gb.json");
            String response;
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null){
//                response.append(line);
//            }
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            response = new String(buffer,"GB2312");

            //JSONArray jsonArray = new JSONArray(response.toString());
            //for (int i =0;i<jsonArray.length();i++){
                //JSONObject jsonObject = jsonArray.getJSONObject(i);
                //String id = jsonObject.getString("id");
                //String name = jsonObject.getString("name");
                //Log.d("MainActivity","id= "+id);
                //Log.d("MainActivity","name= "+name);
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

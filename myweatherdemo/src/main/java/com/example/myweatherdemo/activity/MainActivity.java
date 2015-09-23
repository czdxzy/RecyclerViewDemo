package com.example.myweatherdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myweatherdemo.R;
import com.example.myweatherdemo.model.City;
import com.example.myweatherdemo.model.County;
import com.example.myweatherdemo.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

/**
 * Created by Administrator on 2015/9/17.
 */
public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private TextView weatherTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.city);
        weatherTextView = (TextView) findViewById(R.id.weather);
    }

    public void click(View view){
        Intent intent = new Intent(MainActivity.this,ChooseAreaActivity.class);
        startActivityForResult(intent, 1);
        //startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
            textView.setText(data.getStringExtra("name"));
            Log.d("MainActivity","dataweather------"+data.getStringExtra("weather"));
            weatherTextView.setText(data.getStringExtra("weather"));
    }
}

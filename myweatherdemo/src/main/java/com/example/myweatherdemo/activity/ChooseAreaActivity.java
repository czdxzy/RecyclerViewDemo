package com.example.myweatherdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myweatherdemo.Adapter.ChooseAreaAdapter;
import com.example.myweatherdemo.R;
import com.example.myweatherdemo.model.City;
import com.example.myweatherdemo.model.County;
import com.example.myweatherdemo.model.Province;
import com.example.myweatherdemo.util.HttpCallbackListener;
import com.example.myweatherdemo.util.HttpUtil;
import com.example.myweatherdemo.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015/9/22.
 */
public class ChooseAreaActivity extends Activity{
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private TextView textView;
    static int flag = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            textView.setText((String)msg.obj);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_area);
        initProvinceDatas();
        initView();
        final ChooseAreaAdapter adapter = new ChooseAreaAdapter(this,mDatas);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter.setOnItemClickListener(new ChooseAreaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TextView textView, int position) {

                if (flag == 0){
                    Province province=new Province();
                    for (int i = 0;i< Utils.getProvinceList(ChooseAreaActivity.this).size();i++){
                        if (Utils.getProvinceList(ChooseAreaActivity.this).get(i).getName().equals(textView.getText())) {
                            province = Utils.getProvinceList(ChooseAreaActivity.this).get(i);
                            break;
                        }
                    }
                    final Province finalProvince = province;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.obj = finalProvince.getName();
                            handler.sendMessage(message);
                        }
                    }).start();
                    flag++;
                    mDatas.clear();
                    initCityDatas(province);
                    adapter.notifyDataSetChanged();

                }else if(flag == 1){
                    City city = new City();
                    for (int i = 0 ;i<Utils.getCityList(ChooseAreaActivity.this).size();i++){
                        if (Utils.getCityList(ChooseAreaActivity.this).get(i).getName().equals(textView.getText())){
                            city = Utils.getCityList(ChooseAreaActivity.this).get(i);
                            break;
                        }
                    }
                    final City finalCity = city;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.obj = finalCity.getName();
                            handler.sendMessage(message);
                        }
                    }).start();
                    flag++;
                    mDatas.clear();
                    initCountyDatas(city);
                    adapter.notifyDataSetChanged();
                }
                else if (flag == 2){
                    flag = 0;
                    final Intent intent = new Intent(ChooseAreaActivity.this,MainActivity.class);
                    intent.putExtra("name", textView.getText());
                    HttpUtil.sendHttpRequest("http://wthrcdn.etouch.cn/weather_mini?city=" + Utils.subName((String) textView.getText()), new HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            String weather = Utils.weather(response);
                            Log.d("MainActivity","--------------"+weather);
                            intent.putExtra("weather", weather);
                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(ChooseAreaActivity.this,"error",Toast.LENGTH_SHORT).show();
                        }
                    });
                    ChooseAreaActivity.this.setResult(2, intent);
                    finish();
                }
            }

        });

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        textView = (TextView) findViewById(R.id.choose_area);
        textView.setText("中国");
    }

    private void initProvinceDatas() {
        mDatas = new ArrayList<>();
        InputStream is = null;
        AssetManager manager = getAssets();
        try {
            is = manager.open("Province.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                response.append(line);
            }
            Gson gson = new Gson();
            List<Province> provinceList = gson.fromJson(response.toString(),new TypeToken<List<Province>>(){}.getType());
            for (int i=0;i<provinceList.size();i++){
                mDatas.add(provinceList.get(i).getName());
            }
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
    }
    private void initCityDatas(Province province){
            InputStream is = null;
            AssetManager manager = getAssets();
            try {
                is = manager.open("City.json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null){
                    response.append(line);
                }
                String s = Utils.formatString(response.toString());
                Gson gson = new Gson();
                List<City> CityList = gson.fromJson(s,new TypeToken<List<City>>(){}.getType());
                for (int i=0;i<CityList.size();i++){
                    if (province.getId() == CityList.get(i).getProId()){
                        mDatas.add(CityList.get(i).getName());
                    }
                }
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
    }
    private void initCountyDatas(City city){
        InputStream is = null;
        AssetManager manager = getAssets();
        try {
            is = manager.open("County.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                response.append(line);
            }
            String s = Utils.formatString(response.toString());
            Gson gson = new Gson();
            List<County> CountyList = gson.fromJson(s,new TypeToken<List<County>>(){}.getType());
            for (int i=0;i<CountyList.size();i++){
                if (city.getId() == CountyList.get(i).getCityId()){
                    mDatas.add(CountyList.get(i).getDisName());
                }
            }
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
    }
}

package com.example.myweatherdemo.util;

/**
 * Created by Administrator on 2015/9/17.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}

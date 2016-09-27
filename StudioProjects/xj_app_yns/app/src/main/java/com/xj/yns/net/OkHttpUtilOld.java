package com.xj.yns.net;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class OkHttpUtilOld {
    private static final int DOWN_SUCCESS = 0;
    private static final int DOWN_FAILED = 1;
    public static NetLoadListener loadListener;
    private String jsonString;
    static OkHttpUtilOld okHttpUtil = new OkHttpUtilOld();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("ZYM", "zym监听不为空" + loadListener);
            switch (msg.what) {
                case DOWN_SUCCESS:
                    if (loadListener != null) {
                        Log.d("ZYM", "zym监听不为空");
                        jsonString = (String) msg.obj;
                        loadListener.loadSuccess();
                    }
                    break;
                case DOWN_FAILED:
                    if (loadListener != null) {
                        loadListener.loadFailed();
                    }
                    break;
            }
        }
    };
    private Class<?extends Object> clazz;


    private OkHttpUtilOld() {

    }

    public static OkHttpUtilOld getInstance() {
        return okHttpUtil;
    }

    public void load(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(responseCallback);
    }
    public void load(String url,Class clazz) {
        this.clazz=clazz;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(responseCallback);
    }

    private Callback responseCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            handler.sendEmptyMessage(DOWN_FAILED);
            Log.d("ZYM", "zym失败");
        }

        @Override
        public void onResponse(Response response) throws IOException {
            int count = 0;
            Log.d("ZYM", "zym成功");
            String jsonString = response.body().string();
            List<?> list = JSON.parseArray(jsonString, clazz);
            Log.d("ZYM", jsonString + (count++));
            Message message = handler.obtainMessage();
            message.obj = jsonString;
            message.what = DOWN_SUCCESS;
            handler.sendMessage(message);
            Log.d("ZYM", "zym发送消息成功");
        }
    };
    public void setLoadListener(NetLoadListener listener) {
        this.loadListener = listener;
    }

    public String getJson() {
        return jsonString;
    }

}

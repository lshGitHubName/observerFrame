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
public class OkHttpUtil {
    private static final int DOWN_LIST_SUCCESS = 0;
    private static final int DOWN_OBJECT_SUCCESS = 1;
    private static final int DOWN_FAILED = 2;
    public static NetLoadListener loadListener;
    private Class<?extends Object> clazz;
    List<? extends Object> list;
    Object object;
    static OkHttpUtil okHttpUtil = new OkHttpUtil();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DOWN_LIST_SUCCESS:
                    if (loadListener != null) {
                        Log.d("ZYM", "zym监听不为空");
                        list = (List<? extends Object>) msg.obj;
                        loadListener.loadSuccess();
                    }
                    break;
                case DOWN_OBJECT_SUCCESS:
                    if (loadListener != null) {
                        Log.d("ZYM", "zym监听不为空");
                        object= msg.obj;
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

    private OkHttpUtil() {

    }
    public static OkHttpUtil getInstance() {
        return okHttpUtil;
    }
    /*public void load(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(responseCallback);
    }*/
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
            if(jsonString.startsWith("[")){
                //解析成集合
                List<?> list = JSON.parseArray(jsonString, clazz);
                Message message = handler.obtainMessage();
                message.obj = list;
                message.what = DOWN_LIST_SUCCESS;
                handler.sendMessage(message);
                Log.d("ZYM", "zym发送集合消息成功");
            }else{

                //解析成对象
                Log.d("AAA", jsonString);
                Object object = JSON.parseObject(jsonString, clazz);
                Message message = handler.obtainMessage();
                message.obj = object;
                message.what = DOWN_OBJECT_SUCCESS;
                handler.sendMessage(message);
                Log.d("ZYM", "zym发送对象消息成功");
            }

        }
    };

    public void setLoadListener(NetLoadListener listener) {
        this.loadListener = listener;
    }

    public List<?extends Object> getList() {
        return list;
    }
    public Object getObject() {
        return object;
    }



}

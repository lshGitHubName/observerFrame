package com.xj.yns.net;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class ARLOkHttpUtil {
    private static final int DOWN_SUCCESS = 0;
    private static final int DOWN_FAILED = 1;
    public  static ARLLoadListener arlListener;
    private String jsonString;
    static ARLOkHttpUtil okHttpUtil = new ARLOkHttpUtil();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (arlListener != null) {
                        Log.d("ARL", "ARL监听不为空");
                        jsonString = (String) msg.obj;
                        arlListener.arlloadSuccess();
                    }
                    break;
                case 1:
                    Log.d("ARL", "ARL监听为空");
                    if (arlListener == null) {
                        arlListener.arlloadFailed();
                    }
                    break;
            }
        }
    };


    private ARLOkHttpUtil() {

    }

    public static ARLOkHttpUtil getInstance() {
        return okHttpUtil;
    }

    public void load(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(arlCallback);
    }

    private Callback arlCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            handler.sendEmptyMessage(DOWN_FAILED);
            Log.d("ARL", "ARL失败");
        }

        @Override
        public void onResponse(Response response) throws IOException {
            int count = 0;
            Log.d("ARL", "ARL成功");
            String jsonString = response.body().string();
            Log.d("ARL", jsonString + (count++));
            Message message = handler.obtainMessage();
            message.obj = jsonString;
            message.what = DOWN_SUCCESS;
            handler.sendMessage(message);
        }
    };

    public void setARLLoadListener(ARLLoadListener listener) {
        this.arlListener = listener;
    }


    public String getJson() {
        return jsonString;
    }
}

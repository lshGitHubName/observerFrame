package com.xj.yns.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.entity.Home.CompanyDetails;
import com.xj.yns.net.NetLoadListener;
import com.xj.yns.net.OkHttpUtil;
import com.xj.yns.view.manager.BaseUI;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class HomeDetailsUI extends BaseUI {

    private static Activity activity;
    private static TextView tv;
    private ImageView iv;
    private OkHttpUtil okHttpUtil;

    public HomeDetailsUI(Context context, Bundle bundle) {
        super(context, bundle);
        this.bundle = bundle;

    }

    @Override
    public void init() {
        showInMiddle = (LinearLayout) View.inflate(context, R.layout.home_details, null);
        geNetData(ConstantValue.HOME_GV_DETAIL_URL + bundle.getInt("id"));
        tv = (TextView) findViewById(R.id.detail_tv);
        iv = (ImageView) findViewById(R.id.details_iv);
    }

    private void geNetData(String url) {
        okHttpUtil = OkHttpUtil.getInstance();
        okHttpUtil.load(url, CompanyDetails.class);
    }


    @Override
    public void setListener() {
        okHttpUtil.setLoadListener(new NetLoadListener() {
            @Override
            public void loadSuccess() {
                CompanyDetails companyDetails = (CompanyDetails) okHttpUtil.getObject();
                tv.setText("      "+companyDetails.getContent());
                DisplayImageOptions options = new DisplayImageOptions.Builder()
                        .showImageOnFail(R.drawable.isloading1)
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .build();

                ImageLoader.getInstance().displayImage(companyDetails.getPic(),iv, options);

            }
            @Override
            public void loadFailed() {
                Log.d("ZYM", "网络加载失败");
            }
        });

    }

    @Override
    public int getID() {
        return ConstantValue.VIEW_HOME_DETAIL;
    }
}

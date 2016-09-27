package com.xj.yns.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.adapter.HomeGridAdapter;
import com.xj.yns.entity.Home.CompanyLibs;
import com.xj.yns.entity.Home.HomeBanner;
import com.xj.yns.net.ARLLoadListener;
import com.xj.yns.net.ARLOkHttpUtil;
import com.xj.yns.view.manager.BaseUI;
import com.xj.yns.viewplus.AutoRollLayout;
import com.xj.yns.viewplus.MyGridView;

import java.util.ArrayList;
import java.util.List;

public class HomeUI extends BaseUI {

    protected static final String TAG = "HomeUI";
    //	首页轮播图
    private ViewPager homeViewPager;
    private LinearLayout homePointGroup;

    public MyGridView homeGview;
    private static HomeGridAdapter homeGridAdapter;
    private AutoRollLayout arl;
    private LinearLayout linearLayout;
    private ARLOkHttpUtil arlOkhttpUtil;
    ARLLoadListener loadListener;
    public int a = 10;
    private View.OnClickListener clickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "收到了", Toast.LENGTH_SHORT).show();
        }
    };

    public HomeUI(Context context, Bundle bundle) {
        super(context, bundle);

    }


    /**
     * 初始化RelativeLayout
     */
    public void init() {
        //首页中间布局
        showInMiddle = (LinearLayout) View.inflate(context, R.layout.yns_home, null);
        //首页的gridview
        homeGview = (MyGridView) findViewById(R.id.id_yns_home_gview);
        homeGview.setFocusable(false);
        homeGridAdapter = new HomeGridAdapter(context, R.layout.yns_home_item, homeGview);
        homeGview.setAdapter(homeGridAdapter);

        //获取轮播图数据，从服务器获取
        arl = (AutoRollLayout) findViewById(R.id.home_arl);
        //获取轮播图的数据
        getNetData();


    }


    List<HomeBanner> bannerlist = new ArrayList<HomeBanner>();
    List<CompanyLibs> companylist = new ArrayList<CompanyLibs>();

    private void getNetData() {
        arlOkhttpUtil = ARLOkHttpUtil.getInstance();
        arlOkhttpUtil.setARLLoadListener(new ARLLoadListener() {
            @Override
            public void arlloadSuccess() {
                Toast.makeText(context, "轮播图内容更新", Toast.LENGTH_SHORT).show();
                String json = arlOkhttpUtil.getJson();
                bannerlist = JSON.parseArray(json, HomeBanner.class);
                arl.setItems(bannerlist);
                arl.setAutoRoll(true);
            }

            @Override
            public void arlloadFailed() {
                Log.d("ARL", "轮播图内容更新失败");
                Toast.makeText(context, "轮播图内容更新失败", Toast.LENGTH_SHORT).show();
            }
        });
        arlOkhttpUtil.load(ConstantValue.HOME_BANNER_URL);
    }

    @Override
    public void onResume() {
        arl.setAutoRoll(true);
        super.onResume();
    }

    @Override
    public void onPause() {
        arl.setAutoRoll(false);
        super.onPause();
    }


    public void setData(List<CompanyLibs> list) {
        companylist = list;
    }

    @Override
    public int getID() {
        return ConstantValue.VIEW_HOME;
    }

    /**
     * 设置首页各控件的监听
     */
    public void setListener() {
//        Toast.makeText(context, "a:" + a, Toast.LENGTH_SHORT).show();
//        Toast.makeText(context, "clickListener1:" + clickListener1, Toast.LENGTH_SHORT).show();
        creatListener();

        //水平的scrollview的条目点击跳转
        LinearLayout ll = (LinearLayout) findViewById(R.id.id_home_subnav);
        int childCount = ll.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ll.getChildAt(i).setOnClickListener(clickListener1);
        }
        //"更多"的点击跳转
        TextView moreTv = (TextView) findViewById(R.id.home_more);
        moreTv.setOnClickListener(clickListener);


    }

    private void creatListener() {

    }


}

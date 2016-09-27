package com.xj.yns.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.adapter.MsgMarketAdapter;
import com.xj.yns.net.OkHttpUtil;
import com.xj.yns.view.manager.BaseUI;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class MessageMarketUI extends BaseUI{

    private GridView gridView;
    private OkHttpUtil okHttpUtil;

    public MessageMarketUI(Context context, Bundle bundle) {
        super(context, bundle);
        this.context=context;
    }

    @Override
    public void init() {
        showInMiddle = (LinearLayout) View.inflate(context, R.layout.yns_home_msgmarket, null);
        //各类信息的线性布局
        LinearLayout ll = (LinearLayout) findViewById(R.id.home_msgmarket_ll);
        //信息集市的gridview
        gridView = (GridView) findViewById(R.id.home_msgmarket_gv);
        MsgMarketAdapter msgMarketAdapter=new MsgMarketAdapter(context);
        msgMarketAdapter.setView(ll,gridView);
        gridView.setAdapter(msgMarketAdapter);

    }



    @Override
    public void setListener() {

    }

    @Override
    public int getID() {
        return ConstantValue.VIEW_HOME_MSGMARKET;
    }


}

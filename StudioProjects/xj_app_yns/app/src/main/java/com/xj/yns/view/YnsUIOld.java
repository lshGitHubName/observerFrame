package com.xj.yns.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.adapter.YnsAdapter;
import com.xj.yns.view.manager.BaseUI;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class YnsUIOld extends BaseUI {
    public YnsUIOld(Context context, Bundle bundle) {
        super(context, bundle);
    }

    @Override
    public void init() {
        showInMiddle = (LinearLayout) View.inflate(context, R.layout.home_yns, null);
        ListView listView= (ListView) findViewById(R.id.home_yns_lv);
        listView.setAdapter(new YnsAdapter(context));
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getID() {
        return ConstantValue.VIEW_HOME_YNS;
    }
}

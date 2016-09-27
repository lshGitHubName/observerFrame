package com.xj.yns.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.adapter.SnAppearanceAdapter;
import com.xj.yns.adapter.SnItemAdapter;
import com.xj.yns.view.manager.BaseUI;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class SnAppearanceUI extends BaseUI{
    public SnAppearanceUI(Context context, Bundle bundle) {
        super(context, bundle);
    }

    @Override
    public void init() {
        showInMiddle = (LinearLayout) View.inflate(context, R.layout.home_sn, null);
        ListView sn_lv= (ListView) findViewById(R.id.sn_lv);
        sn_lv.setAdapter(new SnAppearanceAdapter(context,new SnItemAdapter(context)));
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getID() {
        return ConstantValue.VIEW_HOME_SNAPPEARANCE;
    }
}

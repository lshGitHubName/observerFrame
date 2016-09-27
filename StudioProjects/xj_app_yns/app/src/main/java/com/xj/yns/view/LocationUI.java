package com.xj.yns.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.view.manager.BaseUI;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class LocationUI extends BaseUI {
    public LocationUI(Context context, Bundle bundle) {
        super(context, bundle);
    }

    @Override
    public void init() {
        //搜索界面中间布局
        showInMiddle = (LinearLayout) View.inflate(context, R.layout.yns_location, null);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getID() {
        return ConstantValue.VIEW_YNS_LOCATION;
    }
}

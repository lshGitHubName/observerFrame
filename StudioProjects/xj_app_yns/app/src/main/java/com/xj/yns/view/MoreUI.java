package com.xj.yns.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.adapter.MoreAdapter;
import com.xj.yns.view.manager.BaseUI;
import com.xj.yns.viewplus.GlideRoundTransform;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class MoreUI extends BaseUI {
    public MoreUI(Context context, Bundle bundle) {
        super(context, bundle);
    }

    @Override
    public void init() {
        showInMiddle = (LinearLayout) View.inflate(context, R.layout.home_more, null);
        GridView moreGv= (GridView) findViewById(R.id.home_more_gridview);
        moreGv.setAdapter(new MoreAdapter(context,new GlideRoundTransform(context,30)));
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getID() {
        return ConstantValue.VIEW_HOME_MORE;
    }
}

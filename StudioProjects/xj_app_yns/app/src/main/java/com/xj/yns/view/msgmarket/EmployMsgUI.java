package com.xj.yns.view.msgmarket;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.adapter.KindsMsgAdapter;
import com.xj.yns.view.manager.BaseUI;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class EmployMsgUI extends BaseUI {

    public EmployMsgUI(Context context, Bundle bundle) {
        super(context, bundle);
        this.bundle=bundle;
    }
    @Override
    public void init() {
        //首页中间布局
        showInMiddle = (LinearLayout) View.inflate(context, R.layout.msgmarket_generalmsg, null);
        TextView msg_title= (TextView) findViewById(R.id.msg_title);
        msg_title.setText("用工信息");
    }
    @Override
    public void setListener() {
        ListView generalmsg_lv = (ListView) findViewById(R.id.generalmsg_lv);
        generalmsg_lv.setAdapter(new KindsMsgAdapter(context,bundle.getString("url")));
    }
    @Override
    public int getID() {
        return ConstantValue.VIEW_MSGMARKET_EMPLOY;
    }
}

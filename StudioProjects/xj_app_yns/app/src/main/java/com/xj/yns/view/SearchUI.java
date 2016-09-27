package com.xj.yns.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.view.manager.BaseUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class SearchUI extends BaseUI {

    private EditText search_et;

    public SearchUI(Context context, Bundle bundle) {
        super(context, bundle);
    }

    @Override
    public void init() {
        //搜索界面中间布局
        showInMiddle = (LinearLayout) View.inflate(context, R.layout.yns_search, null);
        search_et = (EditText) findViewById(R.id.search_et);
//        setListener();
    }

    @Override
    public void setListener() {
        search_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"搜索框被点击",Toast.LENGTH_SHORT).show();
                showPopupWindow();
            }
        });
    }

    //点击搜索框显示popupwindow
    private void showPopupWindow() {
        View  contentView=View.inflate(context,R.layout.search_tips,null);
        ListView tips_lv= (ListView) contentView.findViewById(R.id.search_tips_lv);
        PopupWindow pw=new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        pw.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        pw.showAsDropDown(findViewById(R.id.search_frame));
        tips_lv.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,getData()));
    }

    private List<String> getData() {
        List<String> tips=new ArrayList<>();
        tips.add("fall框架培训");
        tips.add("fall框架培训");
        tips.add("IOS讲义");
        tips.add("IOS讲义");
        tips.add("javaee企业实战");
        tips.add("javaee企业实战");
        tips.add("javaee企业实战");
        tips.add("XML讲义");
        tips.add("XML讲义");
        tips.add("XML讲义");
        return  tips;
    }

    @Override
    public int getID() {
        return ConstantValue.VIEW_HOME_SEARCH;
    }
}

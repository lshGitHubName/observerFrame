package com.xj.yns.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.adapter.CompanyLibsAdapter;
import com.xj.yns.view.manager.BaseUI;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class CompanyLibsUI extends BaseUI {


    int a = 10;
    public CompanyLibsUI(Context context, Bundle bundle) {
        super(context, bundle);//     1. 父类的构造方法
                               //   2. 显示初始化

        Log.d("CompanyLibsUI", "a:" + a);//   3.  自己构造方法的代码   2.3哪个先执行  搞忘了
    }




    @Override
    public void init() {
        showInMiddle = (LinearLayout) View.inflate(context, R.layout.home_companylibs, null);
        GridView gridView= (GridView) findViewById(R.id.home_companylibs_gridview);
        gridView.setAdapter(new CompanyLibsAdapter(context));
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getID() {
        return ConstantValue.VIEW_HOME_COMPANYLIBS;
    }
}

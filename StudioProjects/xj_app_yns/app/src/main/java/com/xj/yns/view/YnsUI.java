package com.xj.yns.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.adapter.YnsAdapter;
import com.xj.yns.view.manager.BaseUI;

/**
 * 测试UI
 * @author Administrator
 *
 */
public class YnsUI extends BaseUI{
	
	private ListView monitorListView;
	private String[] monitorDatas;
	private TextView tv;
	private ListView service_lv;

	public YnsUI(Context context, Bundle bundle) {
		super(context, bundle);
	}
	
	/**
	 * 初始化--只执行一次
	 * @return
	 */
	public void init() {
		monitorDatas = new String[]{"测试1","测试2", "测试3"};
		String[] categoriesData=new String[]{"分类1","分类2","分类3"};
		showInMiddle = (LinearLayout) View.inflate(context, R.layout.home_yns, null);
		ListView listView= (ListView) findViewById(R.id.home_yns_lv);
		listView.setAdapter(new YnsAdapter(context));

	}
	@Override
	public int getID() {
		return ConstantValue.VIEW_YNS;
	}

	@Override
	public void setListener() {

	}

	
}

package com.xj.yns.view;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.view.manager.BaseUI;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * “我的”UI
 * @author Administrator
 *
 */
public class MyUI extends BaseUI{
	private TextView textView;
	private View view;

	public MyUI(Context context, Bundle bundle) {
		super(context, bundle);
	}
	
	/**
	 * 初始化--只执行一次
	 * @return
	 */
	public void init() {
		/*textView = new TextView(context);
		LayoutParams layoutParams = textView.getLayoutParams();
		layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		textView.setLayoutParams(layoutParams);
		textView.setBackgroundColor(Color.WHITE);
		textView.setText("我的主界面");*/

		view = View.inflate(context, R.layout.yns_mine, null);

	}
	
	/**
	 * 获取child
	 * 
	 * @return
	 */
	public View getChild() {
		return view;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ConstantValue.VIEW_MY;
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		
	}

	
}

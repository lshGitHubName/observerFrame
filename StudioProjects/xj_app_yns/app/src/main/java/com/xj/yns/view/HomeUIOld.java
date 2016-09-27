package com.xj.yns.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ScrollView;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.adapter.HomeGridAdapter;
import com.xj.yns.util.Logger;
import com.xj.yns.view.manager.BaseUI;
import com.xj.yns.viewplus.MyGridView;

import java.util.ArrayList;
import java.util.List;

public class HomeUIOld extends BaseUI {

	protected static final String TAG = "HomeUI";
	
	/*********** 首页广告图片轮换(PageView)start **************/
	private ViewPager homeViewPager;
	private LinearLayout homePointGroup;
	private List<ImageView> ivList;
	private int previousPosition = 0;
	private boolean isStop = false;
	/*********** 首页广告图片轮换(PageView)end **************/
	
	/*********** 首页控件广告图片轮换start **************/

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			homeViewPager.setCurrentItem(homeViewPager.getCurrentItem() + 1);
		}
	};
	/*********** 首页控件广告图片轮换end **************/
	
	
	private MyGridView homeGview;
	private HomeGridAdapter homeGridAdapter;

	private ListView home_lv;
	
	public HomeUIOld(Context context, Bundle bundle) {
		super(context,bundle);
	}


	/**
	 * 初始化RelativeLayout
	 */
	public void init() {
		Log.i("HomeUI", 11+"");
		showInMiddle = (LinearLayout) View.inflate(context, R.layout.yns_home, null);
		
		ScrollView mScrollView=(ScrollView) showInMiddle.findViewById(R.id.home_scrollview);
		mScrollView.smoothScrollTo(0,0);
		/****************************二级导航列表start********************************************************/
		homeGview = (MyGridView) showInMiddle.findViewById(R.id.id_yns_home_gview);
//		使GridView从顶部开始显示
		homeGview.setFocusable(false);
		homeGridAdapter = new HomeGridAdapter(context,R.layout.yns_home_item, homeGview);
		homeGview.setAdapter(homeGridAdapter);
		/****************************二级导航列表end**********************************************************/
		
		/************************************** 首页广告图片轮换start ************************************************/
		homeViewPager = (ViewPager) showInMiddle.findViewById(R.id.id_home_viewpager_ad);
		
		
		homePointGroup = (LinearLayout) findViewById(R.id.id_home_point_group);
		ivList = new ArrayList<ImageView>();
		// 从服务器获取的图片的地址
		//indexBannar();
		int[] imageResIDs = { R.drawable.home_banner1, R.drawable.home_banner2, R.drawable.home_banner3,
				R.drawable.home_banner4 };
		ImageView iv;
		View pointView;
		LayoutParams params;
		for (int i = 0; i < imageResIDs.length; i++) {
			iv = new ImageView(context);
			iv.setBackgroundResource(imageResIDs[i]);
			ivList.add(iv);
			pointView = new View(context);
			params = new LayoutParams(10, 10);
			params.leftMargin = 8;
			pointView.setLayoutParams(params);
			pointView.setEnabled(false);
			pointView.setBackgroundResource(R.drawable.selector_vpad_spot);
			homePointGroup.addView(pointView);
		}
		HomeViewPagerAdapter mpa = new HomeViewPagerAdapter();
		homeViewPager.setAdapter(mpa);
		homeViewPager.setOnPageChangeListener(pageChangeListener);
		// 轮播图的默认显示图片索引计算
		int item = (Integer.MAX_VALUE / 2) - ((Integer.MAX_VALUE / 2) % ivList.size());
		homeViewPager.setCurrentItem(item);
		homePointGroup.getChildAt(previousPosition).setEnabled(true);
		/******************************* 首页广告图片轮换end ***********************************/
	}
	/******************************* 轮播图变化的监听 ***********************************/
	
	private OnPageChangeListener pageChangeListener=new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			Log.i("HomeUI", arg0+"");
			homePointGroup.getChildAt(1).setEnabled(false);
			
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	/*******************************************
	 * 首页广告图片轮换start*************************************************
	 ********/
	@Override
	public void onResume() {
		// 改变广告切换的图片
		isStop = false;
		changImage();
		super.onResume();
	}

	@Override
	public void onPause() {
		isStop = true;
		super.onPause();
	}

	private void changImage() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 广告图片轮换
				while (!isStop) {
					SystemClock.sleep(2000);
					handler.sendEmptyMessage(0);
				}
				Logger.i(TAG, "循环结束了");
			}
		}).start();
	}

	/**
	 * 首页广告图片轮换适配器
	 * 
	 * @author Administrator
	 *
	 */
	private class HomeViewPagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			homeViewPager.removeView(ivList.get(position % ivList.size()));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			homeViewPager.addView(ivList.get(position % ivList.size()));
			return ivList.get(position % ivList.size());
		}
	}

	/*************************** 首页广告图片轮换end ******************************************************/
	
	@Override
	public int getID() {
		return ConstantValue.VIEW_HOME;
	}
	
	 
	 /**
	 * 设置首页各控件的监听
	 */
	public void setListener() {
		
	}

}

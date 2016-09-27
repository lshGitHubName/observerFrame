package com.xj.yns.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.viewpagerindicator.TabPageIndicator;
import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.adapter.NewsListAdapter;
import com.xj.yns.adapter.NewsPagerAdapter;
import com.xj.yns.net.OkHttpUtil;
import com.xj.yns.view.manager.BaseUI;

/**
 * 测试UI
 * @author Administrator
 *
 */
public class NewsUI extends BaseUI{
	
	private ListView news_lv;
	private NewsListAdapter newsListAdapter;
	private String[] alertDatas;
	private TabPageIndicator tpi;
	private ViewPager vp;
	private OkHttpUtil okHttpUtil;
	private NewsPagerAdapter pagerAdapter;


	public NewsUI(Context context, Bundle bundle) {
		super(context,bundle);
	}
	/**
	 * 初始化--只执行一次
	 * @return
	 */

	String[] url=new String[]{ConstantValue.YNS_ALLNEWS_URL,ConstantValue.YNS_PLANT_URL,
			ConstantValue.YNS_SN_URL,ConstantValue.YNS_NZ_URL,
			ConstantValue.YNS_FEED_URL};
	String[] titles=new String[]{"全部","种植","三农","农资","养殖"};
	public void init() {
		showInMiddle = (LinearLayout) View.inflate(context, R.layout.yns_news, null);
		tpi = (TabPageIndicator) findViewById(R.id.news_tpi);
		vp = (ViewPager) findViewById(R.id.news_vp);
//		news_lv = (ListView) findViewById(R.id.news_lv);
//		newsListAdapter = new NewsListAdapter(context);
//		news_lv.setAdapter(newsListAdapter);
//		tpi与vp关联
		pagerAdapter = new NewsPagerAdapter();
		vp.setAdapter(pagerAdapter);
		tpi.setViewPager(vp);
		vp.setCurrentItem(0);
		tpi.setCurrentItem(0);
		//给tpi设置监听
	}
/*

	private void getNetData(String url) {
		okHttpUtil = OkHttpUtil.getInstance();
		okHttpUtil.setLoadListener(new NetLoadListener() {
			@Override
			public void loadSuccess() {
				List<SingleNews> list = (List<SingleNews>) okHttpUtil.getList();
			}

			@Override
			public void loadFailed() {

			}
		});
		okHttpUtil.load(url, SingleNews.class);
	}
*/


	@Override
	public int getID() {
		return ConstantValue.VIEW_NEWS;
	}

	@Override
	public void setListener() {

	}

	 /*public class PagerListener implements ViewPager.OnPageChangeListener {

		 @Override
		 public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

		 }

		 @Override
		 public void onPageSelected(int position) {

		 }

		 @Override
		 public void onPageScrollStateChanged(int state) {

		 }
	 }*/


}

package com.xj.yns.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

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
public class NewsUIOLd extends BaseUI{

	private ListView news_lv;
	private NewsListAdapter newsListAdapter;
	private String[] alertDatas;
	private TabPageIndicator tpi;
	private ViewPager vp;
	private OkHttpUtil okHttpUtil;
	private NewsPagerAdapter pagerAdapter;


	public NewsUIOLd(Context context, Bundle bundle) {
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
		tpi.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
//				new NewsPager().setUrl(url[position]);
				getNetData(url[position]);
				Toast.makeText(context,"第"+position+"个界面",Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		vp.setCurrentItem(0);
		//给tpi设置监听
	}

	private void getNetData(String url) {


	}


	@Override
	public int getID() {
		return ConstantValue.VIEW_NEWS;
	}

	@Override
	public void setListener() {

	}

	  /*pagerAdapter=new PagerAdapter() {
		@Override
		public int getCount() {
			return titles==null?0:titles.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view==object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			NewsPager newsPager=new NewsPager(container.getContext(),null);
			View view = newsPager.getView();
			container.addView(view);
			return  view ;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {

		}

		@Override
		public CharSequence getPageTitle(int position) {
			return titles[position];
		}
	};*/


}

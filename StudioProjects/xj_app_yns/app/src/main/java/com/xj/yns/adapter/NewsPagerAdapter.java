package com.xj.yns.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xj.yns.ConstantValue;
import com.xj.yns.entity.yns.SingleNews;
import com.xj.yns.pager.NewsPager;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class NewsPagerAdapter extends PagerAdapter {

    String[] url=new String[]{ConstantValue.YNS_ALLNEWS_URL,ConstantValue.YNS_PLANT_URL,
            ConstantValue.YNS_SN_URL,ConstantValue.YNS_NZ_URL,
            ConstantValue.YNS_FEED_URL};
    String[] titles=new String[]{"全部","种植","三农","农资","养殖"};
    private List<SingleNews> newsList;

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d("AAA","instantiateItem===="+position);
//        NewsPager newsPager=new NewsPager(container.getContext(),url[position]);
        NewsPager newsPager=new NewsPager(container.getContext(),null);
        newsPager.setUrl("http://192.168.1.106:8080/text.json");
        View view = newsPager.getView();
        container.addView(view);
        return  view ;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    /*public  void setData(List<SingleNews> list) {
        newsList.clear();
        newsList.addAll(list);
        notifyDataSetChanged();
    }*/
}

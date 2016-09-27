package com.xj.yns.pager;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.adapter.NewsPagerAdapter;
import com.xj.yns.entity.yns.SingleNews;
import com.xj.yns.net.NetLoadListener;
import com.xj.yns.net.OkHttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class NewsPager1 {
    private  Context context;

    View rootView;
    private  ListView pager_lv;
    private OkHttpUtil okHttpUtil;

    public NewsPager1(){

    }
    public  void setUrl(String url){
        getNetData(url);
    }

    private void getNetData(String url) {
        okHttpUtil = OkHttpUtil.getInstance();
        okHttpUtil.setLoadListener(loadListener);
        okHttpUtil.load(url, SingleNews.class);
    }

    public  List<SingleNews> list=new ArrayList<>();
    NetLoadListener loadListener = new NetLoadListener() {
        @Override
        public void loadSuccess() {
            Log.d("JORDAN", "加载成功");
            list.clear();
            List<SingleNews> newslist = (List<SingleNews>) okHttpUtil.getList();
            list.addAll(newslist);
            Log.d("JORDAN", "LSIT.SIZE()"+list.size());
//            pager_lv.setAdapter(newsListAdapter);
            baseAdapter.notifyDataSetChanged();
            baseAdapter.notifyDataSetInvalidated();
        }
        @Override
        public void loadFailed() {
            Log.d("JORDAN", "网络加载失败");
        }
    };


    public NewsPager1(Context context, NewsPagerAdapter adapter) {
        this.context=context;
        rootView = View.inflate(context, R.layout.yns_news_category, null);
        pager_lv = (ListView) rootView.findViewById(R.id.pager_news_category);
//        BaseAdapter baseAdapter=new BaseAdapter();
        pager_lv.setAdapter(baseAdapter);
    }

    public View getView() {
        return rootView;
    }

   public BaseAdapter baseAdapter=new BaseAdapter() {
        @Override
        public int getCount() {
            Log.d("JORDAN", "getcount执行"+list);
            return list == null ? 0 : list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(context, R.layout.yns_common_listitem, null);
                holder.single_news_iv = (ImageView) convertView.findViewById(R.id.single_news_iv);
                holder.singlenews_title = (TextView) convertView.findViewById(R.id.single_news_title);
                holder.singlenews_time = (TextView) convertView.findViewById(R.id.single_news_time);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            SingleNews singleNews = list.get(position);
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .displayer(new RoundedBitmapDisplayer(20))
                    .showImageOnFail(R.drawable.isloading1)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();

            Log.d("JORDAN","kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
            ImageLoader.getInstance().displayImage(ConstantValue.HOME_HOST + singleNews.getPic(), holder.single_news_iv, options);
            holder.singlenews_title.setText(singleNews.getTitle());
            holder.singlenews_time.setText(singleNews.getRelease_date());
            return convertView;
        }


    };
        // 依据最新揭晓ListView的item的layout，确定需要适配替换修改的元素信息（后续可扩展，增加）
        class ViewHolder {
            ImageView single_news_iv;
            TextView singlenews_title;
            TextView singlenews_time;
        }


    }

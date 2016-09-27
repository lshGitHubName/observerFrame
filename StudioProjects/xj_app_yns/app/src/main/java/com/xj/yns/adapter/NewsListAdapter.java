package com.xj.yns.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.entity.yns.SingleNews;
import com.xj.yns.net.NetLoadListener;
import com.xj.yns.net.OkHttpUtil;

import java.util.List;

public class NewsListAdapter extends BaseAdapter {
	public Context context;  
	private OkHttpUtil okHttpUtil;

	public NewsListAdapter(Context context, List<SingleNews> list) {
		super();
		this.context = context;
		this.list=list;
		notifyDataSetChanged();
	}

	private void getNetData(String url) {
		okHttpUtil = OkHttpUtil.getInstance();
		okHttpUtil.setLoadListener(loadListener);
		okHttpUtil.load(url, SingleNews.class);
	}

	private List<SingleNews> list;
	NetLoadListener loadListener = new NetLoadListener() {
		@Override
		public void loadSuccess() {
			Log.d("ZYM", "加载成功");
			list = (List<SingleNews>) okHttpUtil.getList();
			notifyDataSetChanged();
		}

		@Override
		public void loadFailed() {
			Log.d("ZYM", "网络加载失败");
		}
	};


	@Override
	public int getCount() {
		return list==null?0:list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
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
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		SingleNews singleNews = list.get(position);
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.displayer(new RoundedBitmapDisplayer(20))
				.showImageOnFail(R.drawable.isloading1)
				.cacheInMemory(true)
				.cacheOnDisk(true)
				.build();
		ImageLoader.getInstance().displayImage(ConstantValue.HOME_HOST + singleNews.getPic(), holder.single_news_iv, options);
		holder.singlenews_title.setText(singleNews.getTitle());
		holder.singlenews_time.setText(singleNews.getRelease_date());
		return convertView;
	}
	// 依据最新揭晓ListView的item的layout，确定需要适配替换修改的元素信息（后续可扩展，增加）
	class ViewHolder {
		ImageView single_news_iv;
		TextView singlenews_title;
		TextView singlenews_time;
	}

}

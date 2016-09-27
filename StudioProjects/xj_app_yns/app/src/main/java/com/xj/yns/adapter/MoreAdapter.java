package com.xj.yns.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xj.yns.R;
import com.xj.yns.entity.Goods;
import com.xj.yns.viewplus.GlideRoundTransform;

import java.io.IOException;
import java.util.List;

/**************************************GridView的适配器*****************************************/

/**
 * 创建首页商品展示gridView的适配器
 * 
 */
public class MoreAdapter extends BaseAdapter {

	private final GlideRoundTransform glideRoundTransform;
	private Context context;
		//图片封装为一个数组
		private int[] homeItemIcon = { R.drawable.home_banner1, R.drawable.home_banner1,
				R.drawable.home_banner1, R.drawable.home_banner1,
				R.drawable.home_banner1,R.drawable.home_banner1};

	public MoreAdapter(Context context, GlideRoundTransform glideRoundTransform) {
			super();
			this.context = context;
		this.glideRoundTransform=glideRoundTransform;

//			getData();

		}


	private void getData() {
		String HOST = "http://192.168.1.188:8080/ynsServer/api/getGoods";
		OkHttpClient okHttpClient = new OkHttpClient();
		Request request = new Request.Builder()
				.url(HOST)
				.get()
				.build();
		okHttpClient.newCall(request).enqueue(responseCallback);
	}

	private static Callback responseCallback=new Callback() {
		@Override
		public void onFailure(Request request, IOException e) {
			Log.d("ZYM","失败");
		}

		@Override
		public void onResponse(Response response) throws IOException {
			Log.d("ZYM","成功");
			String jsonString = response.body().string();
			List<Goods> goodsList = JSON.parseArray(jsonString, Goods.class);
			Log.d("ZYM",goodsList+"");
		}
	};
		@Override
		public int getCount() {
//			return data==null?0:data.size();
			return homeItemIcon==null?0:12;
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
				convertView =View.inflate(context, R.layout.home_more_item, null);
				holder.more_item_iv = (ImageView) convertView.findViewById(R.id.more_item_iv);
				//needUpdate.add(holder.progressText);
				// A tag can be used to mark a view in its hierarchy and does not have to be unique within the hierarchy.
				// Tags can also be used to store data within a view without resorting to another data structure.
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Glide.with(context).load(homeItemIcon[0]).centerCrop().transform(glideRoundTransform).into(holder.more_item_iv);
//			holder.homeItemIcon.setImageResource(homeItemIcon[position]);
//			holder.homeItemName.setText(homeItemName[position]);
			return convertView;
		}

	// 依据首页GridView的item的layout，确定需要适配替换修改的元素信息（后续可扩展，增加）
		class ViewHolder {
			ImageView more_item_iv;
		}
	}
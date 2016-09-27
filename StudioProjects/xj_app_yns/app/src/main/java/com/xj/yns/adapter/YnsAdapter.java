package com.xj.yns.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xj.yns.R;

/**************************************GridView的适配器*****************************************/

/**
 * 创建首页商品展示gridView的适配器
 * 
 */
public class YnsAdapter extends BaseAdapter {

	private Context context;
		//图片封装为一个数组
		private int[] homeItemIcon = { R.drawable.home_banner1, R.drawable.home_banner1,
				R.drawable.home_banner1, R.drawable.home_banner1,
				R.drawable.home_banner1,R.drawable.home_banner1};

	public YnsAdapter(Context context) {
			super();
			this.context = context;
		}

		@Override
		public int getCount() {
//			return data==null?0:data.size();
			return homeItemIcon==null?0:10;
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
				convertView =View.inflate(context, R.layout.home_yns_item, null);
				holder.check_more = (TextView) convertView.findViewById(R.id.check_more);

				holder.check_more.setOnClickListener(clickListener);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			return convertView;
		}

	// 依据首页GridView的item的layout，确定需要适配替换修改的元素信息（后续可扩展，增加）
		class ViewHolder {
			ImageView more_item_iv;
			TextView check_more;
		}

	View.OnClickListener clickListener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Toast.makeText(context,"查看更多",Toast.LENGTH_SHORT).show();
		}
	};
	}
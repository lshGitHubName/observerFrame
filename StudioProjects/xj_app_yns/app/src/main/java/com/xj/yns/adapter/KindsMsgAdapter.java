package com.xj.yns.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xj.yns.R;
import com.xj.yns.entity.msgmarket.TopMessage;
import com.xj.yns.net.NetLoadListener;
import com.xj.yns.net.OkHttpUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/9/19 0019.
 */

//综合信息
public class KindsMsgAdapter extends BaseAdapter {

    private final Context context;
    private OkHttpUtil okHttpUtil;

    public KindsMsgAdapter(Context context, String url) {
        this.context=context;
        getNetData(url);
    }

    private void getNetData(String url) {
        okHttpUtil = OkHttpUtil.getInstance();
        okHttpUtil.setLoadListener(loadListener);
        okHttpUtil.load(url, TopMessage.class);
    }


    private List<TopMessage> list;
    NetLoadListener loadListener = new NetLoadListener() {
        @Override
        public void loadSuccess() {
            Log.d("ZYM", "加载成功");
            list = (List<TopMessage>) okHttpUtil.getList();
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
            convertView =View.inflate(context, R.layout.generalmsg_lv_item, null);
            holder.kindsmsg_item_tv= (TextView) convertView.findViewById(R.id.kindsmsg_item_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TopMessage topMessage = list.get(position);
        holder.kindsmsg_item_tv.setText(topMessage.getTitle());
        return convertView;
    }

    // 依据首页GridView的item的layout，确定需要适配替换修改的元素信息（后续可扩展，增加）
    class ViewHolder {
        ImageView kindsmsg_item_iv;
        TextView kindsmsg_item_tv;
    }
}

package com.xj.yns.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xj.yns.R;
import com.xj.yns.viewplus.GlideRoundTransform;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class SnItemAdapter extends BaseAdapter {

    private final Context context;

    public SnItemAdapter(Context context) {
        this.context=context;
    }
    @Override
    public int getCount() {
        return 5;
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
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(context, R.layout.sn_gridview_item,null);
            holder.sn_gridview_item_iv = (ImageView) convertView.findViewById(R.id.sn_gridview_item_iv);
            holder.sn_gridview_item_tv = (TextView) convertView.findViewById(R.id.sn_gridview_item_tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.sn_gridview_item_iv.setImageResource(R.drawable.home_list1);
        Glide.with(context).load(R.drawable.home_list1).transform(new GlideRoundTransform(context,10)).into(holder.sn_gridview_item_iv);

        return convertView;
    }
    class ViewHolder{
        ImageView sn_gridview_item_iv;
        TextView sn_gridview_item_tv;
    }



}

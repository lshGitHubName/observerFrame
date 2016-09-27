package com.xj.yns.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.xj.yns.R;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class ServiceAdapter extends BaseAdapter {

    private final Context mContext;
    private final String[] categories;
    private final HomeGridAdapter gridAdapter;

    public ServiceAdapter(Context mContext, String[] categories,HomeGridAdapter gridAdapter){
        this.mContext=mContext;
        this.categories=categories;
        this.gridAdapter=gridAdapter;
    }
    @Override
    public int getCount() {
        return categories==null?0:categories.length;
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
            convertView=View.inflate(mContext, R.layout.service_categories_item,null);
            holder.service_categories_tv= (TextView) convertView.findViewById(R.id.service_categories_tv);
            holder.service_categories_gv= (GridView) convertView.findViewById(R.id.service_categories_gv);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.service_categories_tv.setText(categories[position]);
        holder.service_categories_gv.setAdapter(gridAdapter);
        return convertView;
    }

    class  ViewHolder{
        TextView service_categories_tv;
        GridView service_categories_gv;
    }
}

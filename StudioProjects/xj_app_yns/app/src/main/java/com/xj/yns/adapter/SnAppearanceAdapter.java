package com.xj.yns.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.xj.yns.R;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class SnAppearanceAdapter extends BaseAdapter {

    private final Context context;
    private final SnItemAdapter adapter;

    public SnAppearanceAdapter(Context context,SnItemAdapter adapter) {
        this.context=context;
        this.adapter=adapter;
    }

    @Override
    public int getCount() {
        return 10;
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
            convertView=View.inflate(context, R.layout.sn_appearance_item,null);
            holder.sn_item_tv = (TextView) convertView.findViewById(R.id.sn_item_tv);
            holder.sn_item_gv = (GridView) convertView.findViewById(R.id.sn_item_gv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.sn_item_gv.setAdapter(adapter);
        return convertView;
    }

    class ViewHolder{
        TextView sn_item_tv;
        GridView sn_item_gv;
    }
}

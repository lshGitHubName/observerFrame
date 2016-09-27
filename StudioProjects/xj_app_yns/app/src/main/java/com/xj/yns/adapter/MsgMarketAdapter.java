package com.xj.yns.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.entity.msgmarket.TopMessage;
import com.xj.yns.net.NetLoadListener;
import com.xj.yns.net.OkHttpUtil;
import com.xj.yns.view.manager.MiddleManager;
import com.xj.yns.view.msgmarket.EmployMsgUI;
import com.xj.yns.view.msgmarket.FindJobMsgUI;
import com.xj.yns.view.msgmarket.OtherMsgUI;
import com.xj.yns.view.msgmarket.SeekBuyerMsgUI;
import com.xj.yns.view.msgmarket.SupplyMsgUI;

import java.util.List;

/**************************************
 * GridView的适配器
 *****************************************/

/**
 * 创建首页商品展示gridView的适配器
 *
 */
public class MsgMarketAdapter extends BaseAdapter {

    private Context context;
    private OkHttpUtil okHttpUtil;
    private LinearLayout ll;
    private GridView gridView;
    private List<TopMessage> list;
    //图片封装为一个数组
    private int[] homeItemIcon = {R.drawable.home_banner1, R.drawable.home_banner1,
            R.drawable.home_banner1, R.drawable.home_banner1,
            R.drawable.home_banner1, R.drawable.home_banner1};
    private String[] msg = new String[]{"用工信息", "求职信息", "供应信息", "求购信息", "其他"};

    String[] url=new String[] {ConstantValue.MSG_URL0,ConstantValue.MSG_URL1,ConstantValue.MSG_URL2,ConstantValue.MSG_URL3,ConstantValue.MSG_URL4};

    Class[] clazz=new Class[]{EmployMsgUI.class,FindJobMsgUI.class, SupplyMsgUI.class, SeekBuyerMsgUI.class,OtherMsgUI.class};
    public MsgMarketAdapter(Context context) {
        super();
        this.context = context;
        getNetData();
    }

    private void getNetData() {
        okHttpUtil = OkHttpUtil.getInstance();
        okHttpUtil.setLoadListener(loadListener);
        okHttpUtil.load(ConstantValue.HOME_MSGMARKET_URL, TopMessage.class);
    }

    private NetLoadListener loadListener = new NetLoadListener() {
        @Override
        public void loadSuccess() {
            list = (List<TopMessage>) okHttpUtil.getList();
            notifyDataSetChanged();
            setText();
        }

        @Override
        public void loadFailed() {

        }
    };

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
            convertView = View.inflate(context, R.layout.home_msgmarket_item, null);
            holder.msgmarket_iv = (ImageView) convertView.findViewById(R.id.msgmarket_item_iv);
            holder.msgmarket_tv = (TextView) convertView.findViewById(R.id.msgmarket_item_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TopMessage topMessage = list.get(position);
        holder.msgmarket_iv.setImageResource(homeItemIcon[position]);
        holder.msgmarket_tv.setText(msg[position]);
        return convertView;
    }

    public void setView(LinearLayout ll, GridView gridView) {
        this.ll = ll;
        this.gridView = gridView;
        //给gridview设置条目点击监听
        gridView.setOnItemClickListener(itemClickListener);
    }

    AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle=new Bundle();
                bundle.putString("url",url[position]);
                MiddleManager.getInstance().changeUI(clazz[position],bundle);
        }
    };

    private void setText() {
        int childCount = ll.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView tv = (TextView) ll.getChildAt(i);
            tv.setText("[" + msg[i] + "]         " + list.get(i).getTitle());
        }
    }


    // 依据首页GridView的item的layout，确定需要适配替换修改的元素信息（后续可扩展，增加）
    class ViewHolder {
        ImageView msgmarket_iv;
        TextView msgmarket_tv;
    }
}
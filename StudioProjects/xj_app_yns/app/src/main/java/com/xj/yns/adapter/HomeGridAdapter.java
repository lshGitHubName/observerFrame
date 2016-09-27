package com.xj.yns.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.entity.Home.CompanyLibs;
import com.xj.yns.net.NetLoadListener;
import com.xj.yns.net.OkHttpUtil;
import com.xj.yns.view.HomeDetailsUI;
import com.xj.yns.view.manager.MiddleManager;
import com.xj.yns.viewplus.MyGridView;

import java.util.List;

/**************************************
 * GridView的适配器
 *****************************************/

/**
 * 创建首页商品展示gridView的适配器
 */
public class HomeGridAdapter extends BaseAdapter {

    private final int resource;
    private OkHttpUtil okHttpUtil;
    private Context context;
    private List<CompanyLibs> list;


    public HomeGridAdapter(Context context, int resource, MyGridView homeGview) {
        super();
        this.context = context;
        this.resource = resource;
        getNetData();

        //首页中的条目点击的监听
        homeGview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击后跳转到企业的详情界面，需要传入条目的id
                CompanyLibs companyLibs = list.get(position);
                int itemId = companyLibs.getId();
                Bundle bundle=new Bundle();
                bundle.putInt("id", itemId);
                MiddleManager.getInstance().changeUI(HomeDetailsUI.class,bundle);
            }
        });
    }

    private void getNetData() {
        okHttpUtil = OkHttpUtil.getInstance();
        okHttpUtil.setLoadListener(loadListener);
        okHttpUtil.load(ConstantValue.HOME_GRIDVIEW_URL, CompanyLibs.class);
    }


    NetLoadListener loadListener = new NetLoadListener() {
        @Override
        public void loadSuccess() {
            Log.d("ZYM", "加载成功");
            list = (List<CompanyLibs>) okHttpUtil.getList();
            notifyDataSetChanged();
        }

        @Override
        public void loadFailed() {
            Log.d("ZYM", "网络加载失败");
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
            convertView = View.inflate(context, resource, null);
//				convertView =View.inflate(context, R.layout.yns_home_item, null);
            holder.homeItemIcon = (ImageView) convertView.findViewById(R.id.id_yns_home_itemIcon);
            holder.homeItemName = (TextView) convertView.findViewById(R.id.id_yns_home_itemName);
            //needUpdate.add(holder.progressText);
            // A tag can be used to mark a view in its hierarchy and does not have to be unique within the hierarchy.
            // Tags can also be used to store data within a view without resorting to another data structure.
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CompanyLibs companyLibs = list.get(position);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(20))
                .showImageOnFail(R.drawable.isloading1)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(ConstantValue.HOME_HOST + companyLibs.getPic(), holder.homeItemIcon, options);
        holder.homeItemName.setText(companyLibs.getName());
        return convertView;
    }

    // 依据首页GridView的item的layout，确定需要适配替换修改的元素信息（后续可扩展，增加）
    class ViewHolder {
        ImageView homeItemIcon;
        TextView homeItemName;
    }
}
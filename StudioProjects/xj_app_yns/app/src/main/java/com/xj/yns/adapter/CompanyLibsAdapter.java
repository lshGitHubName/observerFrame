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
import com.xj.yns.entity.Home.CompanyLibs;
import com.xj.yns.net.NetLoadListener;
import com.xj.yns.net.OkHttpUtil;

import java.util.List;

/**************************************
 * GridView的适配器
 *****************************************/

/**
 * 创建首页商品展示gridView的适配器
 */
public class CompanyLibsAdapter extends BaseAdapter {

    private static Context context;
    static List<CompanyLibs> companyLibsList;
    private  OkHttpUtil okHttpUtil;
    //图片封装为一个数组
    private int[] homeItemIcon = {R.drawable.home_banner1, R.drawable.home_banner1,
            R.drawable.home_banner1, R.drawable.home_banner1,
            R.drawable.home_banner1, R.drawable.home_banner1};

    public CompanyLibsAdapter(Context context) {
        super();
        this.context = context;
        getNetData();


    }

    private void getNetData() {
        okHttpUtil = OkHttpUtil.getInstance();
        okHttpUtil.load(ConstantValue.HOME_GRIDVIEW_URL,CompanyLibs.class);
        okHttpUtil.setLoadListener(loadListener);
    }

    NetLoadListener loadListener = new NetLoadListener() {
        @Override
        public void loadSuccess() {
            companyLibsList= (List<CompanyLibs>) okHttpUtil.getList();
            notifyDataSetChanged();
        }
        @Override
        public void loadFailed() {
            Log.d("ZYM", "网络加载失败");
        }
    };


    @Override
    public int getCount() {
        return companyLibsList == null ? 0 : companyLibsList.size();

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
            convertView = View.inflate(context, R.layout.home_companylibs_item, null);
            holder.companylibs_item_iv = (ImageView) convertView.findViewById(R.id.companylibs_item_iv);
            holder.companylibs_item_tv = (TextView) convertView.findViewById(R.id.companylibs_item_tv);
            //needUpdate.add(holder.progressText);
            // A tag can be used to mark a view in its hierarchy and does not have to be unique within the hierarchy.
            // Tags can also be used to store data within a view without resorting to another data structure.
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CompanyLibs companyLibs = companyLibsList.get(position);
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.isloading)
                .displayer(new RoundedBitmapDisplayer(20))
                .showImageOnFail(R.drawable.isloading1)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(ConstantValue.HOME_HOST+companyLibs.getPic(),holder.companylibs_item_iv,options);
        return convertView;
    }

    // 依据首页GridView的item的layout，确定需要适配替换修改的元素信息（后续可扩展，增加）
    class ViewHolder {
        ImageView companylibs_item_iv;
        TextView companylibs_item_tv;
    }
}
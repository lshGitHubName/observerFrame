package com.xj.yns.view.manager;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.util.PromptManager;
import com.xj.yns.view.LocationUI;
import com.xj.yns.view.SearchUI;

import org.apache.commons.lang3.StringUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * 管理标题容器的工具
 *
 * @author Administrator
 */
public class TitleManager implements Observer {
    private static TitleManager instance = new TitleManager();
    private Activity activity;
    private RelativeLayout search_engine;
    private ImageView location_iv;
    private ImageView menu_iv;

    private TitleManager() {
    }

    public static TitleManager getInstance() {
        if (instance == null) {
            instance = new TitleManager();
        }
        return instance;
    }

    //标题title容器
    //首页--未登陆的标题title
    private RelativeLayout unLoginTitle;
    //首页--已登陆的通用标题title
    private RelativeLayout commonTitle;

    private TextView commonTitleText;
    private ImageView commonTitleLeft;
    private ImageView commonTitleLeft1;

    public void init(Activity activity) {
        this.activity = activity;
        //定位图标
        location_iv = (ImageView) activity.findViewById(R.id.location_iv);
        //地图界面菜单图标
        menu_iv = (ImageView) activity.findViewById(R.id.menu_iv);
        //搜索框
        search_engine = (RelativeLayout) activity.findViewById(R.id.serarch_engine);
        //带搜索框的标题栏
        unLoginTitle = (RelativeLayout) activity.findViewById(R.id.id_home_title);
        //通用的登陆后的标题栏
        commonTitle = (RelativeLayout) activity.findViewById(R.id.ir_common_title);
        commonTitleText = (TextView) activity.findViewById(R.id.common_title_text);
        commonTitleLeft = (ImageView) activity.findViewById(R.id.id_db_title_left);
        setListener();
    }

    /**
     * 设置各控件的监听
     */
    private void setListener() {
        //点击返回键
        commonTitleLeft.setOnClickListener(listener);
        //点击搜索框跳转到搜索界面
        search_engine.setOnClickListener(listener);
        //点击地图定位图标
        location_iv.setOnClickListener(listener);

    }

    OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.id_db_title_left:
                    boolean result = MiddleManager.getInstance().goBack();
                    // 按下回退键，弹出系统弹出框
                    if (!result) {
                        PromptManager.showExitSystem(MiddleManager.getInstance().getContext());
                    }
                    break;
                case R.id.serarch_engine:
                    Toast.makeText(activity, "搜索框被点击", Toast.LENGTH_SHORT).show();
                    MiddleManager.getInstance().changeUI(SearchUI.class,null);
                    break;
                case R.id.location_iv:
                    Toast.makeText(activity, "定位图标被点击", Toast.LENGTH_SHORT).show();
                    MiddleManager.getInstance().changeUI(LocationUI.class,null);
                    break;
            }

        }
    };

    private void initTitle() {
        diplayTitle();
        commonTitle.setVisibility(View.VISIBLE);
    }

    private void diplayTitle() {
        unLoginTitle.setVisibility(View.GONE);
        commonTitle.setVisibility(View.GONE);
    }

    // 显示未签到的标题title
    public void showUnSignTitle() {
        diplayTitle();
        unLoginTitle.setVisibility(View.VISIBLE);
    }

    // 显示新闻的标题title
    public void showNewsTitle() {
        initTitle();
        commonTitleText.setText("新闻");
    }

    // 显示 服务的标题title
    public void showServiceTitle() {
        initTitle();
        commonTitleText.setText("益农社");
    }

    // 显示 我的的标题title
    public void showMyTitle() {
        initTitle();
        commonTitleText.setText("我的");
    }

    //显示首页商品详情
    public void showHomeDetailTitle() {
        initTitle();
        commonTitleText.setText("商品详情");
    }

    //显示首页更多
    public void showHomeMoreTitle() {
        initTitle();
        commonTitleText.setText(null);
    }

    //显示首页企业库
    public void showHomeCompanyLibsTitle() {
        initTitle();
        commonTitleText.setText(null);
    }

    //显示益农社
    public void showHomeYnsTitle() {
        initTitle();
        commonTitleText.setText(null);
    }

    //显示三农风光
    public void showHomeSnTitle() {
        initTitle();
        commonTitleText.setText(null);
    }
    //显示搜索界面
    public void showSearchTitle() {
        initTitle();
        commonTitleText.setText(null);
    }

    //显示地图
    public void showMapTitle() {
        initTitle();
        commonTitleText.setText(null);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data != null && StringUtils.isNumeric(data.toString())) {
            int id = Integer.parseInt(data.toString());
            if(id==ConstantValue.VIEW_HOME_YNS){
                location_iv.setVisibility(View.VISIBLE);
            }else{
                location_iv.setVisibility(View.GONE);
            }
            if(id==ConstantValue.VIEW_YNS_LOCATION){
                menu_iv.setVisibility(View.VISIBLE);
            }else{
                menu_iv.setVisibility(View.GONE);
            }
            switch (id) {
                case ConstantValue.VIEW_HOME:
                    showUnSignTitle();
                    break;
                case ConstantValue.VIEW_NEWS:
                    showNewsTitle();
                    break;
                case ConstantValue.VIEW_YNS:
                    showServiceTitle();
                    break;
                case ConstantValue.VIEW_MY:
                    showMyTitle();
                    break;
                case ConstantValue.VIEW_HOME_DETAIL:
                    showHomeDetailTitle();
                    break;
                //信息集市
                case ConstantValue.VIEW_HOME_MSGMARKET:
                    diplayTitle();
                    break;
                //三农风光
                case ConstantValue.VIEW_HOME_SNAPPEARANCE:
                    showHomeSnTitle();
                    break;
                //企业库
                case ConstantValue.VIEW_HOME_COMPANYLIBS:
                    showHomeCompanyLibsTitle();
                    break;
                //更多
                case ConstantValue.VIEW_HOME_MORE:
                    showHomeMoreTitle();
                    break;
                //地图定位
                case ConstantValue.VIEW_HOME_SEARCH:
                    showSearchTitle();
                    break;
                //地图定位
                case ConstantValue.VIEW_YNS_LOCATION:
                    showMapTitle();
                    break;
            }
        }
    }


}

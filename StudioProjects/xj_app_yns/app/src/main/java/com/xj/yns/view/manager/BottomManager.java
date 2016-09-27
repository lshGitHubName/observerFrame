package com.xj.yns.view.manager;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.view.HomeUI;
import com.xj.yns.view.MyUI;
import com.xj.yns.view.NewsUI;
import com.xj.yns.view.YnsUI;

import org.apache.commons.lang3.StringUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * 控制底部导航容器
 *
 * @author Administrator
 */
public class BottomManager implements Observer {
    protected static final String TAG = "BottomManager";

    Resources resources;

    /*******************
     * 第一步：管理对象的创建(单例模式)
     ***************************************************/
    // 创建一个静态实例
    private static BottomManager instance;

    // 构造私有
    private BottomManager() {
    }

    // 提供统一的对外获取实例的入口
    public static BottomManager getInstance() {
        if (instance == null) {
            instance = new BottomManager();
        }
        return instance;
    }

    /******************* 第二步：初始化各个导航容器及相关控件设置监听 *********************************/

    /**********
     * 底部菜单容器
     **********/
    private RelativeLayout bottomMenuContainer;
    /************
     * 底部导航
     ************/
    private LinearLayout commonBottom;//通用导航
    private LinearLayout otherBottom;// 其它导航

    /***************** 导航按钮 ******************/

    /************
     * 通用导航底部按钮
     ***********/
    private TextView homeButton;
    private TextView newsButton;
    private TextView serviceButton;
    private TextView myButton;

    public void init(Activity activity) {
        bottomMenuContainer = (RelativeLayout) activity.findViewById(R.id.id_ir_bottom);
        commonBottom = (LinearLayout) activity.findViewById(R.id.id_bottom_common);
        otherBottom = (LinearLayout) activity.findViewById(R.id.id_bottom_other);

        homeButton = (TextView) activity.findViewById(R.id.id_menu_homeName);
        newsButton = (TextView) activity.findViewById(R.id.id_menu_newsName);
        serviceButton = (TextView) activity.findViewById(R.id.id_menu_serviceName);
        myButton = (TextView) activity.findViewById(R.id.id_menu_myName);
        resources = activity.getResources();
        setListener();
    }

    /**
     * 设置各控件的监听事件
     */
    public void setListener() {
        homeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击首页，跳转到相应界面
                MiddleManager.getInstance().changeUI(HomeUI.class, null);
            }
        });
        newsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击“新闻”，跳转到相应界面
                MiddleManager.getInstance().changeUI(NewsUI.class, null);
            }
        });
        serviceButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击“益农社”，跳转到相应界面
                MiddleManager.getInstance().changeUI(YnsUI.class, null);
            }
        });
        myButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击“我的”，跳转到相应界面
                MiddleManager.getInstance().changeUI(MyUI.class, null);
            }
        });
    }

    /****************** 第三步：控制各个导航容器的显示和隐藏 *****************************************/

    /**
     * 完全隐藏所有底部导航
     */
    public void displayBottom() {

        Drawable drawable1 = resources.getDrawable(R.drawable.tab_home_normal);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight()); //设置边界
        homeButton.setCompoundDrawables(null, drawable1, null, null);//画在文字上面
        homeButton.setTextColor(resources.getColor(R.color.color_bottom_gray));//文字变成灰色

        Drawable drawable2 = resources.getDrawable(R.drawable.tab_news_normal);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight()); //设置边界
        newsButton.setCompoundDrawables(null, drawable2, null, null);//画在文字上面
        newsButton.setTextColor(resources.getColor(R.color.color_bottom_gray));//文字变成灰色

        Drawable drawable3 = resources.getDrawable(R.drawable.tab_service_normal);
        drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight()); //设置边界
        serviceButton.setCompoundDrawables(null, drawable3, null, null);//画在文字上面
        serviceButton.setTextColor(resources.getColor(R.color.color_bottom_gray));//文字变成灰色

        Drawable drawable4 = resources.getDrawable(R.drawable.tab_my_normal);
        drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight()); //设置边界
        myButton.setCompoundDrawables(null, drawable4, null, null);//画在文字上面
        myButton.setTextColor(resources.getColor(R.color.color_bottom_gray));//文字变成灰色

        commonBottom.setVisibility(View.GONE);
        otherBottom.setVisibility(View.GONE);
    }

    /**
     * 显示通用的底部导航
     */
    public void showCommonBottom(int id) {
        displayBottom();
        switch (id) {
            case ConstantValue.VIEW_HOME:
                //切换首页图片为黄色
                Drawable drawable1 = resources.getDrawable(R.drawable.tab_home_pressed);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight()); //设置边界
                homeButton.setCompoundDrawables(null, drawable1, null, null);//画在文字上面
                homeButton.setTextColor(resources.getColor(R.color.color_bottom_green));//文字变成蓝色
                break;
            case ConstantValue.VIEW_NEWS:
                //切换预警的图片为黄色
                Drawable drawable2 = resources.getDrawable(R.drawable.tab_news_pressed);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight()); //设置边界
                newsButton.setCompoundDrawables(null, drawable2, null, null);//画在文字上面
                newsButton.setTextColor(resources.getColor(R.color.color_bottom_green));//文字变成蓝色
                break;
            case ConstantValue.VIEW_YNS:
                //切换监控图片为黄色
                Drawable drawable3 = resources.getDrawable(R.drawable.tab_service_pressed);
                drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight()); //设置边界
                serviceButton.setCompoundDrawables(null, drawable3, null, null);//画在文字上面
                serviceButton.setTextColor(resources.getColor(R.color.color_bottom_green));//文字变成蓝色
                break;
            case ConstantValue.VIEW_MY:
                //切换我的图片为黄色
                Drawable drawable4 = resources.getDrawable(R.drawable.tab_my_pressed);
                drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight()); //设置边界
                myButton.setCompoundDrawables(null, drawable4, null, null);//画在文字上面
                myButton.setTextColor(resources.getColor(R.color.color_bottom_green));//文字变成蓝色
                break;
        }
        commonBottom.setVisibility(View.VISIBLE);
    }


    /**
     * 显示其它底部导航
     */
    public void showOtherBottom() {
        displayBottom();
        otherBottom.setVisibility(View.VISIBLE);
    }

    /**
     * 改变底部导航容器显示情况
     */
    public void changeBottomVisiblity(int type) {
        if (bottomMenuContainer.getVisibility() != type)
            bottomMenuContainer.setVisibility(type);
    }

    /***********************
     * 第四步：控制other导航内容显示
     ********************************************/

    @Override
    public void update(Observable observable, Object data) {
        if (data != null && StringUtils.isNumeric(data.toString())) {
            int id = Integer.parseInt(data.toString());
            switch (id) {
                case ConstantValue.VIEW_HOME:
                case ConstantValue.VIEW_NEWS:
                case ConstantValue.VIEW_YNS:
                case ConstantValue.VIEW_MY:
                    showCommonBottom(id);
                    break;
                case ConstantValue.VIEW_HOME_DETAIL:
                    displayBottom();
                    break;
                //信息集市底部容器隐藏
                case ConstantValue.VIEW_HOME_MSGMARKET:
                    displayBottom();
                    break;
                //三农风光底部容器隐藏
                case ConstantValue.VIEW_HOME_SNAPPEARANCE:
                    displayBottom();
                    break;
                //企业库底部容器隐藏
                case ConstantValue.VIEW_HOME_COMPANYLIBS:
                    displayBottom();
                    break;
                //更多底部容器隐藏
                case ConstantValue.VIEW_HOME_MORE:
                    displayBottom();
                    break;
                //搜索界面底部容器隐藏
                case ConstantValue.VIEW_HOME_SEARCH:
                    displayBottom();
                    break;

            }
        }

    }

}

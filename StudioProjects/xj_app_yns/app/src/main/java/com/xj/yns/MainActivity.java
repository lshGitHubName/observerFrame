package com.xj.yns;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.xj.yns.util.PromptManager;
import com.xj.yns.util.SystemStatusManager;
import com.xj.yns.view.HomeUI;
import com.xj.yns.view.manager.BottomManager;
import com.xj.yns.view.manager.MiddleManager;
import com.xj.yns.view.manager.TitleManager;

@SuppressLint("InlinedApi")
public class MainActivity extends Activity {
	private LinearLayout dbMiddle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
    		    .detectDiskReads()  
    		    .detectDiskWrites()  
    		    .detectNetwork()  
    		    .penaltyLog()  
    		    .build());
		super.onCreate(savedInstanceState);
		setTranslucentStatus();
		setContentView(R.layout.yns_main);
		//获取屏幕宽度
		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		GlobalParams.WIN_WIDTH = outMetrics.widthPixels;
		init();

	}
	
	 /**
     * 标题栏一致化
     */
    private void setTranslucentStatus() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                    SystemStatusManager tintManager = new SystemStatusManager(this);
        tintManager.setStatusBarTintEnabled(true);
                    //设置颜色
                    tintManager.setStatusBarTintResource(R.color.color_green);
        getWindow().getDecorView().setFitsSystemWindows(true);
    }
}
	
	private void init() {
		TitleManager.getInstance().init(this);
		TitleManager.getInstance().showUnSignTitle();

		BottomManager.getInstance().init(this);
		BottomManager.getInstance().showCommonBottom(ConstantValue.VIEW_HOME);

		dbMiddle = (LinearLayout) findViewById(R.id.id_ir_middle);
		MiddleManager.getInstance().setDbMiddle(dbMiddle);
		
		//建立中间容器与标题容器（title）和底部导航（tottom）的关联关系
		//中间容器为被观察者，title和bottom为观察者
		MiddleManager.getInstance().addObserver(TitleManager.getInstance());
		MiddleManager.getInstance().addObserver(BottomManager.getInstance());
		
		
		//MiddleManager.getInstance().changeUI(FirstUI.class);
		MiddleManager.getInstance().changeUI(HomeUI.class,null);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			boolean result = MiddleManager.getInstance().goBack();
			//按下回退键，弹出系统弹出框
			if(!result){
				PromptManager.showExitSystem(this);
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

}

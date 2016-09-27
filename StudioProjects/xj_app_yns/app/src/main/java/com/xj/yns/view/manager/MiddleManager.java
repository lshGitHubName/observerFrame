package com.xj.yns.view.manager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

/**
 * 中间容器的管理工具
 * 
 * @author Administrator
 *
 */
public class MiddleManager extends Observable{
	private static final String TAG = "MiddleManager";
	private static MiddleManager instance = new MiddleManager();

	private MiddleManager() {
	}

	public static MiddleManager getInstance() {
		if (instance == null) {
			instance = new MiddleManager();
		}
		return instance;
	}

	private LinearLayout dbMiddle;

	public void setDbMiddle(LinearLayout dbMiddle) {
		this.dbMiddle = dbMiddle;
	}

	// 利用手机内存空间，换应用应用的运行速度（有OOM问题）
	private Map<String, BaseUI> VIEWCACHE = new HashMap<String, BaseUI>();

	// 唯一的标识BaseUI的子类
	private BaseUI currentUI;

	// 用户操作的历史记录
	private LinkedList<String> HISTORY = new LinkedList<String>();

	/**
	 * 切换界面:解决问题“三个容器的联动”
	 * 
	 * @param ui
	 */
	/*public void changeUI(Class<? extends BaseUI> targetClazz) {
		// 判断：当前正在展示的界面和切换目标界面是否相同
		if (currentUI != null && currentUI.getClass() == targetClazz) {
			return;
		}

		BaseUI targetUI = null;
		// 一旦创建过，重用
		// 判断是否创建了——曾经创建过的界面需要存储
		String key = targetClazz.getSimpleName();
		if (VIEWCACHE.containsKey(key)) {
			// 创建了，重用
			targetUI = VIEWCACHE.get(key);
		} else {
			// 否则，创建
			try {
				Constructor<? extends BaseUI> constructor = targetClazz.getConstructor(Context.class);
				targetUI = constructor.newInstance(getContext());
				VIEWCACHE.put(key, targetUI);
			} catch (Exception e) {
				throw new RuntimeException("constructor new instance error!");
			}
		}

		Log.i(TAG, targetUI.toString());
		
		if(currentUI!=null){
			//在清理掉当前正在展示的界面之前-onPause方法（清理耗时的方法和操作）
			currentUI.onPause();
		}
		
		dbMiddle.removeAllViews();
		View child = targetUI.getChild();
		dbMiddle.addView(child);
		
		//在加载完界面之后--onResume（开启耗时的方法和操作，访问网络服务器获取数据）
		targetUI.onResume();
		
		currentUI = targetUI;
		//当前访问的页面记录进历史列表中
		HISTORY.addFirst(key);
		// 当中间容器切换成功时，通知另外的两个容器相应的变化
		changeTitleAndBottom();
	}*/
	
	/**
	 * 如果需要传递数据给目标界面
	 * 
	 * @param targetClazz
	 */
	public void changeUI(Class<? extends BaseUI> targetClazz,Bundle bundle) {
		// 判断：当前正在展示的界面和切换目标界面是否相同
		if (currentUI != null && currentUI.getClass() == targetClazz) {
			return;
		}

		BaseUI targetUI = null;
		// 一旦创建过，重用
		// 判断是否创建了——曾经创建过的界面需要存储
		String key = targetClazz.getSimpleName();
		if (VIEWCACHE.containsKey(key)) {
			// 创建了，重用
			targetUI = VIEWCACHE.get(key);
		} else {
			// 否则，创建
			try {
				Constructor<? extends BaseUI> constructor = targetClazz.getConstructor(Context.class, Bundle.class);
				targetUI = constructor.newInstance(getContext(),bundle);
				if(!key.equals("HomeDetailsUI")&&!key.equals("WeatherDetailUI")){
					VIEWCACHE.put(key, targetUI);
				}
				
			} catch (Exception e) {
				throw new RuntimeException("constructor new instance error!");
			}
		}
		
		if(targetUI!=null){
			targetUI.setBundle(bundle);
		}
		
		Log.i(TAG, targetUI.toString());
		
		if(currentUI!=null){
			//在清理掉当前正在展示的界面之前-onPause方法（清理耗时的方法和操作）
			currentUI.onPause();
		}
		
		dbMiddle.removeAllViews();
		View child = targetUI.getChild();
		dbMiddle.addView(child);
		
		//在加载完界面之后--onResume（开启耗时的方法和操作，访问网络服务器获取数据）
		targetUI.onResume();
		
		currentUI = targetUI;
		
		// 如果当前访问的页面在栈中已经存在了，那么删除后，在把key存入栈顶
		if (HISTORY.contains(key)) {
			HISTORY.remove(key);
		}
		//当前访问的页面记录进历史列表中
		HISTORY.addFirst(key);
		// 当中间容器切换成功时，通知另外的两个容器相应的变化
		changeTitleAndBottom();
	}

	//切换界面:解决问题“三个容器的联动”
	private void changeTitleAndBottom() {
		//通知观察者title和bottom各自去更新自己对应的UI界面
		setChanged();
		notifyObservers(currentUI.getID());
	}

	public Context getContext() {
		return dbMiddle.getContext();
	}

	/**
	 *返回键处理
	 * 
	 * @return
	 */
	public boolean goBack() {
		if (HISTORY.size() > 0) {
			if (HISTORY.size() == 1) {
				return false;
			}
			HISTORY.removeFirst();
			if (HISTORY.size() > 0) {
				String key = HISTORY.getFirst();
				BaseUI targetUI = null;
				if (VIEWCACHE.containsKey(key)) {
					// 创建了，重用
					targetUI = VIEWCACHE.get(key);
					dbMiddle.removeAllViews();
					dbMiddle.addView(targetUI.getChild());
					currentUI = targetUI;
					//通知观察者title和bottom各自去更新自己对应的UI界面
					changeTitleAndBottom();
				}
				return true;
			}
		}
		return false;
	}
}

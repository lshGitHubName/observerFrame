package com.xj.yns.view.manager;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xj.yns.R;
import com.xj.yns.net.NetUtil;
import com.xj.yns.util.PromptManager;
import com.xj.yns.view.CompanyLibsUI;
import com.xj.yns.view.MoreUI;

/**
 * 所有UI的基类
 * @author Administrator
 *
 */
public abstract class BaseUI /*implements OnClickListener*/{
	protected Context context;
	protected Bundle bundle;
	
	//中间容器显示的内容
	protected ViewGroup showInMiddle;

	public View.OnClickListener clickListener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.msg_market:
					//信息集市跳转
//					MiddleManager.getInstance().changeUI(MessageMarketUI.class,null);
					PromptManager.showProgressDialog(context);
					break;
				case R.id.sn_appearance:
					//三农风光跳转
//					MiddleManager.getInstance().changeUI(SnAppearanceUI.class,null);

					break;
				case R.id.companylibs:
					//企业库跳转
					MiddleManager.getInstance().changeUI(CompanyLibsUI.class,null);
					break;
				case R.id.weather:
					//企业库跳转
					MiddleManager.getInstance().changeUI(CompanyLibsUI.class,null);
					break;
				//更多被点击
				case R.id.home_more:
					MiddleManager.getInstance().changeUI(MoreUI.class,null);
					break;

			}
		}
	};



	public BaseUI(Context context, Bundle bundle) {
		this.context = context;
		if(bundle==null){
			bundle= new Bundle();
		}
		this.bundle = bundle;


		init();
		setListener();

	}
	

	public void setBundle(Bundle bundle){
		this.bundle = bundle;
	}
	
	/**
	 * 界面初始化
	 * @return
	 */
	public abstract void init();
	
	/**
	 * 设置页面控件的监听
	 * @return
	 */
	public abstract void setListener();
	
	
	/**
	 * 获取每个界面的标示——容器联动时的比对依据
	 */
	public abstract int getID();
	
	/**
	 * 
	 */
	public void onStart() {
		
	}
	
	/**
	 * 要进入的时候调用--（开启耗时的方法和操作）
	 */
	public void onResume() {
		
	}
	/**
	 * 要出去的时候调用--（清理耗时的方法和操作）
	 */
	public void onPause() {

	}
	/**
	 * 获取需要在中间容器加载的内容
	 * 
	 * @return
	 */
	public View getChild() {
		//当LayoutParams类型转换异常，向父容器看齐
		if(showInMiddle.getLayoutParams() == null){
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
			showInMiddle.setLayoutParams(params);
		}
		return showInMiddle;
	}
	
	/**
	 * 点击事件
	 */
	/*public void onClick(View v) {
		
	}*/
	
	public View findViewById(int id){
		return showInMiddle.findViewById(id);
	}
	
	/**
	 * 访问网络的方法
	 * @author Administrator
	 *
	 * @param <Params>
	 * Params:传输的参数,
	 * Progress:下载相关的，进度提示（Integer，float）,
	 * Result:服务器回复的数据封装
	 */
	protected abstract class MyHttpTask<Params> extends AsyncTask<Params,Void,String>{
		//类似与Thread.start方法 由于final修饰，无法Override，方法重命名，子类中可以省略掉网络判断这些代码
		@SuppressWarnings("unchecked")
		public final AsyncTask<Params,Void,String> executeProxy(Params...params){
			if(NetUtil.checkNet(context)) {
				return super.execute(params);
			} else {
				PromptManager.showNoNetWork(context); 
			}
			return null;
		}
	}




}

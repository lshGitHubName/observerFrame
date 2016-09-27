package com.xj.yns.viewplus;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xj.yns.ConstantValue;
import com.xj.yns.R;
import com.xj.yns.entity.Home.HomeBanner;

import java.util.ArrayList;
import java.util.List;


public class AutoRollLayout extends FrameLayout {
	
	  private ViewPager viewPager;
	private LinearLayout dotContainer;
//	private List<HomeBanner> itemList;
	private List<HomeBanner> itemList;
	private boolean autoRoll;
	  public AutoRollLayout(Context context) {
	        this(context, null);
	    }

	    public AutoRollLayout(Context context, AttributeSet attrs) {
	        this(context, attrs, 0);
	    }

	    public AutoRollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
	        super(context, attrs, defStyleAttr);
	        init();
	    }

		private void init() {
			View view = View.inflate(getContext(), R.layout.home_arl_layout, this);
			viewPager = (ViewPager) findViewById(R.id.arl_vp);
	       
			viewPager.setOnPageChangeListener(pageListener);
	        //viewpager设置触摸监听
	        viewPager.setOnTouchListener(vpTouchListener);
	        dotContainer = (LinearLayout) findViewById(R.id.arl_dot_container);
		}
		 public void setItems(List<HomeBanner> itemList) {
		        this.itemList = itemList;
		        viewPager.setAdapter(pagerAdapter);
		        //在dotcontain添加元素之前，清空所有内容
		        dotContainer.removeAllViews();
		        addDots();
		        pageListener.onPageSelected(0);
		        if(autoRoll){
		            handler.post(autoRollRunnable);
		        }else{
		            handler.removeCallbacks(autoRollRunnable);
		        }
		    }

		    boolean toRight=true;
		    static Handler handler=new Handler();
		    Runnable autoRollRunnable=new Runnable() {
		        @Override
		        public void run() {
		            handler.removeCallbacks(autoRollRunnable);
		            if(pagerAdapter.getCount()==1){
		                return;
		            }
		            if(!isTouchingVp){
		                showNextPage();
		            }
		            handler.postDelayed(this,1000);

		        }
		    };

		    private void showNextPage() {
		        int currentPosition = viewPager.getCurrentItem();
//		        轮播图从左向右轮播，再回到第一张
//		        if(currentPosition==itemList.size()-1){
//		        	 viewPager.setCurrentItem(0);
//		        	 return;
//		        }
//		        viewPager.setCurrentItem(currentPosition+1);
//		        轮播图从左向右轮播，再从右向左轮播
		        if(currentPosition==0){
		            toRight=true;
		        }
		        if(currentPosition==pagerAdapter.getCount()-1){
		            toRight=false;
		        }
		        int currentPosistion=toRight?(currentPosition+=1):(currentPosition-=1);
		        viewPager.setCurrentItem(currentPosistion);
		    }

		    private void addDots() {
		        if(itemList==null||itemList.isEmpty()){
		            return;
		        }
		        int pxFor10Dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
		        for (HomeBanner homeBanner : itemList) {
		            View dot = new View(getContext());
		            dot.setBackgroundResource(R.drawable.selector_vpad_spot);
		            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pxFor10Dp, pxFor10Dp);
		            params.rightMargin = pxFor10Dp;
		            dotContainer.addView(dot, params);
		        }
		    }

		    private List<ImageView> imageViewList = new ArrayList<>();
		    private PagerAdapter pagerAdapter = new PagerAdapter() {

				@Override
		        public int getCount() {
		            return itemList == null ? 0 : itemList.size();
		        }

		        @Override
		        public boolean isViewFromObject(View view, Object object) {
		            return view == object;
		        }

		        @Override
		        public Object instantiateItem(ViewGroup container, int position) {
		            if (imageViewList.isEmpty()) {
		                ImageView imageView = new ImageView(container.getContext());
		                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		                imageViewList.add(imageView);
		            }
		            ImageView imageView = imageViewList.remove(0);
					DisplayImageOptions options = new DisplayImageOptions.Builder()
							.showImageOnFail(R.drawable.isloading1)
							.cacheInMemory(true)
							.cacheOnDisk(true)
							.build();
					ImageLoader.getInstance().displayImage(ConstantValue.HOME_HOST +itemList.get(position).getPic(), imageView, options);
		            container.addView(imageView);
		            return imageView;
		        }

		        @Override
		        public void destroyItem(ViewGroup container, int position, Object object) {
		            container.removeView((View) object);
		            imageViewList.add((ImageView) object);
		        }
		    };
		 OnPageChangeListener pageListener=new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
			    if (itemList == null || itemList.isEmpty()) {
	                return;
	            }
	            for (int i = 0; i < pagerAdapter.getCount(); i++) {
	                dotContainer.getChildAt(i).setEnabled(i == arg0);
	            }
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		};
		
	    //对外提供设置自动播放
	    public void setAutoRoll(boolean autoRoll) {
	        this.autoRoll = autoRoll;
	        if(autoRoll){
	            handler.post(autoRollRunnable);
	        }else{
	            handler.removeCallbacks(autoRollRunnable);
	        }
	    }
		  //轮播图触摸监听
		  private boolean isTouchingVp=false;
		    private OnTouchListener vpTouchListener=new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					 switch (event.getAction()){
		                case MotionEvent.ACTION_DOWN:
		                    // 停下来
		                    isTouchingVp = true;
		                    break;
		                case MotionEvent.ACTION_UP:
		                    // 恢复滚动
		                    isTouchingVp = false;
		                    break;
		            }
					
					return false;
				}
		      
		    };
}

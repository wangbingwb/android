package com.wb.viewgroup;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by wangbing on 2016/4/18.
 */
public class WbRefreshListView extends LinearLayout {
    private FrameLayout mHeader;
    private FrameLayout mFooter;
    private WbListView mListView;
    private int mLastY;
    private Handler mHandler = new Handler();
    private int mRrefreshHeight = 50;
    /**
     * 从越界位置返回正常位置的周期
     */
    private int mDuring = 500;
    /**
     * 帧画面步值，越小画面越流畅，但应为系统配置性能各有差异，不是越低越好
     */
    private int mStep = 10;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mOverY < 0){
                ViewGroup.LayoutParams layoutParams = mHeader.getLayoutParams();
                layoutParams.height = Math.abs(mOverY) / 2;
                mHeader.setLayoutParams(layoutParams);
            }else if (mOverY > 0) {
                ViewGroup.LayoutParams layoutParams = mFooter.getLayoutParams();
                layoutParams.height = Math.abs(mOverY) / 2;
                mFooter.setLayoutParams(layoutParams);
            }else {
                ViewGroup.LayoutParams layoutParams = mHeader.getLayoutParams();
                layoutParams.height = 0;
                mHeader.setLayoutParams(layoutParams);
                layoutParams = mFooter.getLayoutParams();
                layoutParams.height = 0;
                mFooter.setLayoutParams(layoutParams);
            }
            onOverHeightChangeListener(mOverY/2);
        }
    };

    private PullOverHeightChangeListener mPullOverHeightChangeListener = new PullOverHeightChangeListener() {
        /**
         * 一个简单的实现监听器
         */
        TextView textView = new TextView(getContext());

        @Override
        public void onHeightChange(int currentHeight, ViewGroup rootView) {
            if (rootView.getChildCount() == 0 ){
                textView.setText("下拉刷新");
                textView.setGravity(Gravity.CENTER);
                rootView.addView(textView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50));
            }else if (currentHeight >= 50){
                textView.setText("松开刷新");
            }else {
                textView.setText("下拉刷新");
            }
        }
    };
    private PushOverHeightChangeListener mPushOverHeightChangeListener = null;

    public WbRefreshListView(Context context) {
        super(context);
    }

    public WbRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHeader = new FrameLayout(getContext());
        this.addView(mHeader, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
        mListView = new WbListView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.weight = 1f;
        this.addView(mListView, layoutParams);
        mFooter = new FrameLayout(getContext());
        this.addView(mFooter, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
        this.setOrientation(VERTICAL);
    }

    public void setAdapter(ListAdapter adapter) {
        mListView.setAdapter(adapter);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP){
            mOverScrollState = OVERSCROLL_STATE_NORMAL;
            onBound();
        }

        if (mOverScrollState != OVERSCROLL_STATE_NORMAL){
            mOverY +=  mLastY - (int)ev.getY();
            mLastY = (int) ev.getY();

            if ((mOverY > 0 && mOverScrollState == OVERSCROLL_STATE_PULL) || (mOverY < 0 && mOverScrollState == OVERSCROLL_STATE_PUSH)){
                mOverY=0;
                mOverScrollState = OVERSCROLL_STATE_NORMAL;
            }
            if ( mOverScrollState == OVERSCROLL_STATE_PULL){
                ViewGroup.LayoutParams layoutParams = mHeader.getLayoutParams();
                layoutParams.height = Math.abs(mOverY) / 2;
                mHeader.setLayoutParams(layoutParams);
            }else if ( mOverScrollState == OVERSCROLL_STATE_PUSH){
                ViewGroup.LayoutParams layoutParams = mFooter.getLayoutParams();
                layoutParams.height = Math.abs(mOverY) / 2;
                mFooter.setLayoutParams(layoutParams);
            }
            onOverHeightChangeListener(mOverY/2);
            return true;
        }
        mLastY = (int) ev.getY();
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 基本参数
     */
    private static int OVERSCROLL_STATE_NORMAL = 0;//正常
    private static int OVERSCROLL_STATE_PULL = -1;//拉
    private static int OVERSCROLL_STATE_PUSH = 1;//推
    private int mOverScrollState = OVERSCROLL_STATE_NORMAL;

    /**
     * 越界值 mOverY<0是pull mOverY>0是push
     */
    private int mOverY = 0;

    /**
     * 释放时，回弹方法
     */
    private void onBound(){
        new Thread(){
            @Override
            public void run() {
                DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(2);
                int temp = mOverY;
                float i = 0;
                while (i < mDuring && Math.abs(mOverY) > 3 && mOverScrollState == OVERSCROLL_STATE_NORMAL){
                    try {
                        Thread.sleep(mStep);
                        i+=mStep;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //如果到达刷新高度，则调用刷新方法并跳出回弹
                    if (Math.abs(mOverY) > 2*mRrefreshHeight &&  Math.abs(temp - (int)(temp * decelerateInterpolator.getInterpolation(i*1.0f/mDuring))) < 2*mRrefreshHeight){
                       if (mOverY < 0){
                           mOverY =  -2*mRrefreshHeight;
                       }else {
                           mOverY =  2*mRrefreshHeight;
                       }
                        onRefresh();
                        return;
                    }else {
                        mOverY = temp - (int)(temp * decelerateInterpolator.getInterpolation(i*1.0f/mDuring));
                    }
                    mHandler.post(runnable);
                }
                mOverY = 0;
                mHandler.post(runnable);
            }
        }.start();
    }

    /**
     * 内部ListView方便调用外部类
     */
    private class WbListView extends ListView{

        public WbListView(Context context) {
            super(context);
        }

        @Override
        public void invalidate() {
            if (mOverScrollState == OVERSCROLL_STATE_PUSH){
                this.setSelection(super.computeVerticalScrollRange()-super.computeVerticalScrollExtent());
            }else if (mOverScrollState == OVERSCROLL_STATE_PULL){
                this.setSelection(0);
            }
            super.invalidate();
        }

        @Override
        public void setOverScrollMode(int mode) {
            //去掉边缘发光效果
            super.setOverScrollMode(OVER_SCROLL_NEVER);
        }
        @Override
        protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
            if(isTouchEvent){
                if (deltaY < 0){
                    mOverScrollState = OVERSCROLL_STATE_PULL;
                }else {
                    mOverScrollState = OVERSCROLL_STATE_PUSH;
                }
            }
            return true;
        }
    }

    /**
     * 当有越界事件发生时调用该方法
     * @param mOverY mOverY<0是pull mOverY>0是push
     */
    public void onOverHeightChangeListener(int mOverY){
        if (mOverY < 0 && mPullOverHeightChangeListener != null){
            mPullOverHeightChangeListener.onHeightChange(Math.abs(mOverY),mHeader);
        }else if (mOverY > 0 && mPushOverHeightChangeListener != null) {
            mPushOverHeightChangeListener.onHeightChange(mOverY,mHeader);
        }else {
            //do nothing
        }
    }

    /**
     * 执行刷新
     */
    private void onRefresh(){
        if (mOverY < 0){
            mOverY = -2*mRrefreshHeight;
            new Thread(){
                @Override
                public void run() {
                    if (onPullRefreshListener()){
                        onBound();
                    }
                }
            }.start();
        }else {
            mOverY = 2*mRrefreshHeight;
            new Thread(){
                @Override
                public void run() {
                    if (onPushRefreshListener()){
                        onBound();
                    }
                }
            }.start();
        }
    }

    /**
     * 当高度缩短至mRrefreshHeight调用该方法，该方法可以执行耗时操作
     */
    public boolean onPullRefreshListener(){
        try {
            Log.e("-->", "onPullRefreshListener start");
            Thread.sleep(2000);
            Log.e("-->", "onPullRefreshListener end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 当高度缩短至mRrefreshHeight调用该方法，该方法可以执行耗时操作
     */
    public boolean onPushRefreshListener(){
        try {
            Log.e("-->", "onPushRefreshListener start");
            Thread.sleep(2000);
            Log.e("-->", "onPushRefreshListener end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * pull事件的listener接口
     */
    public interface PullOverHeightChangeListener{
        void onHeightChange(int currentHeight,ViewGroup rootView);
    }

    /**
     * push事件的listener接口
     */
    public interface PushOverHeightChangeListener{
        void onHeightChange(int currentHeight,ViewGroup rootView);
    }

    public PullOverHeightChangeListener getPullOverHeightChangeListener() {
        return mPullOverHeightChangeListener;
    }

    public void setPullOverHeightChangeListener(PullOverHeightChangeListener mPullOverHeightChangeListener) {
        this.mPullOverHeightChangeListener = mPullOverHeightChangeListener;
    }

    public PushOverHeightChangeListener getPushOverHeightChangeListener() {
        return mPushOverHeightChangeListener;
    }

    public void setPushOverHeightChangeListener(PushOverHeightChangeListener mPushOverHeightChangeListener) {
        this.mPushOverHeightChangeListener = mPushOverHeightChangeListener;
    }
}

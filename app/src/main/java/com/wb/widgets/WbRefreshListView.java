package com.wb.widgets;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by wangbing on 2016/4/18.
 */
public class WbRefreshListView extends LinearLayout {
    private LinearLayout mHeader;
    private LinearLayout mFooter;
    private WbListView mListView;
    private int mLastY;
    private Handler mHandler = new Handler();
    private int mDuring = 500;
    private int mStep = 10;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mOverY < 0){
                mHeader.setMinimumHeight(Math.abs(mOverY/2));
            }else if (mOverY > 0) {
                mFooter.setMinimumHeight(Math.abs(mOverY/2));
            }else {
                mHeader.setMinimumHeight(0);
                mFooter.setMinimumHeight(0);
            }
        }
    };

    public WbRefreshListView(Context context) {
        super(context);
    }

    public WbRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHeader = new LinearLayout(getContext());
        this.addView(mHeader, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mListView = new WbListView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.weight = 1f;
        this.addView(mListView, layoutParams);
        mFooter = new LinearLayout(getContext());
        this.addView(mFooter, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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
                mHeader.setMinimumHeight(Math.abs(mOverY) / 2);
            }else if ( mOverScrollState == OVERSCROLL_STATE_PUSH){
                mFooter.setMinimumHeight(Math.abs(mOverY) / 2);
            }
            return true;
        }
        mLastY = (int) ev.getY();
        return super.dispatchTouchEvent(ev);
    }

    private static int OVERSCROLL_STATE_NORMAL = 0;//正常
    private static int OVERSCROLL_STATE_PULL = -1;//拉
    private static int OVERSCROLL_STATE_PUSH = 1;//推
    private int mOverScrollState = OVERSCROLL_STATE_NORMAL;

    private int mOverY = 0;

    /**
     * 回弹归位
     *
     */
    private void onBound(){
        new Thread(){
            @Override
            public void run() {
                DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(2);

                int temp = mOverY;
                float i = 0;
                while (i < mDuring && Math.abs(mOverY) >= 5 && mOverScrollState == OVERSCROLL_STATE_NORMAL){
                    try {
                        Thread.sleep(mStep);
                        i+=mStep;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mOverY = temp - (int)(temp * decelerateInterpolator.getInterpolation(i*1.0f/mDuring));
                    mHandler.post(runnable);
                }
                mOverY = 0;
                mHandler.post(runnable);
            }
        }.start();
    }

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
        protected int computeVerticalScrollOffset() {

            return super.computeVerticalScrollOffset();
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
}

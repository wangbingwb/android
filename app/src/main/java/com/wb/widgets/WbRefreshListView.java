package com.wb.widgets;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.lang.reflect.Field;

/**
 * Created by wangbing on 2016/4/18.
 */
public class WbRefreshListView extends LinearLayout {
    private LinearLayout mHeader;
    private LinearLayout mFooter;
    private WbListView mListView;
    private int mLastY;

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
            mOverY +=  mLastY - ev.getY();
            mLastY = (int) ev.getY();

            if ((mOverY > 0 && mOverScrollState == OVERSCROLL_STATE_PULL) || (mOverY < 0 && mOverScrollState == OVERSCROLL_STATE_PUSH)){
                mOverY=0;
                mOverScrollState = OVERSCROLL_STATE_NORMAL;
                Class<AbsListView> superclass = (Class<AbsListView>) mListView.getClass().getSuperclass().getSuperclass();
                try {
                    Field mLastY = superclass.getDeclaredField("mLastY");
                    mLastY.setAccessible(true);
                    mLastY.setInt(mListView, (int) ev.getY());
                    mLastY.setAccessible(false);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if ( mOverScrollState == OVERSCROLL_STATE_PULL){
                mHeader.setMinimumHeight(Math.abs(mOverY)/2);
            }else if ( mOverScrollState == OVERSCROLL_STATE_PUSH){
                mFooter.setMinimumHeight(Math.abs(mOverY) / 2);
                mListView.invalidate();
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
     */
    private void onBound(){
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
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
        new Thread(){
            int during = 1000;
            int d = 10;

            @Override
            public void run() {
                int flag = 1;
                if (mOverY<0){
                    flag = -1;
                    mOverY = -mOverY;
                }
                int temp = mOverY;

                int i = 0;
                while (i < during && Math.abs(temp) >= 5 && mOverScrollState == OVERSCROLL_STATE_NORMAL){
                    try {
                        Thread.sleep(d);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i += d;
                    temp = (int) (Math.pow((double)i / during - 1, 2)*temp);
                    mOverY = flag*temp;
                    handler.sendEmptyMessage(1);
                }
                mOverY = 0;
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

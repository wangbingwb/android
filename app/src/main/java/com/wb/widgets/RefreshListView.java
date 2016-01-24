package com.wb.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;

import com.wb.util.CusMath;

/**
 * Created by YiSD on 2015/12/5.
 */
public class RefreshListView extends ListView {
    public static int OVERSCROLL_STATE_NORMAL = 0;
    public static int OVERSCROLL_STATE_PULL = -1;
    public static int OVERSCROLL_STATE_PUSH = 1;

    private boolean pullToNormal = false;
    private boolean pushToNormal = false;

    private int overScrollState = OVERSCROLL_STATE_NORMAL;
    private float lastY = -1;

    /**
     * 小于0-->pull,大于0-->push
     */
    private int overY = 0;

    public RefreshListView(Context context) {
        super(context);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setOverScrollMode(int mode) {
        super.setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_UP){
            onBound();
        }
        //如果没有越界，交给自己处理
        if (overScrollState == OVERSCROLL_STATE_NORMAL){
            if (pullToNormal){
                setSelection(0);
                pullToNormal = false;
            }
            if (pushToNormal){
                setSelection(getCount());
                pushToNormal = false;
            }
            return super.onTouchEvent(ev);
        }
        if (lastY == -1){
            lastY = ev.getY();
        }
        if (overScrollState == OVERSCROLL_STATE_PULL){
            float temp = ev.getY();
            if (temp >= lastY){
                lastY = temp;
                return super.onTouchEvent(ev);
            }else {
                overY += (lastY - temp);
                lastY = temp;
                if (overY > 0){
                    overY = 0;
                    overScrollState = OVERSCROLL_STATE_NORMAL;
                    pullToNormal = true;
                }
                invalidate();
            }
        }
        if (overScrollState == OVERSCROLL_STATE_PUSH){
            float temp = ev.getY();
            if (temp <= lastY){
                lastY = temp;
                return super.onTouchEvent(ev);
            }else {
                overY -= (temp - lastY);
                Log.e("overY",""+overY);
                lastY = temp;
                if (overY < 0){
                    overY = 0;
                    overScrollState = OVERSCROLL_STATE_NORMAL;
                    pushToNormal = true;
                }
                invalidate();
            }
        }
        return true;
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if(isTouchEvent){
            if (deltaY < 0){
                overScrollState = OVERSCROLL_STATE_PULL;
            }else {
                overScrollState = OVERSCROLL_STATE_PUSH;
            }
            overY += deltaY;
            invalidate();
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.translate(0,-overY/2);
        super.draw(canvas);
    }

    private void onBound(){
        final int during = 1000;
        final int d = 10;
        if (overY > 0){
            new Thread(){
                @Override
                public void run() {
                    int i = 0;
                    while (i < during && overY > 1){
                        try {
                            Thread.sleep(d);
                            i += d;
                            overY =CusMath.calculate(overY,during,i);
                            postInvalidate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        overY-=5;
                        postInvalidate();
                    }
                    overY = 0;
                    postInvalidate();
                }
            }.start();
        }else if (overY < 0){
            new Thread(){
                @Override
                public void run() {
                    int i = 0;
                    while (i < during && overY < -1){
                        try {
                            Thread.sleep(d);
                            i += d;
                            overY = -CusMath.calculate(-overY,during,i);
                            postInvalidate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    overY = 0;
                    postInvalidate();
                }
            }.start();
        }
    }
}

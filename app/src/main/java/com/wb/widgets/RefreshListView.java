package com.wb.widgets;

import android.content.Context;
import android.gesture.Gesture;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
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
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (overY < 0){
                    overScrollState = OVERSCROLL_STATE_PULL;
                }else if (overY > 0){
                    overScrollState = OVERSCROLL_STATE_PUSH;
                }
        }
        if (overScrollState != OVERSCROLL_STATE_NORMAL){
            switch (ev.getAction()){
                case MotionEvent.ACTION_UP:
                    overScrollState = OVERSCROLL_STATE_NORMAL;
                    onBound();
                    return true;
            }

            if (lastY < 0){
                lastY = ev.getY();
            }else {
                Log.e("lastY=",""+lastY);
                Log.e("ev=",""+ev.getY());
                if (Math.abs(ev.getY() - lastY) > 30){
                    lastY = ev.getY();
                    return true;
                }
                if (ev.getY() - lastY != 0){
                    if (overScrollState == OVERSCROLL_STATE_PULL){
                        overY -= (ev.getY() - lastY);
                        if (overY > 0){
                            overY = 0;
                            overScrollState = OVERSCROLL_STATE_NORMAL;
                        }
                    }else if(overScrollState == OVERSCROLL_STATE_PUSH){
                        overY -= (ev.getY() - lastY);
                        if (overY < 0){
                            overY = 0;
                            overScrollState = OVERSCROLL_STATE_NORMAL;
                        }
                    }
                }
                lastY = ev.getY();
                invalidate();
            }
            return true;
        }else {
            return super.onTouchEvent(ev);
        }
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
        if (overY != 0){
            new Thread(){
                @Override
                public void run() {
                    int flag = 1;
                    if (overY<0){
                        flag = -1;
                        overY = -overY;
                    }
                    int temp = overY;

                    int i = 0;
                    while (i < during && temp > 1 && overScrollState == OVERSCROLL_STATE_NORMAL){
                        try {
                            Thread.sleep(d);
                            i += d;
                            temp =CusMath.calculate(temp,during,i);
                            postInvalidate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        temp-=5;
                        overY = flag*temp;
                        postInvalidate();
                    }
                }
            }.start();
        }
    }
}

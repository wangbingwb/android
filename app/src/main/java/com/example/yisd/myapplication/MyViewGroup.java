package com.example.yisd.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wangbing on 2016/5/7.
 */
public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        TextView textView = new TextView(getContext());
        textView.setText("asdads");
        textView.setBackgroundColor(Color.RED);
        this.addView(textView);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        View childAt = getChildAt(0);
        childAt.layout(0, 0, 100, 0);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }
}

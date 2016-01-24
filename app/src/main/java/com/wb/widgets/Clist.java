package com.wb.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by YiSD on 2016/1/23.
 */
public class Clist extends ListView {
    public Clist(Context context){
        super(context);
    }

    public Clist(Context context, AttributeSet attrs){
        super(context, attrs);
        super.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent){
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, 400, isTouchEvent);
    }
}

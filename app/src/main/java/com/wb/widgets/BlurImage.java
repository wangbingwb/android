package com.wb.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.wb.util.FastBlur;

/**
 * Created by bingwang on 2016/6/7.
 */
public class BlurImage extends ImageView {
    public BlurImage(Context context) {
        super(context);
    }

    public BlurImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取当前显示的位图
        Bitmap bitMap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
        super.onDraw(new Canvas(bitMap));
        //模糊处理
        FastBlur.doBlur(bitMap,10,true);
        //绘图
        canvas.drawBitmap(bitMap, 0, 0, new Paint());
    }
}

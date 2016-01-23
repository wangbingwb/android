package com.wb.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by YiSD on 2015/11/22.
 */
public class Rotation extends View{
    private int width;
    private int height;
    private int length;
    private int firstFlag;
    private int color;
    private int backgroudColor;

    @Override
    public void setBackgroundColor(int color) {
        backgroudColor = color;
    }

    {
        firstFlag = 0;
        color = Color.WHITE;
        backgroudColor = Color.alpha(0);
    }

    /**
     * 转动一圈的毫秒
     */
    private int speed = 500;

    public Rotation(Context context) {
        super(context);
    }

    public Rotation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float scaledDensity = getContext().getResources().getDisplayMetrics().scaledDensity;
        if (MeasureSpec.EXACTLY == MeasureSpec.getMode(widthMeasureSpec)){
            width  = MeasureSpec.getSize(widthMeasureSpec);
        }else if (MeasureSpec.AT_MOST == MeasureSpec.getMode(widthMeasureSpec)){
            width  = (int) (40*scaledDensity);
        }
        if (MeasureSpec.EXACTLY == MeasureSpec.getMode(heightMeasureSpec)){
            height = MeasureSpec.getSize(heightMeasureSpec);
        }else if (MeasureSpec.AT_MOST == MeasureSpec.getMode(heightMeasureSpec)){
            height = (int) (40*scaledDensity);
        }
        setMeasuredDimension(width, height);

        if (width > height){
            length = height;
        }else {
            length = width;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(color);
        canvas.drawColor(backgroudColor);

        int centerX = width/2;
        int centerY = height/2;
        int blockWidth = length/20;
        int blockHeight = length/4;
        int radiou = blockWidth/2;
        RectF rect = new RectF(centerX - blockWidth / 2, centerY - 3 * blockHeight / 2, centerX + blockWidth / 2, centerY - blockHeight / 2);
        canvas.rotate(firstFlag,centerX,centerY);
        firstFlag += 30;
        if (firstFlag == 360) firstFlag = 0;
        canvas.drawRoundRect(rect, radiou, radiou, p);

        for (int i = 0; i < 11; i++) {
            canvas.rotate(30,centerX,centerY);
            p.setAlpha(i*(255/12));
            canvas.drawRoundRect(rect, radiou, radiou, p);
        }
        postInvalidateDelayed(speed/12);
    }
}

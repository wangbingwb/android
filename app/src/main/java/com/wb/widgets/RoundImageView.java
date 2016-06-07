package com.wb.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.yisd.myapplication.R;

/**
 * Created by bingwang on 2016/6/6.
 */
public class RoundImageView extends ImageView {
    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        //通过查看ImageView源码，可以知道最终图片会转成Drawable对象
        //获取ImageView源drawable，并转换为Bitmap对象
        Bitmap bitMap = ((BitmapDrawable) getDrawable()).getBitmap();

        //图片缩放至当前控件大小
        Bitmap srcBitmap = Bitmap.createScaledBitmap(bitMap, width, height, true);

        //创建需要的形状
        Bitmap shapeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //在中心画个默认颜色的圆，其他部分是透明
        Canvas shapeCanvas = new Canvas(shapeBitmap);
        shapeCanvas.drawCircle(width / 2, height / 2, width / 2, new Paint());

        //下面是比较重要的一步，new一个画笔，
        // 并设置相交模式为SRC_IN
        //什么意思
        //-->现在用一个形状去切原图片，那么相切之后的图片怎么显示了
        //-->SRC代表将要切得原图
        //-->DST代表当前主动去切的形状图
        //-->IN表示两者交集
        //-->SRC_IN 则表示两者交集,且在原图的部分
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        shapeCanvas.drawBitmap(srcBitmap, 0, 0, paint);

        canvas.drawBitmap(shapeBitmap, 0, 0, new Paint());
    }
}

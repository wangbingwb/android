package com.wb.component;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wb.widgets.Rotation;

/**
 * Created by YiSD on 2015/11/28.
 */
public abstract class SimpleLoader extends AbsLoader {

    public final int FLAG_STYLE_1 = -1;
    public final int FLAG_STYLE_2 = -2;

    private String message;
    private int flagStyle = -1;

    public SimpleLoader(Context context,String message) {
        super(context);
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置Content
        this.setContentView(getView(flagStyle));
    }

    public View getView(int flagTyle){
        switch (flagTyle){
            case -2:
                return null;
            case -1:
            default:
                //屏幕密度比例尺
                float scaledDensity = getContext().getResources().getDisplayMetrics().scaledDensity;

                LinearLayout LLayout = new LinearLayout(getContext());
                LLayout.setOrientation(LinearLayout.VERTICAL);
                int padding = (int) (10 * scaledDensity);
                LLayout.setPadding((int) (1.5 * padding), padding, (int) (1.5 * padding), padding);
                RoundRectShape roundRectShape = new RoundRectShape(
                        new float[]{padding,padding,padding,padding,padding,padding,padding,padding},
                        new RectF(0,0,0,0),
                        new float[]{0,0,0,0,0,0,0,0}
                );
                ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
                LLayout.setBackground(shapeDrawable);

                Rotation rotation = new Rotation(this.getContext());
                LLayout.addView(rotation);

                TextView TVmessage = new TextView(this.getContext());
                TVmessage.setText(message);
                TVmessage.setTextColor(Color.WHITE);
                TVmessage.setGravity(Gravity.CENTER);
                LLayout.addView(TVmessage);
                return  LLayout;
        }
    }
}

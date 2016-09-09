package com.wb.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.widget.Button;

public class WbButton extends Button {
    private int mColor;
    private float mScale;

    public WbButton(Context context) {
        super(context);
        init();
    }

    public WbButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mScale = getResources().getDisplayMetrics().density;
        ColorStateList textColors = this.getTextColors();
        mColor = textColors.getDefaultColor();

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.setEnterFadeDuration(200);
        stateListDrawable.setExitFadeDuration(200);
        int radius = (int) (5 * mScale);
        int width = (int) (1 * mScale);

        {
            RoundRectShape roundRectShape = new RoundRectShape(
                            new float[]{radius,radius,radius,radius,radius,radius,radius,radius},
                            new RectF(width,width,width,width),
                            new float[]{radius,radius,radius,radius,radius,radius,radius,radius}
                    );
            ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
            shapeDrawable.getPaint().setColor(mColor);

            stateListDrawable.addState(new int[]{-android.R.attr.state_pressed},shapeDrawable);
        }
        {
            RoundRectShape roundRectShape = new RoundRectShape(
                    new float[]{radius,radius,radius,radius,radius,radius,radius,radius},
                    null,
                    null
            );
            ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
            shapeDrawable.getPaint().setColor(mColor);
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed},shapeDrawable);
        }

        this.setBackground(stateListDrawable);

        int[][] satate = {
                {-android.R.attr.state_pressed}, {android.R.attr.state_pressed}
        };
        int[] colors = {mColor , Color.WHITE};
        ColorStateList colorStateList = new ColorStateList(satate, colors);

        this.setTextColor(colorStateList);
    }
}

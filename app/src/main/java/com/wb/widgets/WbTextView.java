package com.wb.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.yisd.myapplication.R;

/**
 * Created by bingwang on 2016/5/24.
 */
public class WbTextView extends TextView {
    public WbTextView(Context context) {
        super(context);
    }

    public WbTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);

        TranslateAnimation translateAnimation = new TranslateAnimation(0,50,0,50);
        this.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.push_left_in));
    }
}

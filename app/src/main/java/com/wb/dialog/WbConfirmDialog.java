package com.wb.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yisd.myapplication.R;

/**
 * Created by bingwang on 2016/5/17.
 */
public class WbConfirmDialog extends Dialog {
    LinearLayout mLinearLayout;

    public WbConfirmDialog(Context context) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.getWindow().setDimAmount(0.2f);
        this.getWindow().setWindowAnimations(R.style.slide_from_button);

        mLinearLayout = new LinearLayout(context);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.width = context.getResources().getDisplayMetrics().widthPixels;
        this.setContentView(mLinearLayout, lp);

        TextView textView = new TextView(context);
        textView.setText("asdasdasdasd");
        textView.setBackgroundColor(Color.BLUE);
        mLinearLayout.addView(textView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void show() {
        super.show();
//        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom);
//        mLinearLayout.startAnimation(animation);
    }

    public void addView(View view){
        mLinearLayout.addView(view);
    }
}

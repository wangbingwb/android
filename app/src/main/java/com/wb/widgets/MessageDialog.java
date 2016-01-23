package com.wb.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yisd.myapplication.R;

/**
 * Created by YiSD on 2015/12/31.
 */
public class MessageDialog extends CustomDialog {
    String message;
    private MessageDialog(Context context) {
        super(context);
    }

    public MessageDialog(Context context, String title, String message) {
        super(context);
    }

    public MessageDialog(Context context, String message) {
        super(context);
        this.message = message;
        show();
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setWindowAnimations(R.style.slide_from_button);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.message, null);
        super.setContentView(inflate, layoutParams, 1, 0.5);
//        super.setDimAmount(0.5f);
        super.setGravity(Gravity.CENTER);
    }
}

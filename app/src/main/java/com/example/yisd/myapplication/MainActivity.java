package com.example.yisd.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.wb.activity.BaseActivity;
import com.wb.widgets.WbTextView;

public class MainActivity extends BaseActivity {

    @Bind(R.id.btn)
    private Button btn;

    @Bind(R.id.text)
    private WbTextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text.setText("12345");
//
//            }
//        });
    }
}
package com.example.yisd.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wb.activity.BaseActivity;

/**
 * Created by wangbing on 2016/4/6.
 */
public class NextActivity extends BaseActivity {

    @Bind(R.id.btn)
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

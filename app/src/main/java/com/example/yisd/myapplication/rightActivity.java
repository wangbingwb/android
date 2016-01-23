package com.example.yisd.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wb.activity.BaseActivity;

/**
 * Created by YiSD on 2016/1/23.
 */
public class rightActivity extends BaseActivity {
    @Bind(R.id.go_left)
    private Button goLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.right);

        final ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setCustomView(R.layout.tit);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            final Button back = (Button) actionBar.getCustomView().findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        goLeft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
//                startActivity(new Intent(rightActivity.this, MainActivity.class));
            }
        });
    }
}

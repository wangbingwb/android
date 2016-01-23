package com.example.yisd.myapplication;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.wb.activity.BaseActivity;
import com.wb.widgets.MessageDialog;
import com.wb.widgets.RefreshListView;

public class MainActivity extends BaseActivity {

    @Bind(R.id.btn)
    private Button btn;

    @Bind(R.id.title)
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setCustomView(R.layout.tit);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            final TextView tit = (TextView) actionBar.getCustomView().findViewById(R.id.title);
            tit.setText("主页");
            final Button back = (Button) actionBar.getCustomView().findViewById(R.id.back);
            back.setText("主页");
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new MessageDialog(MainActivity.this, "next");
                }
            });
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MessageDialog(MainActivity.this, "next");
                startActivity(new Intent(MainActivity.this, rightActivity.class));
            }
        });
    }
}

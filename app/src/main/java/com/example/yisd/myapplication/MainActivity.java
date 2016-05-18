package com.example.yisd.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.wb.activity.BaseActivity;
import com.wb.dialog.WbConfirmDialog;

public class MainActivity extends BaseActivity {


    @Bind(R.id.dia)
    private Button dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WbConfirmDialog wbConfirmDialog = new WbConfirmDialog(MainActivity.this);

                wbConfirmDialog.show();
            }
        });

    }
}

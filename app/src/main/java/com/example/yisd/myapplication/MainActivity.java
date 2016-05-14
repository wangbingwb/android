package com.example.yisd.myapplication;

import android.content.Intent;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.wb.activity.BaseActivity;
import com.wb.widgets.BoundListView;
import com.wb.widgets.WbRefreshListView;

public class MainActivity extends BaseActivity {

    @Bind(R.id.list)
    private WbRefreshListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{
                "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"}));
    }
}

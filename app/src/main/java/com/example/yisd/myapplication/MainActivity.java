package com.example.yisd.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.wb.activity.BaseActivity;
import com.wb.viewgroup.WbRefreshListView;
import com.wb.widgets.LoadingView;

public class MainActivity extends BaseActivity {


    @Bind(R.id.list)
    private WbRefreshListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1"
        }));
    }
}

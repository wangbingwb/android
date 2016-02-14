package com.example.yisd.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.wb.activity.BaseActivity;
import com.wb.widgets.MessageDialog;
import com.wb.widgets.BoundListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Bind(R.id.btn)
    private Button btn;

    @Bind(R.id.title)
    private TextView title;

    @Bind(R.id.list)
    private BoundListView list;

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

        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        data.add("6");
        data.add("7");
        data.add("8");
        data.add("9");
        data.add("10");
        data.add("11");
        data.add("12");
        data.add("13");
        data.add("14");
        data.add("15");
        data.add("16");
        data.add("17");
        data.add("18");
        data.add("19");
        data.add("20");
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data);
        list.setAdapter(stringArrayAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MessageDialog(MainActivity.this, "next");
                startActivity(new Intent(MainActivity.this, rightActivity.class));
            }
        });
    }
}

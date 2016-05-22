package com.example.yisd.myapplication;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.orhanobut.logger.Logger;
import com.wb.activity.BaseActivity;
import com.wb.viewgroup.WbViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    @Bind(R.id.pager)
    private WbViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<View> list = new ArrayList();

        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.zh_1);
            list.add(imageView);
        }

        pager.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(list.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(list.get(position));
                return list.get(position);
            }
        });

    }
}
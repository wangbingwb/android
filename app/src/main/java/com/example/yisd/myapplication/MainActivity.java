package com.example.yisd.myapplication;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.wb.activity.BaseActivity;
import com.wb.viewgroup.WbViewPager;

public class MainActivity extends BaseActivity {


    @Bind(R.id.pager)
    private WbViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        pager.setAdapter(new PagerAdapter() {
            int[] ids = {R.drawable.zh_1,R.drawable.zh_1};

            @Override
            public int getCount() {
                return ids.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeViewAt(position);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(ids[position]);
                container.addView(imageView);
                return imageView;
            }
        });
    }
}

package com.wb.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.TextView;

import com.example.yisd.myapplication.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class BaseActivity extends Activity {

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        inject();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        inject();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }

    public void setTitle(String left,String title,String right){
        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(0, 0, 0, 0)));
            actionBar.setCustomView(R.layout.tit);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            TextView txtTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
            txtTitle.setText(title);
            TextView txtLeft = (TextView) actionBar.getCustomView().findViewById(R.id.left);
            TextView txtRight = (TextView) actionBar.getCustomView().findViewById(R.id.right);
            txtLeft.setText(left);
            txtRight.setText(right);

            View customView = actionBar.getCustomView();
            AnimationSet set = new AnimationSet(true);
            AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
            alphaAnimation.setDuration(2000);
            set.addAnimation(alphaAnimation);
            customView.startAnimation(set);
            actionBar.show();
        }
    }

    /**
     * 注入绑定注解
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    protected @interface Bind{
        int value() default -1;
    }

    /**
     * 自动注入解析
     */
    private void inject(){
        Class Class = this.getClass();
        Field[] fields = Class.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Bind.class)){
                Bind bind = f.getAnnotation(Bind.class);
                int id = bind.value();
                if (id > 0){
                    f.setAccessible(true);
                    try {
                        f.set(this, this.findViewById(id));
                    } catch (IllegalAccessException e) {
                        Log.e("Bind",f.getName()+"注入失败!");
                        e.printStackTrace();
                    }finally {
                        f.setAccessible(false);
                    }
                }
            }
        }
    }
}

package com.wb.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class BaseActivity extends Activity {

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        Inject();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Inject();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    private void Inject(){
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

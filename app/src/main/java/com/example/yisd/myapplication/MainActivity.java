package com.example.yisd.myapplication;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import com.wb.activity.BaseActivity;


public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void re(View view){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.Test");
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
        sendBroadcast(intent);
    }
}
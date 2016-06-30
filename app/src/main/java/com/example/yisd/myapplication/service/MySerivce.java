package com.example.yisd.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by YiSD on 2016/2/20.
 */
public class MySerivce extends Service{
    private int count = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate() {

//        getWindow().setCallback(new Window.Callback() {
//            @Override
//            public boolean dispatchKeyEvent(KeyEvent event) {
//                return false;
//            }
//
//            @Override
//            public boolean dispatchKeyShortcutEvent(KeyEvent event) {
//                return false;
//            }
//
//            @Override
//            public boolean dispatchTouchEvent(MotionEvent event) {
//
//                System.out.println("------------------------------");
//                return false;
//            }
//
//            @Override
//            public boolean dispatchTrackballEvent(MotionEvent event) {
//                return false;
//            }
//
//            @Override
//            public boolean dispatchGenericMotionEvent(MotionEvent event) {
//                return false;
//            }
//
//            @Override
//            public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
//                return false;
//            }
//
//            @Nullable
//            @Override
//            public View onCreatePanelView(int featureId) {
//                return null;
//            }
//
//            @Override
//            public boolean onCreatePanelMenu(int featureId, Menu menu) {
//                return false;
//            }
//
//            @Override
//            public boolean onPreparePanel(int featureId, View view, Menu menu) {
//                return false;
//            }
//
//            @Override
//            public boolean onMenuOpened(int featureId, Menu menu) {
//                return false;
//            }
//
//            @Override
//            public boolean onMenuItemSelected(int featureId, MenuItem item) {
//                return false;
//            }
//
//            @Override
//            public void onWindowAttributesChanged(WindowManager.LayoutParams attrs) {
//
//            }
//
//            @Override
//            public void onContentChanged() {
//
//            }
//
//            @Override
//            public void onWindowFocusChanged(boolean hasFocus) {
//
//            }
//
//            @Override
//            public void onAttachedToWindow() {
//
//            }
//
//            @Override
//            public void onDetachedFromWindow() {
//
//            }
//
//            @Override
//            public void onPanelClosed(int featureId, Menu menu) {
//
//            }
//
//            @Override
//            public boolean onSearchRequested() {
//                return false;
//            }
//
//            @Nullable
//            @Override
//            public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
//                return null;
//            }
//
//            @Override
//            public void onActionModeStarted(ActionMode mode) {
//
//            }
//
//            @Override
//            public void onActionModeFinished(ActionMode mode) {
//
//            }
//        });
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e("e","onDestroy");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("e","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public class MyBinder extends Binder{
        public int getCount(){
            return count;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("e","onUnbind");
        return super.onUnbind(intent);
    }
}

package com.example.yisd.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

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
        Log.e("e","onCreate");
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                        count ++;
                        Log.e("e",""+count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
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

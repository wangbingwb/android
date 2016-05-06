package com.example.yisd.myapplication.test;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yisd.myapplication.R;
import com.example.yisd.myapplication.service.MySerivce;
import com.wb.activity.BaseActivity;

/**
 * Created by YiSD on 2016/2/20.
 */
public class TestServiceActivity extends BaseActivity {

    @Bind(R.id.startService)
    private Button startService;

    @Bind(R.id.stopService)
    private Button stopService;

    @Bind(R.id.getCount)
    private Button getCount;

    private MySerivce.MyBinder myBinder;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("e", "onServiceConnected");
            //这边service对象便是Service中onBind()返回的IBinder
            myBinder = (MySerivce.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //发生异常中断时会被调用
            Log.e("e", "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_service_activity);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestServiceActivity.this, MySerivce.class);
                bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
            }
        });

        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //解绑
                unbindService(serviceConnection);
            }
        });

        getCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestServiceActivity.this, String.valueOf(myBinder.getCount()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

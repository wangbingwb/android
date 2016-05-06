package com.example.yisd.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import static android.widget.Toast.*;

/**
 * Created by YiSD on 2016/2/25.
 */
public class MyBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        String name = intent.getStringExtra("name");
        Log.e("/","-->"+name);
    }

}

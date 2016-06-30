package com.example.yisd.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Set;

import static android.widget.Toast.*;

/**
 * Created by YiSD on 2016/2/25.
 */
public class MyBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("-----------------------------" + intent.getAction());

        Set<String> categories = intent.getCategories();

        for (String s : categories) {
            System.out.println(s);
        }
    }

}

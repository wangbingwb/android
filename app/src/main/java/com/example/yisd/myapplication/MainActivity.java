package com.example.yisd.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import com.wb.activity.BaseActivity;

import java.io.File;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        File file = Environment.getExternalStorageDirectory();
//
//        if (file.exists()){
//            System.out.println("存在");
//        }else {
//            boolean mkdir = file.mkdir();
//            if (mkdir){
//                System.out.println("创建成功!");
//            }
//        }
//
//        String path = Environment.getExternalStorageDirectory() + "/test.db";

//        System.out.println(path);

//        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path, null);

//        db.execSQL("create table(id integer(20) primary key autoincrement,name varchar(20))");
//        db.close();
     }
}
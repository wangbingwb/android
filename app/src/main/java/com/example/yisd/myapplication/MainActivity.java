package com.example.yisd.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.wb.activity.BaseActivity;
import com.wb.widgets.WbTextView1;

public class MainActivity extends BaseActivity {

    @Bind(R.id.seekbar)
    private SeekBar seekbar;
    @Bind(R.id.txt)
    private WbTextView1 txt;

    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println(progress);
                txt.setR(progress);
                txt.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//
//        txt.setText(i + "");
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                i++;
//                txt.setText(i + "");
//            }
//        });
    }
}
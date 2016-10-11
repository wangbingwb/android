package com.example.yisd.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout view = (LinearLayout) findViewById(R.id.con);

        {
            EditText textView = new EditText(this);
            SpannableString spannableString = new SpannableString("文字加粗");
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), 2, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            textView.setText(spannableString);
            view.addView(textView);
        }
        {
            TextView textView = new TextView(this);
            SpannableString spannableString = new SpannableString("文字字体");
            spannableString.setSpan(new TypefaceSpan("serif"), 2, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            textView.setText(spannableString);
            view.addView(textView);
        }
        {
            TextView textView = new TextView(this);
            SpannableString spannableString = new SpannableString("文字斜体");
            spannableString.setSpan(new StyleSpan(Typeface.ITALIC), 2, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            textView.setText(spannableString);
            view.addView(textView);
        }
        {
            TextView textView = new TextView(this);
            SpannableString spannableString = new SpannableString("文字背景红色");
            spannableString.setSpan(new BackgroundColorSpan(Color.RED), 2, 6, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            textView.setText(spannableString);
            view.addView(textView);
        }
        {
            TextView textView = new TextView(this);
            SpannableString spannableString = new SpannableString("文字蓝色");
            spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 2, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            textView.setText(spannableString);
            view.addView(textView);
        }
        {
            TextView textView = new TextView(this);
            SpannableString spannableString = new SpannableString("文字带下划线");
            spannableString.setSpan(new UnderlineSpan(), 2, 6, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            textView.setText(spannableString);
            view.addView(textView);
        }
        {
            TextView textView = new TextView(this);
            SpannableString spannableString = new SpannableString("文字大小100px");
            spannableString.setSpan(new AbsoluteSizeSpan(100), 2, 9, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            textView.setText(spannableString);
            view.addView(textView);
        }
        {
            TextView textView = new TextView(this);
            SpannableString spannableString = new SpannableString("文字删除线");
            spannableString.setSpan(new StrikethroughSpan(), 2, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            textView.setText(spannableString);
            view.addView(textView);
        }
        {
            TextView textView = new TextView(this);
            SpannableString spannableString = new SpannableString("文字URL");
            spannableString.setSpan(new URLSpan("tel:4155551212"), 2, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            textView.setText(spannableString);
            view.addView(textView);
        }
        {
            TextView textView = new TextView(this);
            SpannableString spannableString = new SpannableString("这是下标");
            spannableString.setSpan(new SubscriptSpan(), 2, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            textView.setText(spannableString);
            view.addView(textView);
        }
        {
            TextView textView = new TextView(this);
            SpannableString spannableString = new SpannableString("这是上标");
            spannableString.setSpan(new SuperscriptSpan(), 2, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            textView.setText(spannableString);
            view.addView(textView);
        }
        {
            TextView textView = new TextView(this);
            SpannableString spannableString = new SpannableString("这是中间对齐");
            spannableString.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, 6, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            textView.setText(spannableString);
            view.addView(textView);
        }
        {
            TextView textView = new TextView(this);
            SpannableString spannableString = new SpannableString("前面多个点");
            spannableString.setSpan(new BulletSpan(10,Color.RED), 0, 0, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            textView.setText(spannableString);
            view.addView(textView);
        }
    }
}
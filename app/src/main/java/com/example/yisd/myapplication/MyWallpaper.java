package com.example.yisd.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;

/**
 * Created by wangbing on 2016/4/26.
 */
public class MyWallpaper extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new MyEngine();
    }

    class MyEngine extends Engine{

        private Handler mHandler = new Handler();
        private boolean mVisible;
        private Paint p = new Paint();
        private final Runnable runnable = new Runnable(){

            @Override
            public void run() {
                drawFrame();
            }
        };

        @Override
        public void onDestroy() {
            super.onDestroy();
            mHandler.removeCallbacks(runnable);
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            this.mVisible = visible;
            if (visible){
                drawFrame();
            }else {
                mHandler.removeCallbacks(runnable);
            }
            Log.i("info-->", "onVisibilityChanged");
        }


        private void drawFrame(){
            final SurfaceHolder holder = getSurfaceHolder();
            Canvas c = null;
            try {
                c = holder.lockCanvas();
                if (c != null){
                    c.drawColor(Color.WHITE);
                    c.drawText("Text", 0f, 20f, p);
                    c.drawText("Text", 20f, 0f, p);
                    c.drawText("Text", 20f, 20f, p);
                    c.drawText("Text",40f,40f,p);
                    c.drawText("Text",60f,60f,p);
                    c.drawText("Text",80f,80f,p);
                    c.drawText("Text",100f,100f,p);
                }

            }finally {
                if (c != null){
                    holder.unlockCanvasAndPost(c);
                }
            }

            mHandler.removeCallbacks(runnable);


            if (mVisible){
                mHandler.postDelayed(runnable, 100);
            }
        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);

            p.setColor(Color.RED);
            p.setAntiAlias(true);

            setTouchEventsEnabled(true);

            Log.i("info-->", "onCreate");
        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            Log.i("info-->", "onTouchEvent");
        }

        @Override
        public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
            super.onOffsetsChanged(xOffset, yOffset, xOffsetStep, yOffsetStep, xPixelOffset, yPixelOffset);
            if (mVisible){drawFrame();}
            Log.i("info-->", "onOffsetsChanged");
        }
    }
}

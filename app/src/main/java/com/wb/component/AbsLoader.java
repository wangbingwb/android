package com.wb.component;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.wb.dialog.CustomDialog;

/**
 * Created by YiSD on 2015/11/25.
 */
public abstract class AbsLoader extends CustomDialog {

    public AbsLoader(Context context) {
        super(context);
        //背景完全透明度
        this.getWindow().setDimAmount(0);
        //禁用手动取消
        this.setCanceledOnTouchOutside(false);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onComplete(msg);
            dismiss();
        }
    };


    /**
     * 执行加载
     * @param exec
     */
    public void onLoad(final Executor exec){
        show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendMessage(exec.exec());
            }
        }).start();
    }

    /**
     * 加载数据
     * @param msg
     */
    public abstract void onComplete(Message msg);

    public interface Executor {
        Message exec();
    }
}

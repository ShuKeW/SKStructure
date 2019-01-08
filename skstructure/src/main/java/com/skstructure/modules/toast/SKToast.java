package com.skstructure.modules.toast;

import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.skstructure.SKHelper;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/6 11:40
 * @类描述 一句话描述 你的类
 */

public class SKToast {
//    private Toast toast = null;


    public void showLongToast(final int msgId) {
        showLongToast(SKHelper.getSkApplication().getString(msgId));
    }

    public void showLongToast(final String msg) {
        showAsMainThread(msg, Toast.LENGTH_LONG);
    }

    public void showShortToast(final int msgId) {
        showShortToast(SKHelper.getSkApplication().getString(msgId));
    }

    public void showShortToast(final String msg) {
        showAsMainThread(msg, Toast.LENGTH_SHORT);
    }

    private void showAsMainThread(final String msg, final int duration) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            SKHelper.getSkMainExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    show(msg, duration);
                }
            });
        } else {
            show(msg, duration);
        }
    }

    private void show(String msg, int duration) {
//        if (toast == null) {
//            toast = Toast.makeText(SKHelper.getSkApplication(), msg, duration);
//        } else {
//            toast.setText(msg);
//            toast.setDuration(duration);
//        }
//        toast.show();
        Toast.makeText(SKHelper.getSkApplication(), msg, duration).show();
    }

    public void destory() {
//        if (toast != null) {
//            toast.cancel();
//            toast = null;
//        }
    }
}

package com.skstructure.modules.display;

import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.skstructure.R;
import com.skstructure.core.Impl;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/6 15:54
 * @类描述 一句话描述 你的类
 */
@Impl(SKDialogDisplay.class)
public interface SKIDialogDisplay extends SKIDisplsy {
    void dialogOKorCancel(@StringRes int title, @StringRes int msg, @StringRes int sOk, @StringRes int sNeutral, @StringRes int sCancel, boolean canceledOnTouchOutside, DialogInterface.OnClickListener clickListener);

    void dialogSingleChoice(@StringRes int title, String[] items, boolean canceledOnTouchOutside, DialogInterface.OnClickListener clickListener);

    void dialogCustom(@StringRes int title, @StringRes int msg, @StringRes int sOk, @StringRes int sNeutral, @StringRes int sCancel, View view, boolean canceledOnTouchOutside, DialogInterface.OnClickListener clickListener);

}

class SKDialogDisplay extends SKDisplay implements SKIDialogDisplay {

    /**
     * 值为0表示不要此项
     *
     * @param title
     * @param msg
     * @param sOk
     * @param sNeutral
     * @param sCancel
     * @param clickListener
     */
    @Override
    public void dialogOKorCancel(@StringRes int title, @StringRes int msg, @StringRes int sOk, @StringRes int sNeutral, @StringRes int sCancel, boolean canceledOnTouchOutside, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity(), R.style.SKDialogTheme);
        if (title != 0) {
            builder.setTitle(title);
        }
        if (msg != 0) {
            builder.setMessage(msg);
        }
        if (sOk != 0) {
            builder.setPositiveButton(sOk, clickListener);
        }
        if (sNeutral != 0) {
            builder.setNeutralButton(sNeutral, clickListener);

        }
        if (sCancel != 0) {
            builder.setNegativeButton(sCancel, clickListener);
        }
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.show();
    }

    @Override
    public void dialogSingleChoice(@StringRes int title, String[] items, boolean canceledOnTouchOutside, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity(), R.style.SKDialogTheme);
        if (title != 0) {
            builder.setTitle(title);
        }
        builder.setItems(items, clickListener);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.show();
    }

    @Override
    public void dialogCustom(@StringRes int title, @StringRes int msg, @StringRes int sOk, @StringRes int sNeutral, @StringRes int sCancel, View view, boolean canceledOnTouchOutside, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity(), R.style.SKDialogTheme);
        if (title != 0) {
            builder.setTitle(title);
        }
        if (msg != 0) {
            builder.setMessage(msg);
        }
        if (sOk != 0) {
            builder.setPositiveButton(sOk, clickListener);
        }
        if (sNeutral != 0) {
            builder.setNeutralButton(sNeutral, clickListener);

        }
        if (sCancel != 0) {
            builder.setNegativeButton(sCancel, clickListener);
        }
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.show();
    }

}

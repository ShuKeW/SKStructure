package com.skstructure.modules.display;

import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.view.View;
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

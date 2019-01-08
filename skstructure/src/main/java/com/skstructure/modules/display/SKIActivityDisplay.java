package com.skstructure.modules.display;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.skstructure.core.Impl;
import com.skstructure.view.SKActivity;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/8 14:34
 * @类描述 一句话描述 你的类
 */

@Impl(SKActivityDisplay.class)
public interface SKIActivityDisplay extends SKIDisplsy {
    void intent(Class<? extends SKActivity> activityClass);

    void intent(Class<? extends SKActivity> activityClass, Bundle args);

    void intent(Class<? extends SKActivity> activityClass, Bundle args, int flag);

    void intentForResult(Class<? extends SKActivity> activityClass, int requestCode);

    void intentForResult(Class<? extends SKActivity> activityClass, int requestCode, Bundle args);

    void intentFromFragment(Class<? extends SKActivity> activityClass, Fragment fragment);

    void intentFromFragment(Class<? extends SKActivity> activityClass, Fragment fragment, Bundle args);

    void intentForResultFromFragment(Class<? extends SKActivity> activityClass, Fragment fragment, int requestCode);

    void intentForResultFromFragment(Class<? extends SKActivity> activityClass, Fragment fragment, Bundle args, int requestCode);
}
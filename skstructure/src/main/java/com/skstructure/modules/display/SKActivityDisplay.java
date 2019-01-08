package com.skstructure.modules.display;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.skstructure.SKHelper;
import com.skstructure.core.Impl;
import com.skstructure.view.SKActivity;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/8 14:34
 * @类描述 一句话描述 你的类
 */

public class SKActivityDisplay extends SKDisplay implements SKIActivityDisplay {

    @Override
    public void intent(Class<? extends SKActivity> activityClass) {
        intent(activityClass, null, -1);
    }

    @Override
    public void intent(Class<? extends SKActivity> activityClass, Bundle args) {
        intent(activityClass, args, -1);
    }

    @Override
    public void intent(Class<? extends SKActivity> activityClass, Bundle args, int flag) {
        if (activity() == null) {
            return;
        }
        Intent intent = new Intent(SKHelper.getSkApplication(), activityClass);
        if (args != null) {
            intent.putExtras(args);
        }
        if (flag != -1) {
            intent.setFlags(flag);
        }
        activity().startActivity(intent);
    }

    @Override
    public void intentForResult(Class<? extends SKActivity> activityClass, int requestCode) {
        intentForResult(activityClass, requestCode, null);
    }

    @Override
    public void intentForResult(Class<? extends SKActivity> activityClass, int requestCode, Bundle args) {
        if (activity() == null) {
            return;
        }
        Intent intent = new Intent(SKHelper.getSkApplication(), activityClass);
        if (args != null) {
            intent.putExtras(args);
        }
        activity().startActivityForResult(intent, requestCode);
    }

    @Override
    public void intentFromFragment(Class<? extends SKActivity> activityClass, Fragment fragment) {
        intentFromFragment(activityClass, fragment, null);
    }

    @Override
    public void intentFromFragment(Class<? extends SKActivity> activityClass, Fragment fragment, Bundle args) {
        intentForResultFromFragment(activityClass, fragment, args, -1);
    }

    @Override
    public void intentForResultFromFragment(Class<? extends SKActivity> activityClass, Fragment fragment, int requestCode) {
        intentForResultFromFragment(activityClass, fragment, null, requestCode);
    }

    @Override
    public void intentForResultFromFragment(Class<? extends SKActivity> activityClass, Fragment fragment, Bundle args, int requestCode) {
        if (activity() == null) {
            return;
        }
        Intent intent = new Intent(SKHelper.getSkApplication(), activityClass);
        if (args != null) {
            intent.putExtras(args);
        }
        activity().startActivityFromFragment(fragment, intent, requestCode);
    }
}

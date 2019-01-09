package com.skstructure.demo.display;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.skstructure.core.Impl;
import com.skstructure.modules.display.SKDialogDisplay;
import com.skstructure.modules.display.SKIDialogDisplay;

/**
 * @author weishukai
 * @date 2019/1/9   2:37 PM
 * @describe
 */
@Impl(DialogDisplay.class)
public interface IDialogDisplay extends SKIDialogDisplay {
    /**
     * 显示或者取消loading
     */
    void dialogLoading();

    void dialogLoadingDismiss();
}

class DialogDisplay extends SKDialogDisplay implements IDialogDisplay {
    @Override
    public void dialogLoading() {
        FragmentManager fragmentManager = activity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoadingDFragment loadingDialogFragment = (LoadingDFragment) fragmentManager.findFragmentByTag(LoadingDFragment.class.getName());
        if (loadingDialogFragment == null) {
            loadingDialogFragment = LoadingDFragment.getInstance();
        } else {
            fragmentTransaction.remove(loadingDialogFragment);
        }
        loadingDialogFragment.showNow(fragmentManager, LoadingDFragment.class.getName());

    }

    @Override
    public void dialogLoadingDismiss() {
        FragmentManager fragmentManager = activity().getSupportFragmentManager();
        LoadingDFragment loadingDialogFragment = (LoadingDFragment) fragmentManager.findFragmentByTag(LoadingDFragment.class.getName());
        if (loadingDialogFragment != null) {
            loadingDialogFragment.dismissAllowingStateLoss();
        }
    }
}

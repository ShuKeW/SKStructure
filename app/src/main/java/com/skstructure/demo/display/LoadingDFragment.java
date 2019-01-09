package com.skstructure.demo.display;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.skstructure.demo.R;

/**
 * @author weishukai
 * @date 2019/1/9   11:59 AM
 * @describe
 */
public class LoadingDFragment extends DialogFragment {

    public static LoadingDFragment getInstance() {
        LoadingDFragment fragment = new LoadingDFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_progress, container, false);
        return view;
    }
}

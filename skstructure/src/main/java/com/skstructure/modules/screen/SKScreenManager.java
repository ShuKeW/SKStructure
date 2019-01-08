package com.skstructure.modules.screen;

import android.app.Activity;

import com.skstructure.view.SKActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/6 16:01
 * @类描述 activity的管理类
 */

public class SKScreenManager {

    private List<SKScreenHolder> activities;

    public SKScreenManager() {
        activities = new ArrayList<>();
    }

    public void onCreate(SKActivity activity) {
        activities.add(new SKScreenHolder(activity));
    }

    public SKActivity getCurrentIsResumeActivity() {
        if (activities.size() > 0) {
            for (int i = activities.size() - 1; i >= 0; i--) {
                if (activities.get(i).isResume()) {
                    return activities.get(i).getActivity();
                }
            }
        }
        return null;
    }

    public SKActivity getCurrentIsRunningActivity() {
        if (activities.size() > 0) {
            for (int i = activities.size() - 1; i >= 0; i--) {
                if (activities.get(i).isRunning()) {
                    return activities.get(i).getActivity();
                }
            }
        }
        return null;
    }

    public SKActivity getCurrentActivity() {
        if (activities.size() > 0) {
            return activities.get(activities.size() - 1).getActivity();
        }
        return null;
    }


    public void onStart(Activity activity) {
        for (int i = activities.size() - 1; i >= 0; i--) {
            if (activities.get(i).getActivity().equals(activity)) {
                activities.get(i).start();
                break;
            }
        }
    }

    public void onResume(Activity activity) {
        for (int i = activities.size() - 1; i >= 0; i--) {
            if (activities.get(i).getActivity().equals(activity)) {
                activities.get(i).resume();
                break;
            }
        }
    }


    public void onPause(Activity activity) {
        for (int i = activities.size() - 1; i >= 0; i--) {
            if (activities.get(i).getActivity().equals(activity)) {
                activities.get(i).pause();
                break;
            }
        }
    }

    public void onStop(Activity activity) {
        for (int i = activities.size() - 1; i >= 0; i--) {
            if (activities.get(i).getActivity().equals(activity)) {
                activities.get(i).stop();
                break;
            }
        }
    }

    public void onActivityResult(Activity activity) {
        for (int i = activities.size() - 1; i >= 0; i--) {
            if (activities.get(i).getActivity().equals(activity)) {
                activities.get(i).result();
                break;
            }
        }
    }

    public void onDestroy(Activity activity) {
        for (int i = activities.size() - 1; i >= 0; i--) {
            if (activities.get(i).getActivity().equals(activity)) {
                activities.remove(i);
                break;
            }
        }
    }
}

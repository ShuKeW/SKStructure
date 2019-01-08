package com.skstructure.demo;

import android.util.Log;
import com.skstructure.SKHelper;
import com.skstructure.core.Impl;
import com.skstructure.core.SKIPre;
import com.skstructure.core.SKPre;
import com.skstructure.modules.methodproxy.Background;
import com.skstructure.modules.methodproxy.BackgroundType;
import com.skstructure.modules.methodproxy.Interceptor;
import com.skstructure.modules.methodproxy.Repeat;

/**
 * @author weishukai
 * @date 2018/12/19   4:05 PM
 * @describe
 */
@Impl(JokeListPre.class)
public interface IJokeListPre extends SKIPre {
    @Background(BackgroundType.HTTP)
    void loadJokeList();

    @Background(BackgroundType.HTTP)
    @Repeat
    void forward(long jokeId);

    @Interceptor(500)
    void up(long jokeId);

    void down(long jokeId);
}

class JokeListPre extends SKPre<IJokeListActivity> implements IJokeListPre {

    @Override
    public void onCreate() {

    }

    @Override
    public void loadJokeList() {
        JokeData jokeDataResponse = http(ApiServices.class).loadJokeList(2, 1);
        if (jokeDataResponse != null && jokeDataResponse.getCode() == 200) {
            ui().showJokeList(jokeDataResponse.getData());
        } else {

        }
    }


    @Override
    public void forward(long jokeId) {
        //网络操作
        Log.i("tag","forward invoke start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("tag","forward invoke end");
    }

    @Override
    public void up(long jokeId) {
        //网络操作
        SKHelper.getSkToast().showLongToast("up++");
    }

    @Override
    public void down(long jokeId) {
        //网络操作
        SKHelper.getSkToast().showLongToast("down++");
    }
}

package com.skstructure.demo.display;

import android.os.Bundle;
import com.skstructure.core.Impl;
import com.skstructure.demo.JokeData;
import com.skstructure.demo.business.jokedetail.JokeDetailActivity;
import com.skstructure.modules.display.SKActivityDisplay;
import com.skstructure.modules.display.SKIActivityDisplay;
import com.skstructure.modules.methodproxy.Interceptor;

/**
 * @author weishukai
 * @date 2019/1/7   5:34 PM
 * @describe
 */

@Impl(DemoActivityDisplay.class)
public interface IDemoActivityDisplay extends SKIActivityDisplay {
    @Interceptor(1)
    void startJokeDetailActivity(JokeData.JokeBean dataBean);
}

class DemoActivityDisplay extends SKActivityDisplay implements IDemoActivityDisplay {
    @Override
    public void startJokeDetailActivity(JokeData.JokeBean dataBean) {
        Bundle args = new Bundle();
        args.putSerializable("jokeBean",dataBean);
        intent(JokeDetailActivity.class,args);
    }
}
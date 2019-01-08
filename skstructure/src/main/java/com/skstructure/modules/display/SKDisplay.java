package com.skstructure.modules.display;

import com.skstructure.SKHelper;
import com.skstructure.view.SKActivity;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/6 15:59
 * @类描述 一句话描述 你的类
 */

public class SKDisplay implements SKIDisplsy {
    @Override
    public SKActivity activity() {
        SKActivity activity = SKHelper.getSkScreenManager().getCurrentIsRunningActivity();
        return activity != null ? activity : SKHelper.getSkScreenManager().getCurrentActivity();
    }
}

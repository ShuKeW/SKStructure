package com.skstructure.modules.methodproxy;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @创建人 weishukai
 * @创建时间 2018/2/28 16:32
 * @类描述 一句话描述 你的类
 */

public class SKProxy {
    public Object impl;//实现类
    public Object proxy;//代理类
    public ConcurrentHashMap<String, SKMethod<?>> methodCache = new ConcurrentHashMap();
}

package com.skstructure.modules.retrofit2;

import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/26 16:37
 * @类描述 一句话描述 你的类
 */

public abstract class SKHttpUrl {
    protected Map<String, Param> pathParam;
    protected Map<String, Param> queryParam;

    public SKHttpUrl() {
        pathParam = new HashMap<>();
        queryParam = new HashMap<>();
        addHeader();
        addPathParam(pathParam);
        addQueryParam(queryParam);
    }

    public Map<String, Param> getPathParam() {
        return pathParam;
    }

    public Map<String, Param> getQueryParam() {
        return queryParam;
    }

    public abstract String baseUrl();

    public abstract String pathSegments();

    public abstract boolean pathSegmentsHead();

    public abstract void addHeader();

    public abstract void addPathParam(Map<String, Param> pathParam);

    public abstract void addQueryParam(Map<String, Param> queryParam);

    public static class Param<T> {
        public T value;
        public boolean encode;

        public Param(T value) {
            this(value, false);
        }

        public Param(T value, boolean encode) {
            this.value = value;
            this.encode = encode;
        }
    }
}


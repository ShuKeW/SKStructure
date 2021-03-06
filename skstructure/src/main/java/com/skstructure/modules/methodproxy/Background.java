package com.skstructure.modules.methodproxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @创建人 weishukai
 * @创建时间 2018/2/28 16:58
 * @类描述 一句话描述 你的类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Background {
    BackgroundType value() default BackgroundType.HTTP;
}

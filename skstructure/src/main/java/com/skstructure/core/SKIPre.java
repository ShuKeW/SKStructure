package com.skstructure.core;

import com.skstructure.modules.structure.SKStructureModel;

/**
 * @创建人 weishukai
 * @创建时间 2018/2/27 15:58
 * @类描述 一句话描述 你的类
 */

public interface SKIPre {
    void create(SKStructureModel skStructureModel);
    void destory();
}

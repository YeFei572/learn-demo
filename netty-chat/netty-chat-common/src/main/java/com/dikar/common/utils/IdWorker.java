package com.dikar.common.utils;

import cn.hutool.core.util.IdUtil;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.common.utils
 * @Author: yefei
 * @CreateTime: 2021-06-01 14:29
 * @Description: Id generator
 */
public class IdWorker {

    public static Long nextId() {
        return IdUtil.createSnowflake(1, 1).nextId();
    }
}

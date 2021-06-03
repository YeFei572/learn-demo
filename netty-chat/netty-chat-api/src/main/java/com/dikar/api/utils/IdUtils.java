package com.dikar.api.utils;

import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.utils
 * @Author: yefei
 * @CreateTime: 2021-06-03 16:37
 * @Description: id generator
 */
@Component
public class IdUtils {

    public Long nexId() {
        return IdUtil.getSnowflake(1, 1).nextId();
    }
}

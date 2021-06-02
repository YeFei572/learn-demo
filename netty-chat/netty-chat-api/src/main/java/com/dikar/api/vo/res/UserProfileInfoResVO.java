package com.dikar.api.vo.res;

import lombok.Data;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.vo.res
 * @Author: yefei
 * @CreateTime: 2021-06-01 16:23
 * @Description:
 */
@Data
public class UserProfileInfoResVO {

    /**
     * 好友请求的数量
     */
    private Integer friendAskCount;
    /**
     * 好友数量
     */
    private Integer friendCount;
}

package com.dikar.api.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.entity.user
 * @Author: yefei
 * @CreateTime: 2021-06-01 16:15
 * @Description:
 */
@Data
public class UserProfile {
    /**
     * 用户的消息配置表
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long uid;
    /**
     * 好友请求的数量
     */
    private Integer friendAskCount;
    /**
     * 好友数量
     */
    private Integer friendCount;
    private Date createTime;
    private Date modifiedTime;
}

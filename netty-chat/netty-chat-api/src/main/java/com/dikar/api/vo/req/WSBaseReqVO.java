package com.dikar.api.vo.req;

import lombok.Data;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.vo.req
 * @Author: yefei
 * @CreateTime: 2021-05-31 17:05
 * @Description:
 */
@Data
public class WSBaseReqVO {

    /**
     * 类型
     */
    private Integer type;

    /**
     * 消息实体
     */
    private WSMessageReqVO message;

    /**
     * 发送者用户信息
     */
    private WSUserReqVO user;
}

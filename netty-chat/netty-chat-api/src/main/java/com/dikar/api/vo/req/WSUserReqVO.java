package com.dikar.api.vo.req;

import lombok.Data;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.vo.req
 * @Author: yefei
 * @CreateTime: 2021-05-31 17:07
 * @Description:
 */
@Data
public class WSUserReqVO {
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 个性签名
     */
    private String remark;
}

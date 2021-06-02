package com.dikar.api.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.entity.user
 * @Author: yefei
 * @CreateTime: 2021-06-01 11:40
 * @Description:
 */
@Data
public class User {
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 密码
     */
    private String pwd;
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
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date modifiedTime;
}

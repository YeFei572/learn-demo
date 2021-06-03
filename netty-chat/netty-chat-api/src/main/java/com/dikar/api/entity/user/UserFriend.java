package com.dikar.api.entity.user;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.entity.user
 * @Author: yefei
 * @CreateTime: 2021-06-02 10:42
 * @Description:
 */
@Data
@Builder
public class UserFriend {
    /**
     * 自增id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 朋友的用户id
     */
    private Long friendUid;
    /**
     * 备注
     */
    private String remark;
    /**
     * 未读消息数量
     */
    private Integer unMsgCount;
    /**
     * 最后一次接收的消息内容
     */
    private String lastMsgContent;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新的时间
     */
    private Date modifiedTime;
}

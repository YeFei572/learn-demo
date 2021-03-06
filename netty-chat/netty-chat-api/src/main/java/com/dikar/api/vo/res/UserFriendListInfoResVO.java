package com.dikar.api.vo.res;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.vo.user
 * @Author: yefei
 * @CreateTime: 2021-06-02 11:25
 * @Description: 用户朋友列表信息
 */
@Data
public class UserFriendListInfoResVO {

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long uid;
    /**
     * 朋友的ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
     * 最后一次接收的消息
     */
    private String lastMsgContent;

    private Date modifiedTime;

    /**
     * 用户
     */
    private UserInfoListResVO user;
}

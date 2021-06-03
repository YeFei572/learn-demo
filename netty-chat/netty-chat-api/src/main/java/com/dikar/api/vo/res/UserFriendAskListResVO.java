package com.dikar.api.vo.res;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.vo.res
 * @Author: yefei
 * @CreateTime: 2021-06-03 10:58
 * @Description:
 */
@Data
public class UserFriendAskListResVO {

    /**
     * 自增ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long uid;
    /**
     * 朋友的用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long friendUid;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态（0：未确认过，1：已确认, 2: 已拒绝）
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

    // 用户信息
    private UserInfoListResVO user;
}

package com.dikar.api.vo.res;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.vo.res
 * @Author: yefei
 * @CreateTime: 2021-06-01 16:25
 * @Description:
 */
@Data
public class UserInfoResVO {

    /**
     * 用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
    /**
     * 创建时间
     */
    private Date createTime;

    private UserProfileInfoResVO profile;
}

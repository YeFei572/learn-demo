package com.dikar.api.vo.res;

import lombok.Data;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.vo.res
 * @Author: yefei
 * @CreateTime: 2021-06-02 11:18
 * @Description:
 */
@Data
public class UserInfoListResVO {

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

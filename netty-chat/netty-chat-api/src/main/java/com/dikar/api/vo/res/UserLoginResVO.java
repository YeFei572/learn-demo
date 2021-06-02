package com.dikar.api.vo.res;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.vo.res
 * @Author: yefei
 * @CreateTime: 2021-06-01 11:58
 * @Description:
 */
@Data
public class UserLoginResVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long uid;

    private String sid;
}

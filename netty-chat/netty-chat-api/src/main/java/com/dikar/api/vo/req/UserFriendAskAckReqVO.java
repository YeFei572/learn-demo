package com.dikar.api.vo.req;

import com.dikar.common.vo.req.BaseReqVO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.vo.req
 * @Author: yefei
 * @CreateTime: 2021-06-03 14:55
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserFriendAskAckReqVO extends BaseReqVO {
    /**
     * 朋友的ID
     */
    @NotNull(message = "id不能为空！")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 状态
     */
    private Integer status;
}

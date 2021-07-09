package com.dikar.api.vo.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.vo.req
 * @Author: yefei
 * @CreateTime: 2021-05-31 17:06
 * @Description:
 */
@Data
public class WSMessageReqVO {
    /**
     * 接收者ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long receiveId;

    /**
     * 消息类型
     */
    private Integer msgType;

    /**
     * 消息内容
     */
    private String msgContent;
}

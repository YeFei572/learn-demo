package com.dikar.common.vo.req;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BaseReqVO {

    /**
     * 唯一请求号
     */
    private String reqNo;
    /**
     * 请求时间戳
     */
    private Long timeStamp;

    public BaseReqVO() {
        this.reqNo = UUID.randomUUID().toString();
        this.timeStamp = System.currentTimeMillis();
    }
}

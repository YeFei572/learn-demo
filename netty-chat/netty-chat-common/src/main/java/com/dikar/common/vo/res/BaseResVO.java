package com.dikar.common.vo.res;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResVO<T> {
    private Integer code;
    private String message;
    private T data;
}

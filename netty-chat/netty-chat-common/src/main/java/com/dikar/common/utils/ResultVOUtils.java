package com.dikar.common.utils;

import com.dikar.common.enums.ResultEnum;
import com.dikar.common.vo.res.BaseResVO;

import java.util.HashMap;

/**
 * 返回结果集操作
 */
public class ResultVOUtils {

    /**
     * 成功时候返回
     *
     * @param data 数据
     * @return 封装返回包装累
     */
    public static BaseResVO success(Object data) {
        return BaseResVO.builder()
                .code(0)
                .message("Success")
                .data(data)
                .build();
    }

    public static BaseResVO success() {
        return BaseResVO.builder()
                .code(0)
                .message("Success")
                .build();
    }

    /**
     * 错误的时候返回
     *
     * @param code    错误码
     * @param message 错误信息
     * @return 封装返回包装累
     */
    public static BaseResVO error(Integer code, String message) {
        return BaseResVO.builder()
                .code(code)
                .message(message)
                .data(new HashMap<>())
                .build();
    }

    /**
     * 返回已有枚举错误结果集
     *
     * @param resultEnum
     * @return
     */
    public static BaseResVO error(ResultEnum resultEnum) {
        return error(resultEnum.getCode(), resultEnum.getMessage());
    }

    /**
     * 错误时候自定义返回信息
     * @param resultEnum
     * @param message
     * @return
     */
    public static BaseResVO error(ResultEnum resultEnum, String message) {
        return error(resultEnum.getCode(), message);
    }

    /**
     * 默认错误
     * @return
     */
    public static BaseResVO error() {
        return error(ResultEnum.NOT_NETWORK);
    }
}

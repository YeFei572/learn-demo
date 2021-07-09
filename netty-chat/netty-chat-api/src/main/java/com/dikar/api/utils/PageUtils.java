package com.dikar.api.utils;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.utils
 * @Author: yefei
 * @CreateTime: 2021-06-04 10:12
 * @Description:
 */
public class PageUtils {
    /**
     * 根据页数和每页显示条数返回 偏移量
     *
     * @param page
     * @param limit
     * @return
     */
    public static Integer createOffset(Integer page, Integer limit) {
        if (null == page || page <= 0) {
            page = 1;
        }
        if (null == limit || limit <= 0) {
            limit = 10;
        }
        return (page - 1) * limit;
    }

}

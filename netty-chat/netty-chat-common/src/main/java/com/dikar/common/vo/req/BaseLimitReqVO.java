package com.dikar.common.vo.req;

import java.util.Objects;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.common.vo.req
 * @Author: yefei
 * @CreateTime: 2021-05-28 15:57
 * @Description: 分页请求包装类
 */
public class BaseLimitReqVO extends BaseReqVO {
    private Integer page;
    private Integer limit;
    private Integer offset;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset() {
        if (Objects.isNull(this.getPage()) || this.getPage() < 1) {
            this.setPage(1);
        }
        if (Objects.isNull(this.getLimit()) || this.getLimit() < 1) {
            this.setLimit(10);
        }
        this.offset = (this.getPage() - 1) * this.getLimit();
    }
}

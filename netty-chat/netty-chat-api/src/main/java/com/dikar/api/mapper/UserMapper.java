package com.dikar.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dikar.api.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.mapper
 * @Author: yefei
 * @CreateTime: 2021-06-01 11:59
 * @Description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

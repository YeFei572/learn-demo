package com.dikar.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dikar.api.entity.user.UserFriendMsg;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.mapper
 * @Author: yefei
 * @CreateTime: 2021-06-03 15:36
 * @Description:
 */
@Mapper
public interface UserFriendMsgMapper extends BaseMapper<UserFriendMsg> {
}

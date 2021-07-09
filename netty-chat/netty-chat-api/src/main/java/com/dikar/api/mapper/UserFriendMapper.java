package com.dikar.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dikar.api.entity.user.UserFriend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.mapper
 * @Author: yefei
 * @CreateTime: 2021-06-02 10:43
 * @Description:
 */
@Mapper
public interface UserFriendMapper extends BaseMapper<UserFriend> {

    void insertUserFriendAll(List<UserFriend> userFriends);
}

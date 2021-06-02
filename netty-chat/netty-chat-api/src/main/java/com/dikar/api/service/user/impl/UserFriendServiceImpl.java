package com.dikar.api.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dikar.api.entity.user.UserFriend;
import com.dikar.api.mapper.UserFriendMapper;
import com.dikar.api.service.user.UserFriendService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.service.user.impl
 * @Author: yefei
 * @CreateTime: 2021-06-02 10:44
 * @Description:
 */
@Service
public class UserFriendServiceImpl extends ServiceImpl<UserFriendMapper, UserFriend> implements UserFriendService {
}

package com.dikar.api.service.user.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dikar.api.entity.user.UserFriend;
import com.dikar.api.mapper.UserFriendMapper;
import com.dikar.api.service.user.UserFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.service.user.impl
 * @Author: yefei
 * @CreateTime: 2021-06-02 10:44
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class UserFriendServiceImpl extends ServiceImpl<UserFriendMapper, UserFriend> implements UserFriendService {


    @Override
    public UserFriend findByUidAndFriendUid(Long uid, Long friendUid) {
        return baseMapper.selectOne(Wrappers.<UserFriend>lambdaQuery()
                .eq(UserFriend::getUid, uid)
                .eq(UserFriend::getFriendUid, friendUid));
    }
}

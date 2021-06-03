package com.dikar.api.service.user.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dikar.api.entity.user.UserProfile;
import com.dikar.api.mapper.UserProfileMapper;
import com.dikar.api.service.user.UserProfileService;
import com.dikar.common.utils.IdWorker;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.service.user.impl
 * @Author: yefei
 * @CreateTime: 2021-06-01 16:20
 * @Description:
 */
@Service
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile> implements UserProfileService {


    @Override
    public void incFriendAskCount(Long friendUid, int count) {
        // 检查是否存在，如果存在，则直接修改数量，如果不存在新增一个
        UserProfile userProfile = baseMapper.selectOne(Wrappers.<UserProfile>lambdaQuery()
                .eq(UserProfile::getUid, friendUid));
        if (Objects.isNull(userProfile)) {
            UserProfile up = new UserProfile();
            up.setId(IdWorker.nextId());
            up.setFriendAskCount(count);
            up.setUid(friendUid);
            up.setCreateTime(new Date());
            up.setModifiedTime(new Date());
            baseMapper.insert(up);
        } else {
            userProfile.setFriendAskCount(userProfile.getFriendAskCount() + count);
            baseMapper.updateById(userProfile);
        }

    }

    @Override
    public void insertUserProfileAll(List<UserProfile> userProfileArrayList) {
        baseMapper.insertUserProfileAll(userProfileArrayList);
    }
}

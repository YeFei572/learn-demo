package com.dikar.api.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dikar.api.entity.user.UserProfile;

import java.util.List;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.service.user
 * @Author: yefei
 * @CreateTime: 2021-06-01 16:20
 * @Description:
 */
public interface UserProfileService extends IService<UserProfile> {
    /**
     * 增加好友数量
     * @param friendUid
     * @param count
     */
    void incFriendAskCount(Long friendUid, int count);

    void insertUserProfileAll(List<UserProfile> userProfileArrayList);
}

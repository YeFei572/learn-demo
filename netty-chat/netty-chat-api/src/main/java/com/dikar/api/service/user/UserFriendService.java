package com.dikar.api.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dikar.api.entity.user.UserFriend;

import java.util.List;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.service.user
 * @Author: yefei
 * @CreateTime: 2021-06-02 10:44
 * @Description:
 */
public interface UserFriendService extends IService<UserFriend> {
    /**
     * 朋友关系查询
     * @param uid
     * @param friendUid
     * @return
     */
    UserFriend findByUidAndFriendUid(Long uid, Long friendUid);

    void insertUserFriendAll(List<UserFriend> userFriends);
}

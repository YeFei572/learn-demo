package com.dikar.api.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dikar.api.entity.user.UserFriendMsg;
import com.dikar.api.mapper.UserFriendMsgMapper;
import com.dikar.api.service.user.UserFriendMsgService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.service.user.impl
 * @Author: yefei
 * @CreateTime: 2021-06-03 15:35
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class UserFriendMsgServiceImpl extends ServiceImpl<UserFriendMsgMapper, UserFriendMsg> implements UserFriendMsgService {

}

package com.dikar.api.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dikar.api.entity.user.UserProfile;
import com.dikar.api.mapper.UserProfileMapper;
import com.dikar.api.service.user.UserProfileService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.service.user.impl
 * @Author: yefei
 * @CreateTime: 2021-06-01 16:20
 * @Description:
 */
@Service
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile> implements UserProfileService {


}

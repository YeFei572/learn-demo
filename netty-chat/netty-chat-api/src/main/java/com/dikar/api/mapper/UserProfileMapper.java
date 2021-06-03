package com.dikar.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dikar.api.entity.user.UserProfile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.mapper
 * @Author: yefei
 * @CreateTime: 2021-06-01 16:17
 * @Description:
 */
@Mapper
public interface UserProfileMapper extends BaseMapper<UserProfile> {

    /**
     * 批量插入
     *
     * @param userProfiles
     * @return
     */
    boolean insertUserProfileAll(List<UserProfile> userProfiles);
}

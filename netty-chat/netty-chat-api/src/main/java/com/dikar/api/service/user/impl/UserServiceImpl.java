package com.dikar.api.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dikar.api.entity.user.User;
import com.dikar.api.mapper.UserMapper;
import com.dikar.api.service.user.UserService;
import com.dikar.api.vo.res.UserInfoListResVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.service.user.impl
 * @Author: yefei
 * @CreateTime: 2021-06-01 11:53
 * @Description: user table
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Map<Long, UserInfoListResVO> listByUidIn(List<Long> uids) {
        List<User> users = baseMapper.selectList(Wrappers.<User>lambdaQuery().in(User::getUid, uids));
        Map<Long, UserInfoListResVO> res = new HashMap<>(users.size());
        users.forEach(item -> {
            UserInfoListResVO userInfoListResVO = new UserInfoListResVO();
            BeanUtil.copyProperties(item, userInfoListResVO);
            res.put(item.getUid(), userInfoListResVO);
        });
        return res;
    }
}

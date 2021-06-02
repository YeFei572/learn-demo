package com.dikar.api.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dikar.api.entity.user.User;
import com.dikar.api.vo.res.UserInfoListResVO;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.service
 * @Author: yefei
 * @CreateTime: 2021-06-01 11:52
 * @Description:
 */
public interface UserService extends IService<User> {

    Map<Long, UserInfoListResVO> listByUidIn(List<Long> uids);

}

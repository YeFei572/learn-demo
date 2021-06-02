package com.dikar.api.controller.user;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dikar.api.dto.UserLoginDTO;
import com.dikar.api.entity.user.User;
import com.dikar.api.entity.user.UserProfile;
import com.dikar.api.service.user.UserProfileService;
import com.dikar.api.service.user.UserService;
import com.dikar.api.utils.UserLoginUtils;
import com.dikar.api.vo.res.UserInfoResVO;
import com.dikar.api.vo.res.UserProfileInfoResVO;
import com.dikar.common.enums.ResultEnum;
import com.dikar.common.utils.ResultVOUtils;
import com.dikar.common.vo.res.BaseResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.controller.user
 * @Author: yefei
 * @CreateTime: 2021-06-01 15:45
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserIndexController {

    private final UserService userService;
    private final UserProfileService userProfileService;

    @GetMapping(value = "/loginInfo")
    public BaseResVO loginInfo(HttpServletRequest request) {
        // 验证登录
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }

        Long uid = userLoginDTO.getUid();

        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUid, uid));
        if (user == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }

        UserProfile userProfile = userProfileService.getOne(Wrappers.<UserProfile>lambdaQuery().eq(UserProfile::getUid, uid));
        UserProfileInfoResVO userProfileInfoResVO = new UserProfileInfoResVO();
        if (userProfile != null) {
            BeanUtils.copyProperties(userProfile, userProfileInfoResVO);
        }

        UserInfoResVO userInfoResVO = new UserInfoResVO();
        BeanUtils.copyProperties(user, userInfoResVO);
        userInfoResVO.setProfile(userProfileInfoResVO);
        return ResultVOUtils.success(userInfoResVO);
    }
}

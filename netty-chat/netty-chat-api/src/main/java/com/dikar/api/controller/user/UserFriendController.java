package com.dikar.api.controller.user;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dikar.api.dto.UserLoginDTO;
import com.dikar.api.entity.user.UserFriend;
import com.dikar.api.service.user.UserFriendService;
import com.dikar.api.service.user.UserService;
import com.dikar.api.utils.UserLoginUtils;
import com.dikar.api.vo.res.UserInfoListResVO;
import com.dikar.api.vo.res.UserFriendListInfoResVO;
import com.dikar.common.enums.ResultEnum;
import com.dikar.common.utils.ResultVOUtils;
import com.dikar.common.vo.res.BaseResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.controller.user
 * @Author: yefei
 * @CreateTime: 2021-06-02 10:46
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user/friend")
public class UserFriendController {

    private final UserService userService;
    private final UserFriendService userFriendService;


    @GetMapping(value = "/lists")
    public BaseResVO listUserFriends(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
            HttpServletRequest request
    ) {
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (Objects.isNull(userLoginDTO)) {
            return ResultVOUtils.error(ResultEnum.AUTH_FAILED);
        }
        limit = limit > 50 ? 50 : limit;
        Long uid = userLoginDTO.getUid();
        List<UserFriend> userFriends = userFriendService.page(
                new Page<>(page, limit),
                Wrappers.<UserFriend>lambdaQuery()
                        .eq(UserFriend::getUid, uid)).getRecords();
        List<Long> uids = userFriends.stream().map(UserFriend::getFriendUid).collect(Collectors.toList());
        List<UserFriendListInfoResVO> userFriendListInfoResVOS = new ArrayList<>();
        if (CollUtil.isNotEmpty(uids)) {
            Map<Long, UserInfoListResVO> userInfoListResVOMap = userService.listByUidIn(uids);
            userFriends.forEach(v -> {
                UserFriendListInfoResVO userFriendListInfoResVO = new UserFriendListInfoResVO();
                BeanUtil.copyProperties(v, userFriendListInfoResVO);
                userFriendListInfoResVO.setUser(userInfoListResVOMap.get(v.getFriendUid()));
                userFriendListInfoResVOS.add(userFriendListInfoResVO);
            });
        }
        return ResultVOUtils.success(userFriendListInfoResVOS);
    }

}

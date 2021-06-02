package com.dikar.api.controller.user;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.dikar.api.entity.user.User;
import com.dikar.api.service.user.UserService;
import com.dikar.api.utils.UserLoginUtils;
import com.dikar.api.vo.res.UserLoginResVO;
import com.dikar.common.enums.ResultEnum;
import com.dikar.common.utils.IdWorker;
import com.dikar.common.utils.ResultVOUtils;
import com.dikar.common.vo.res.BaseResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.controller.user
 * @Author: yefei
 * @CreateTime: 2021-06-01 10:52
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user/login")
public class UserLoginController {

    private final UserService userService;

    @PostMapping(value = "/byTourist")
    public BaseResVO byTourist(@RequestParam(value = "sex") Integer type) {
        if (!Objects.equals(type, 1) && !Objects.equals(type, 2)) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "游客性别未选择");
        }
        String name = "火星人" + RandomUtil.randomString(7);
        String avatar = StrUtil.format("http://prbsvykmy.bkt.clouddn.com/static/image/user-{}-default.png", type);
        String remark = "你今生有没有坚定不移地相信过一件事或一个人？是那种至死不渝的相信？";
        // 创建用户
        User user = new User();
        user.setUid(IdWorker.nextId());
        user.setName(name);
        user.setAvatar(avatar);
        user.setRemark(remark);
        user.setCreateTime(new Date());
        user.setModifiedTime(new Date());
        boolean b = userService.save(user);
        if (!b) {
            return ResultVOUtils.error();
        }

        Long uid = user.getUid();
        String token = UserLoginUtils.createSid(uid);

        UserLoginResVO userLoginResVO = new UserLoginResVO();
        userLoginResVO.setUid(uid);
        userLoginResVO.setSid(token);
        return ResultVOUtils.success(userLoginResVO);
    }
}

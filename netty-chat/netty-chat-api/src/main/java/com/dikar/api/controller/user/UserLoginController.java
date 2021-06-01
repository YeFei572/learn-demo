package com.dikar.api.controller.user;

import com.dikar.common.enums.ResultEnum;
import com.dikar.common.utils.ResultVOUtils;
import com.dikar.common.vo.res.BaseResVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.controller.user
 * @Author: yefei
 * @CreateTime: 2021-06-01 10:52
 * @Description:
 */
@RestController
@RequestMapping(value = "/user/login")
public class UserLoginController {

    @PostMapping(value = "/byTourist")
    public BaseResVO byTourist(@RequestParam(value = "sex")Integer type) {
        if (!Objects.equals(type, 1) && !Objects.equals(type, 2)) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "游客性别未选择");
        }
        char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    }
}

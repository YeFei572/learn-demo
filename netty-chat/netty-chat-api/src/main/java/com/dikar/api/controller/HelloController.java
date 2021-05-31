package com.dikar.api.controller;

import com.dikar.api.utils.WSBaseReqUtils;
import com.dikar.api.vo.req.WSBaseReqVO;
import com.dikar.api.ws.WSServer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.controller
 * @Author: yefei
 * @CreateTime: 2021-05-31 17:02
 * @Description: controller example
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class HelloController {

    private final WSServer wsServer;

    @GetMapping(value = "/test")
    public String hello(@RequestParam("uid")Long uid) {
        Integer type = 1;
        Long id = uid;
        Integer msgType = 0;
        String msgContent = "嘿嘿测试";
        Long sUid = 1L;
        String name = "测试";
        String avatar = "头像";
        String remark = "说明";

        WSBaseReqVO wsBaseReqVO = WSBaseReqUtils.create(type, id, msgType, msgContent, sUid, name, avatar, remark);

        Boolean aBoolean = wsServer.sendMsg(uid, wsBaseReqVO);

        return aBoolean ? "success" : "客户端不在线";
    }

}

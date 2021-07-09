package com.dikar.api.controller.user;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dikar.api.dto.UserLoginDTO;
import com.dikar.api.entity.user.User;
import com.dikar.api.entity.user.UserFriend;
import com.dikar.api.entity.user.UserFriendMsg;
import com.dikar.api.service.user.UserFriendMsgService;
import com.dikar.api.service.user.UserFriendService;
import com.dikar.api.service.user.UserService;
import com.dikar.api.utils.IdUtils;
import com.dikar.api.utils.UserLoginUtils;
import com.dikar.api.utils.WSBaseReqUtils;
import com.dikar.api.vo.req.UserFriendMsgSaveReqVO;
import com.dikar.api.vo.req.WSBaseReqVO;
import com.dikar.api.ws.WSServer;
import com.dikar.common.constants.WSMsgTypeConstant;
import com.dikar.common.constants.WSResTypeConstant;
import com.dikar.common.enums.ResultEnum;
import com.dikar.common.utils.ResultVOUtils;
import com.dikar.common.vo.res.BaseResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.controller.user
 * @Author: yefei
 * @CreateTime: 2021-06-04 09:28
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user/friendMsg")
public class UserFriendMsgController {

    private final IdUtils idUtils;
    private final WSServer wsServer;
    private final UserService userService;
    private final UserFriendMsgService userFriendMsgService;
    private final UserFriendService userFriendService;

    /**
     * 获取指定用户的消息记录
     *
     * @param senderUid
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @GetMapping(value = "/lists")
    public BaseResVO lists(
            @RequestParam(value = "senderUid") Long senderUid,
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
        // 把最小的那个 用户ID作为查询条件
        Long toUid = senderUid;
        if (uid > senderUid) {
            toUid = uid;
            uid = senderUid;
        }
        List<UserFriendMsg> userFriendMsgs = userFriendMsgService.page(new Page<>(page, limit),
                Wrappers.<UserFriendMsg>lambdaQuery()
                        .eq(UserFriendMsg::getUid, uid)
                        .eq(UserFriendMsg::getToUid, toUid)
        ).getRecords();
        return ResultVOUtils.success(userFriendMsgs);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/create")
    public BaseResVO createMsg(
            @Valid @RequestBody UserFriendMsgSaveReqVO userFriendMsgSaveReqVO,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        // 验证登录
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
        Long uid = userLoginDTO.getUid();

        Long receiverUid = userFriendMsgSaveReqVO.getReceiverUid();

        // 判断是不是朋友
        UserFriend userFriend = userFriendService.findByUidAndFriendUid(uid, receiverUid);
        if (userFriend == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "该用户还不是你的好友~");
        }
        Long senderUid = uid;
        // 追加消息
        UserFriendMsg userFriendMsg = new UserFriendMsg();
        // 把最小的那个 用户ID作为 之后的查询uid
        Long toUid = receiverUid;
        if (uid > receiverUid) {
            toUid = uid;
            uid = receiverUid;
        }
        userFriendMsg.setUid(uid);
        userFriendMsg.setToUid(toUid);
        userFriendMsg.setSenderUid(senderUid);
        userFriendMsg.setCreateTime(new Date());
        Integer msgType = userFriendMsgSaveReqVO.getMsgType();
        String msgContent = userFriendMsgSaveReqVO.getMsgContent();
        String lastMsgContent = msgContent;
        switch (msgType) {
            case WSMsgTypeConstant.TEXT:
                break;
            case WSMsgTypeConstant.IMAGE:
                lastMsgContent = "[图片消息]";
                break;
            case WSMsgTypeConstant.FILE:
                lastMsgContent = "[文件消息]";
                break;
            case WSMsgTypeConstant.VOICE:
                lastMsgContent = "[语言消息]";
                break;
            case WSMsgTypeConstant.VIDEO:
                lastMsgContent = "[视频消息]";
                break;
            default:
                return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "位置消息类型");
        }
        userFriendMsg.setMsgType(msgType);
        userFriendMsg.setMsgContent(msgContent);
        boolean b = userFriendMsgService.save(userFriendMsg);
        if (!b) {
            return ResultVOUtils.error();
        }

        List<UserFriend> userFriends = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            UserFriend uf = UserFriend.builder()
                    .id(idUtils.nexId())
                    .uid(i == 0 ? receiverUid : senderUid)
                    .friendUid(i == 0 ? senderUid : receiverUid)
                    .unMsgCount(i == 0 ? 1 : 0)
                    .lastMsgContent(lastMsgContent)
                    .createTime(new Date())
                    .modifiedTime(new Date()).build();
            userFriends.add(uf);
        }

        userFriendService.insertUserFriendAll(userFriends);

        // 发送在线消息
        // 查询用户信息

        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUid, senderUid));
        Long sUid = user.getUid();
        String name = user.getName();
        String avatar = user.getAvatar();
        String remark = user.getRemark();
        WSBaseReqVO wsBaseReqVO = WSBaseReqUtils.create(WSResTypeConstant.FRIEND, receiverUid, msgType, msgContent, sUid, name, avatar, remark);
        wsServer.sendMsg(receiverUid, wsBaseReqVO);

        return ResultVOUtils.success();
    }
}

package com.dikar.api.controller.user;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dikar.api.dto.UserLoginDTO;
import com.dikar.api.entity.user.*;
import com.dikar.api.service.user.*;
import com.dikar.api.utils.IdUtils;
import com.dikar.api.utils.UserFriendUtils;
import com.dikar.api.utils.UserLoginUtils;
import com.dikar.api.utils.WSBaseReqUtils;
import com.dikar.api.vo.req.UserFriendAskAckReqVO;
import com.dikar.api.vo.req.WSBaseReqVO;
import com.dikar.api.vo.res.UserFriendAskListResVO;
import com.dikar.api.vo.res.UserInfoListResVO;
import com.dikar.api.ws.WSServer;
import com.dikar.common.constants.WSMsgTypeConstant;
import com.dikar.common.constants.WSResTypeConstant;
import com.dikar.common.enums.ResultEnum;
import com.dikar.common.utils.IdWorker;
import com.dikar.common.utils.ResultVOUtils;
import com.dikar.common.vo.res.BaseResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.controller.user
 * @Author: yefei
 * @CreateTime: 2021-06-03 10:41
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user/friendAsk")
public class UserFriendAskController {

    private final IdUtils idUtils;
    private final WSServer wsServer;
    private final UserService userService;
    private final UserProfileService userProfileService;
    private final UserFriendAskService userFriendAskService;
    private final UserFriendMsgService userFriendMsgService;
    private final UserFriendService userFriendService;

    @GetMapping(value = "/lists")
    public BaseResVO listUserFriendAsk(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
            HttpServletRequest request
    ) {
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (Objects.isNull(userLoginDTO)) {
            return ResultVOUtils.error(ResultEnum.AUTH_FAILED);
        }
        limit = limit > 50 ? 50 : limit;
        Long uid = userLoginDTO.getUid();
        List<UserFriendAsk> userFriendAsks = userFriendAskService.page(
                new Page<>(page, limit),
                Wrappers.<UserFriendAsk>lambdaQuery()
                        .eq(UserFriendAsk::getUid, uid)).getRecords();
        List<Long> uids = userFriendAsks.stream().map(UserFriendAsk::getFriendUid).collect(Collectors.toList());
        List<UserFriendAskListResVO> userFriendListInfoResVOS = new ArrayList<>();
        if (CollUtil.isNotEmpty(uids)) {
            Map<Long, UserInfoListResVO> userInfoListResVOMap = userService.listByUidIn(uids);
            userFriendAsks.forEach(v -> {
                UserFriendAskListResVO userFriendAskListResVO = new UserFriendAskListResVO();
                BeanUtil.copyProperties(v, userFriendAskListResVO);
                userFriendAskListResVO.setUser(userInfoListResVOMap.get(v.getFriendUid()));
                userFriendListInfoResVOS.add(userFriendAskListResVO);
            });
        }
        return ResultVOUtils.success(userFriendListInfoResVOS);
    }

    /**
     * 创建好友请求
     *
     * @param checkCode
     * @param remark
     * @param friendUid
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/create")
    public BaseResVO createFriendAsk(
            @RequestParam(value = "checkCode", required = false, defaultValue = "") String checkCode,
            @RequestParam(value = "remark", required = false, defaultValue = "") String remark,
            @RequestParam(value = "friendUid") Long friendUid,
            HttpServletRequest request
    ) {
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (Objects.isNull(userLoginDTO)) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
        Long uid = userLoginDTO.getUid();
        if (Objects.isNull(uid) || uid < 1) {
            friendUid = UserFriendUtils.checkCode(checkCode);
        }
        // 验证用户是否合法加入
        if (friendUid == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "二维码过期~");
        }
        if (uid.equals(friendUid)) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "不能自己加自己~");
        }
        UserFriend userFriend = userFriendService.findByUidAndFriendUid(uid, friendUid);
        if (userFriend != null) {
            return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "已经是好友了~");
        }
        User friendUser = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUid, friendUid));
        if (friendUser == null) {
            return ResultVOUtils.error(ResultEnum.DATA_NOT);
        }

        // 判断好友数量
        List<UserProfile> userProfiles = userProfileService.list(Wrappers.<UserProfile>lambdaQuery()
                .in(UserProfile::getUid, Arrays.asList(uid, friendUid)));
        for (UserProfile userProfile : userProfiles) {
            if (userProfile != null
                    && userProfile.getFriendCount() != null
                    && userProfile.getFriendCount() >= 2000) {
                return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "好友数量已到上限~");
            }
        }

        UserFriendAsk userFriendAsk = new UserFriendAsk();
        userFriendAsk.setId(IdWorker.nextId());
        userFriendAsk.setUid(friendUid);
        userFriendAsk.setFriendUid(uid);
        userFriendAsk.setRemark(remark);
        userFriendAsk.setStatus(0);
        userFriendAsk.setCreateTime(new Date());
        userFriendAsk.setModifiedTime(new Date());
        boolean saveFlag = userFriendAskService.save(userFriendAsk);
        if (!saveFlag) {
            return ResultVOUtils.error();
        }
        // 增加朋友请求数量
        userProfileService.incFriendAskCount(friendUid, 1);
        // 发送在线消息
        // 查询用户信息
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUid, uid));
        Integer msgType = WSMsgTypeConstant.TEXT;
        String msgContent = "请求加为好友";
        Long sUid = user.getUid();
        String name = user.getName();
        String avatar = user.getAvatar();
        String remark1 = user.getRemark();
        WSBaseReqVO wsBaseReqVO = WSBaseReqUtils.create(WSResTypeConstant.FRIEND_ASK, friendUid, msgType, msgContent, sUid, name, avatar, remark1);

        wsServer.sendMsg(friendUid, wsBaseReqVO);

        return ResultVOUtils.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/ack")
    public BaseResVO ack(
            @Valid @RequestBody UserFriendAskAckReqVO userFriendAskAckReqVO,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        // 验证登录
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
        Long uid = userLoginDTO.getUid();
        Long id = userFriendAskAckReqVO.getId();
        UserFriendAsk userFriendAsk = userFriendAskService.getOne(Wrappers.<UserFriendAsk>lambdaQuery().eq(UserFriendAsk::getId, id));
        // 已经添加过了
        if (userFriendAsk == null || !uid.equals(userFriendAsk.getUid()) || userFriendAsk.getStatus() != 0) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "请勿重复添加~");
        }
        UserFriendAsk upUserFriendAsk = new UserFriendAsk();
        upUserFriendAsk.setId(id);
        upUserFriendAsk.setStatus(userFriendAskAckReqVO.getStatus());
        boolean b = userFriendAskService.updateById(upUserFriendAsk);
        // 拒绝添加
        if (!b) {
            return ResultVOUtils.error();
        }
        // 如果是拒绝
        if (userFriendAskAckReqVO.getStatus() == 2) {
            return ResultVOUtils.success();
        }
        Long friendUid = userFriendAsk.getFriendUid();
        // 判断是不是好友
        UserFriend userFriend = userFriendService.findByUidAndFriendUid(uid, friendUid);
        if (userFriend != null && userFriend.getId() != null) {
            return ResultVOUtils.success();
        }
        // 判断好友数量
        List<UserProfile> userProfiles = userProfileService.list(Wrappers.<UserProfile>lambdaQuery()
                .in(UserProfile::getUid, Arrays.asList(uid, friendUid)));
        for (UserProfile userProfile : userProfiles) {
            if (userProfile != null
                    && userProfile.getFriendCount() != null
                    && userProfile.getFriendCount() >= 2000) {
                return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "好友数量已到上限~");
            }
        }
        String msgContent = userFriendAsk.getRemark();

        msgContent = msgContent != null && !"".equals(msgContent) ? msgContent : "成为好友，现在开始聊吧~";

        // 增加朋友
        List<UserFriend> userFriends = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            UserFriend uf = UserFriend.builder()
                    .id(idUtils.nexId())
                    .uid(i == 0 ? uid : friendUid)
                    .friendUid(i == 0 ? friendUid : uid)
                    .remark("")
                    .lastMsgContent(msgContent)
                    .createTime(new Date())
                    .modifiedTime(new Date())
                    .build();
            userFriends.add(uf);
        }
        userFriendService.saveBatch(userFriends);

        // 更新好友数量
        List<UserProfile> userProfileArrayList = new ArrayList<>();
        // 先查出对应的uid的userProfile
        for (int i = 0; i < 2; i++) {
            UserProfile up = UserProfile.builder()
                    .id(idUtils.nexId())
                    .uid(i == 0 ? uid : friendUid)
                    .friendCount(1)
                    .createTime(new Date())
                    .modifiedTime(new Date())
                    .build();
            userProfileArrayList.add(up);
        }
        userProfileService.insertUserProfileAll(userProfileArrayList);

        Long senderUid = uid;

        // 追加消息
        UserFriendMsg userFriendMsg = new UserFriendMsg();
        // 把最小的那个 用户ID作为 之后的查询uid
        Long toUid = friendUid;
        if (uid > friendUid) {
            toUid = uid;
            uid = friendUid;
        }
        userFriendMsg.setUid(uid);
        userFriendMsg.setToUid(toUid);
        userFriendMsg.setSenderUid(senderUid);
        userFriendMsg.setMsgContent(msgContent);
        userFriendMsg.setMsgType(1);
        userFriendMsg.setCreateTime(new Date());
        userFriendMsgService.save(userFriendMsg);

        // 发送在线消息
        // 查询用户信息
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUid, senderUid));
        Integer msgType = WSMsgTypeConstant.TEXT;
        Long sUid = user.getUid();
        String name = user.getName();
        String avatar = user.getAvatar();
        String remark1 = user.getRemark();
        WSBaseReqVO wsBaseReqVO = WSBaseReqUtils.create(WSResTypeConstant.FRIEND_ACK, friendUid, msgType, msgContent, sUid, name, avatar, remark1);
        wsServer.sendMsg(friendUid, wsBaseReqVO);

        return ResultVOUtils.success();
    }
}

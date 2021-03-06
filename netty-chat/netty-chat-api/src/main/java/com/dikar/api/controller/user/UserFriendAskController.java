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
     * ??????????????????
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
        // ??????????????????????????????
        if (friendUid == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "???????????????~");
        }
        if (uid.equals(friendUid)) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "?????????????????????~");
        }
        UserFriend userFriend = userFriendService.findByUidAndFriendUid(uid, friendUid);
        if (userFriend != null) {
            return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "??????????????????~");
        }
        User friendUser = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUid, friendUid));
        if (friendUser == null) {
            return ResultVOUtils.error(ResultEnum.DATA_NOT);
        }

        // ??????????????????
        List<UserProfile> userProfiles = userProfileService.list(Wrappers.<UserProfile>lambdaQuery()
                .in(UserProfile::getUid, Arrays.asList(uid, friendUid)));
        for (UserProfile userProfile : userProfiles) {
            if (userProfile != null
                    && userProfile.getFriendCount() != null
                    && userProfile.getFriendCount() >= 2000) {
                return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "????????????????????????~");
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
        // ????????????????????????
        userProfileService.incFriendAskCount(friendUid, 1);
        // ??????????????????
        // ??????????????????
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUid, uid));
        Integer msgType = WSMsgTypeConstant.TEXT;
        String msgContent = "??????????????????";
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
        // ????????????
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
        Long uid = userLoginDTO.getUid();
        Long id = userFriendAskAckReqVO.getId();
        UserFriendAsk userFriendAsk = userFriendAskService.getOne(Wrappers.<UserFriendAsk>lambdaQuery().eq(UserFriendAsk::getId, id));
        // ??????????????????
        if (userFriendAsk == null || !uid.equals(userFriendAsk.getUid()) || userFriendAsk.getStatus() != 0) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "??????????????????~");
        }
        UserFriendAsk upUserFriendAsk = new UserFriendAsk();
        upUserFriendAsk.setId(id);
        upUserFriendAsk.setStatus(userFriendAskAckReqVO.getStatus());
        boolean b = userFriendAskService.updateById(upUserFriendAsk);
        // ????????????
        if (!b) {
            return ResultVOUtils.error();
        }
        // ???????????????
        if (userFriendAskAckReqVO.getStatus() == 2) {
            return ResultVOUtils.success();
        }
        Long friendUid = userFriendAsk.getFriendUid();
        // ?????????????????????
        UserFriend userFriend = userFriendService.findByUidAndFriendUid(uid, friendUid);
        if (userFriend != null && userFriend.getId() != null) {
            return ResultVOUtils.success();
        }
        // ??????????????????
        List<UserProfile> userProfiles = userProfileService.list(Wrappers.<UserProfile>lambdaQuery()
                .in(UserProfile::getUid, Arrays.asList(uid, friendUid)));
        for (UserProfile userProfile : userProfiles) {
            if (userProfile != null
                    && userProfile.getFriendCount() != null
                    && userProfile.getFriendCount() >= 2000) {
                return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "????????????????????????~");
            }
        }
        String msgContent = userFriendAsk.getRemark();

        msgContent = msgContent != null && !"".equals(msgContent) ? msgContent : "?????????????????????????????????~";

        // ????????????
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

        // ??????????????????
        List<UserProfile> userProfileArrayList = new ArrayList<>();
        // ??????????????????uid???userProfile
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

        // ????????????
        UserFriendMsg userFriendMsg = new UserFriendMsg();
        // ?????????????????? ??????ID?????? ???????????????uid
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

        // ??????????????????
        // ??????????????????
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

package com.dikar.api.utils;

import com.dikar.api.vo.req.WSBaseReqVO;
import com.dikar.api.vo.req.WSMessageReqVO;
import com.dikar.api.vo.req.WSUserReqVO;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.utils
 * @Author: yefei
 * @CreateTime: 2021-05-31 17:08
 * @Description:
 */
public class WSBaseReqUtils {


    /**
     * 创建请求实体类
     * @return
     */
    public static WSBaseReqVO create(Integer type, Long receiveId, Integer msgType, String msgContent, Long uid, String name, String avatar, String remark) {

        // 消息实体
        WSMessageReqVO wsMessageReqVO = new WSMessageReqVO();
        wsMessageReqVO.setReceiveId(receiveId != null ? receiveId : 0);
        wsMessageReqVO.setMsgType(msgType != null ? msgType : 0);
        wsMessageReqVO.setMsgContent(msgContent);

        // 用户信息
        WSUserReqVO wsUserReqVO = new WSUserReqVO();
        wsUserReqVO.setUid(uid != null ? uid : 0);
        wsUserReqVO.setName(name != null ? name : "");
        wsUserReqVO.setAvatar(avatar != null ? avatar : "");
        wsUserReqVO.setRemark(remark != null ? remark : "");

        WSBaseReqVO wsBaseReqVO = new WSBaseReqVO();
        wsBaseReqVO.setType(type != null ? type : 0);
        wsBaseReqVO.setMessage(wsMessageReqVO);
        wsBaseReqVO.setUser(wsUserReqVO);
        return wsBaseReqVO;
    }
}

package com.dikar.common.constants;

import lombok.Getter;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.common.constants
 * @Author: yefei
 * @CreateTime: 2021-06-03 11:29
 * @Description: 消息类型（0：普通文字消息，1：图片消息，2：文件消息，3：语音消息，4：视频消息）
 */
@Getter
public class WSMsgTypeConstant {

    public static final int TEXT = 0;
    public static final int IMAGE = 1;
    public static final int FILE = 2;
    public static final int VOICE = 3;
    public static final int VIDEO = 4;
}

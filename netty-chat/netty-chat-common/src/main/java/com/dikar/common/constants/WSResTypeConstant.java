package com.dikar.common.constants;

import lombok.Getter;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.common.constants
 * @Author: yefei
 * @CreateTime: 2021-05-31 11:23
 * @Description:
 */
@Getter
public class WSResTypeConstant {
    public static final int LOGIN_OUT = -2;
    public static final int WS_OUT = -1;
    public static final int PING = 0;
    public static final int FRIEND = 1;
    public static final int GROUP = 2;
    public static final int FRIEND_ASK = 3;
    public static final int FRIEND_ACK = 4;
    public static final int JOIN_GROUP = 5;
}

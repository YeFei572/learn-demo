package com.dikar.api.utils;

import cn.hutool.core.util.StrUtil;
import com.dikar.api.dto.UserLoginDTO;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.utils
 * @Author: yefei
 * @CreateTime: 2021-05-31 10:02
 * @Description:
 */
public class UserLoginUtils {

    public static String createSid(Long uid, Long ttl) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", uid);
        return JwtUtils.createToken(claims, ttl);
    }

    public static String createSid(Long uid) {
        return createSid(uid, null);
    }

    /**
     * 通过token获取uid进行比对
     *
     * @param uid
     * @param token
     * @return
     */
    public static Boolean checkToken(Long uid, String token) {
        if (Objects.isNull(token) || token.isEmpty()) {
            return false;
        }
        try {
            Claims claims = JwtUtils.parse(token);
            if (Objects.isNull(claims)) {
                return false;
            }
            Long jwtUid = Long.valueOf(claims.get("uid").toString());
            if (uid.compareTo(jwtUid) != 0) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static UserLoginDTO check(HttpServletRequest request) {
        String sUid = request.getParameter("UID");
        String token = request.getParameter("SID");
        if (StrUtil.isBlank(sUid) || StrUtil.isBlank(token)) {
            Cookie[] cookies = request.getCookies();
            if (Objects.isNull(cookies)) {
                return null;
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("UID")) {
                    sUid = cookie.getValue();
                } else if (cookie.getName().equals("SID")) {
                    token = cookie.getValue();
                }
            }
        }
        if (Objects.isNull(sUid) || Objects.isNull(token)) {
            return null;
        }
        Long uid = null;
        try {
            uid = Long.valueOf(sUid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!checkToken(uid, token)) {
            return null;
        }
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUid(uid);
        return userLoginDTO;
    }
}

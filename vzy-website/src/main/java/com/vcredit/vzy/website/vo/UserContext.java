package com.vcredit.vzy.website.vo;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/3/18
 */
@Slf4j
@RequiredArgsConstructor
public class UserContext {

    private static final ThreadLocal<String> USER_CONTEXT_HOLDER = new ThreadLocal<>();
    private static final ThreadLocal<String> TOKEN_CONTEXT_HOLDER = new ThreadLocal<>();

    private static final ThreadLocal<List<Long>> USER_DATA_PERMISSION = new ThreadLocal<>();

    public static String getCurrentAccount() {
        UserVO userInfo = JSONObject.parseObject(USER_CONTEXT_HOLDER.get(), UserVO.class);
        return userInfo.getUserAccount();
    }


    public static String getCurrentUserInfo() {
        return USER_CONTEXT_HOLDER.get();
    }


    public static void addCurrentUserInfo(String userInfo) {
        USER_CONTEXT_HOLDER.set(userInfo);
    }

    public static String getCurrentToken() {
        return TOKEN_CONTEXT_HOLDER.get();
    }


    public static void addCurrentToken(String token) {
        TOKEN_CONTEXT_HOLDER.set(token);
    }

    public static void remove() {
        USER_CONTEXT_HOLDER.remove();
        USER_DATA_PERMISSION.remove();
        TOKEN_CONTEXT_HOLDER.remove();
    }

}

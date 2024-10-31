package com.vcredit.vzy.website.controller;

import com.alibaba.fastjson.JSONObject;
import com.vcredit.ashura.sso.SingleSignOn;
import com.vcredit.ashura.sso.model.AuthApplicationVO;
import com.vcredit.ashura.sso.model.Oauth2Token;
import com.vcredit.ashura.sso.model.Oauth2UserInfo;
import com.vcredit.vzy.common.dto.ApiResponse;
import com.vcredit.vzy.common.utils.Constants;
import com.vcredit.vzy.website.vo.UserContext;
import com.vcredit.vzy.website.vo.UserVO;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/3/18
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final SingleSignOn singleSignOn;
    private final RedisTemplate<Object, Object> redisTemplate;

    @Value("${security.oauth2.client.index-redirect-url:}")
    private String indexRedirectUrl;
    @Value("${application-name:}")
    private String applicationName;

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @GetMapping("/login")
    public ApiResponse login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        Oauth2Token oauth2Token = singleSignOn.getToken(code);
        Oauth2UserInfo userInfo = singleSignOn.getUserInfo(oauth2Token.getAccess_token());

        Set<String> roleSet = null;
        Set<String> permissionSet = null;
        Set<String> dataPermissionSet = null;
        if (StringUtils.isNotBlank(oauth2Token.getExpires_in())) {
            userInfo.setTokenExpireTime(LocalDateTime.now().plusSeconds(Integer.parseInt(oauth2Token.getExpires_in())));
        }
        Map<String, AuthApplicationVO> applicationVOMap = userInfo.getAuthApplicationVOList().stream()
            .collect(Collectors.toMap(AuthApplicationVO::getApplicationName, Function.identity()));
        if (CollectionUtils.isNotEmpty(userInfo.getAuthApplicationVOList()) && StringUtils.isNotBlank(applicationName)) {
            AuthApplicationVO authApplicationVO = applicationVOMap.get(applicationName);
            if (Objects.nonNull(authApplicationVO)) {
                if (authApplicationVO.getPermissionEffectiveTime() != null && authApplicationVO.getPermissionEffectiveTime() > 0) {
                    authApplicationVO.setPermissionExpireTime(LocalDateTime.now().plusSeconds(authApplicationVO.getPermissionEffectiveTime()));
                }
                roleSet = authApplicationVO.getRoleCodeList();
                permissionSet = authApplicationVO.getPermissionCodeList();
                dataPermissionSet = authApplicationVO.getDataPermissionCodeList();
                userInfo.setAuthApplicationVO(authApplicationVO);
            }
        }

        // 设置cookie token
        Cookie cookie = new Cookie("Vzy-Website-Token", URLEncoder.encode(oauth2Token.getAccess_token(), StandardCharsets.UTF_8));
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        UserVO userVO = new UserVO();
        userVO.setOauth2UserInfo(userInfo);
        userVO.setUserName(userInfo.getUserName());
        userVO.setUserAccount(userInfo.getUserAccount());
        if (CollectionUtils.isNotEmpty(roleSet)) {
            userVO.setRoleList((roleSet));
        }
        if (CollectionUtils.isNotEmpty(permissionSet)) {
            userVO.setPermissionList((permissionSet));
        }
        if (CollectionUtils.isNotEmpty(dataPermissionSet)) {
            userVO.setDataPermissionList((dataPermissionSet));
        }

        String redisKey = Constants.LOGIN_REDIS_KEY_PREFIX.concat(oauth2Token.getAccess_token());
        redisTemplate.opsForValue().set(redisKey, JSONObject.toJSONString(userVO), 60 * 60 * 12, TimeUnit.SECONDS);
        response.sendRedirect(indexRedirectUrl);
        return ApiResponse.success();
    }

    @GetMapping("/logout")
    public ApiResponse logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String accessToken = getAccessToken(request);
        if (StringUtils.isNotBlank(accessToken)) {
            String redisKey = Constants.LOGIN_REDIS_KEY_PREFIX.concat(accessToken);
            redisTemplate.delete(redisKey);
            singleSignOn.logout(accessToken);
        }
        return ApiResponse.success();
    }

    @GetMapping("/user")
    public ApiResponse<UserVO> user(HttpServletRequest request) {
        UserVO oauth2UserInfo = JSONObject.parseObject(UserContext.getCurrentUserInfo(), UserVO.class);
        oauth2UserInfo.setUserAccountAes(Base64.getEncoder().encodeToString(oauth2UserInfo.getUserAccount().getBytes(StandardCharsets.UTF_8)));
        return ApiResponse.success(oauth2UserInfo);
    }

    public static String getAccessToken(HttpServletRequest request) {
        String accessToken = null;
        String headerToken = request.getHeader(TOKEN_HEADER);
        if (StringUtils.isNotBlank(headerToken) && headerToken.startsWith(TOKEN_PREFIX)) {
            accessToken = headerToken.replace(TOKEN_PREFIX, "");
        }
        return accessToken;
    }

}

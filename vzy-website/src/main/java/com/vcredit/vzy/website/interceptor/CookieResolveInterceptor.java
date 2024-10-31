package com.vcredit.vzy.website.interceptor;

import com.vcredit.ashura.sso.SingleSignOn;
import com.vcredit.vzy.common.utils.Constants;
import com.vcredit.vzy.website.vo.UserContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/3/18
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CookieResolveInterceptor implements HandlerInterceptor {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final SingleSignOn singleSignOn;
    private final RedisTemplate<Object, Object> redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String userInfo = "";
            String accessToken = getAccessToken(request);
            if (StringUtils.isNotEmpty(accessToken)) {
                String redisKey = Constants.LOGIN_REDIS_KEY_PREFIX.concat(accessToken);
                userInfo = (String) redisTemplate.opsForValue().get(redisKey);
            }

            String pathUrl = request.getRequestURL().toString();
            log.info("请求的URL：{}", pathUrl);
            if (StringUtils.isBlank(userInfo)) {
                // 判断未登录跳转到登录页面
                String logInUrl = singleSignOn.getLogInUrl();
                log.info("认证的url：{}", logInUrl);
                response.setHeader("location", logInUrl);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return false;
            }
            UserContext.addCurrentUserInfo(userInfo);
            UserContext.addCurrentToken(accessToken);
            return true;

        } catch (Exception e) {
            log.error("获取登录信息异常，强行要求登录！userInfo:{}", e.getMessage());
            String logInUrl = singleSignOn.getLogInUrl();
            response.setHeader("location", logInUrl);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        String username = UserContext.getCurrentAccount();
        log.info("CookieResolveInterceptor postHandle   username:{}", username);
        clearContext();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        clearContext();
    }

    private void clearContext() {
        UserContext.remove();
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

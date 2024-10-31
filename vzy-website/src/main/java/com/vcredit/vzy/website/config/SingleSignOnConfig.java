package com.vcredit.vzy.website.config;

import com.vcredit.ashura.sso.SingleSignOn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/3/18
 */
@Configuration
public class SingleSignOnConfig {
    @Value("${security.oauth2.client.client-id:}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret:}")
    private String clientSecret;
    @Value("${security.oauth2.client.redirect-url:}")
    private String redirectUri;
    @Value("${security.oauth2.client.access-token-uri:}")
    private String accessTokenUrl;
    @Value("${security.oauth2.client.user-authorization-uri:}")
    private String userAuthorizationUrl;
    @Value("${security.oauth2.client.authorized-grant-types:}")
    private String authorizedGrantTypes;
    @Value("${security.oauth2.client.user-info-uri:}")
    private String userInfoUrl;
    @Value("${security.oauth2.client.user-exit-uri:}")
    private String logoutUrl;

    @Bean
    public SingleSignOn getSingleSignOn() {
        return new SingleSignOn(
                clientId,
                clientSecret,
                redirectUri,
                userAuthorizationUrl,
                authorizedGrantTypes,
                accessTokenUrl,
                userInfoUrl,
                logoutUrl);
    }
}

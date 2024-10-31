package com.vcredit.vzy.website.vo;

import com.vcredit.ashura.sso.model.Oauth2UserInfo;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/3/18
 */
@Data
public class UserVO implements Serializable {
    public Oauth2UserInfo oauth2UserInfo;
    public String userName;
    public String userAccount;
    public String userAccountAes;
    private Set<String> roleList = new HashSet<>();
    private Set<String> permissionList = new HashSet<>();
    private Set<String> dataPermissionList = new HashSet<>();

}

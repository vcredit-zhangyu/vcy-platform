package com.vcredit.vzy.website.vo;

import com.vcredit.ashura.sso.model.AuthApplicationVO;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/3/18
 */
@Data
public class Oauth2UserVO implements Serializable {


    private static final long serialVersionUID = -635709423766298440L;
    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 平台账户
     */
    private String userAccount;

    /**
     * 平台密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 工号
     */
    private String employeeNo;

    /**
     * 域控账户
     */
    private String ldapAccount;

    /**
     * 第一位-平台账户， 第二位-域账户,第三位-手机号登录，第四位预留
     */
    private String loginType;

    /**
     * 岗位
     */
    private String position;

    /**
     * 头像
     */
    private String avatarUrl;

    private LocalDateTime tokenExpireTime;

    private List<AuthApplicationVO> authApplicationVOList;

    private AuthApplicationVO authApplicationVO;

    private String permissionCode;
}

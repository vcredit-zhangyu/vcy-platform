package com.vcredit.vzy.common.dto;

import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/5/28
 */
@Data
public class AppVersionCommitDTO {

    private Long versionId;
    private String appVersion;
    private String versionRemark;
    private String appPlatform;

    private String loginUser;
    private String loginUserAcct;
    private String downloadUrl;
}

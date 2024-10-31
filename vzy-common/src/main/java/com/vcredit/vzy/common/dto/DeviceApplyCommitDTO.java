package com.vcredit.vzy.common.dto;

import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/18
 */
@Data
public class DeviceApplyCommitDTO {

    private Long deviceId;

    private String applyType;

    private String applyPeriod;

    private String loginUser;
    private String loginUserAcct;
    private String applyReason;
}

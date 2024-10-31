package com.vcredit.vzy.common.dto;

import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/18
 */
@Data
public class DeviceApplyApproveDTO {

    private Long applyId;

    private String deviceId;

    private String approveResult;

    private String approveReason;

    private String loginUser;
    private String loginUserAcct;
    private String applyReason;

}

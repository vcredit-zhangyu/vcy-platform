package com.vcredit.vzy.common.dto;

import lombok.Data;

@Data
public class DeviceFeedbackAddDTO {

    private String id;

    /**
     * 设备ID
     */
    private String deviceNo;
    /**
     * 反馈内容
     */
    private String content;
    private String handleResult;

    private String loginUser;
    private String loginUserAcct;

}

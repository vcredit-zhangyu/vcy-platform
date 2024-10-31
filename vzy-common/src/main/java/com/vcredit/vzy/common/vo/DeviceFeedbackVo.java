package com.vcredit.vzy.common.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeviceFeedbackVo {
    private String id;
    private String deviceNo;
    private String content;
    private String handleResult;
    //反馈时间
    private LocalDateTime date;

}

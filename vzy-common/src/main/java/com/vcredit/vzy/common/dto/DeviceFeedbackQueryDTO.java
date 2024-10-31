package com.vcredit.vzy.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeviceFeedbackQueryDTO {
    private Integer currentPage;
    private Integer pageSize;
    private String deviceNo;
    private String handleResult;
    private LocalDateTime feedbackStartTime;
    private LocalDateTime feedbackEndTime;
}

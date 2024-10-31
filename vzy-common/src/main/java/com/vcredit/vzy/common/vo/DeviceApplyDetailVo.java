package com.vcredit.vzy.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/5/27
 */
@Data
public class DeviceApplyDetailVo {

    private Long applyId;
    private String applyType;
    private String deviceName;
    private String deviceNo;
    private String applyStatus;
    private String approveReason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime applyTime;
    private String applyPerson;
    private String approvePerson;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedTime;

    private String applyReason;

}

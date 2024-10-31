package com.vcredit.vzy.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/18
 */
@Data
public class DeviceAppVo {

    private String deviceNo;
    private String deviceType;
    private String deviceName;
    private String deviceSystem;
    private String deviceSystemVersion;
    private String app;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime appTime;
    private String deviceCharge;
    private String downloadUrl;

}

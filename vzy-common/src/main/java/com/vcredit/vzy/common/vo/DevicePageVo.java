package com.vcredit.vzy.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/18
 */
@Data
public class DevicePageVo {

    private String deviceId;
    private String deviceNo;
    private String deviceType;
    private String deviceName;
    private String deviceModel;
    private String deviceSystem;
    private String deviceSystemVersion;
    private String deviceCharge;
    private String deviceChargeAcct;
    private String deviceStatus;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate expireDate;

}

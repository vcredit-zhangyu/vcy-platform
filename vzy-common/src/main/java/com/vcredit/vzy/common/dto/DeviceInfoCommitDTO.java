package com.vcredit.vzy.common.dto;

import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/18
 */
@Data
public class DeviceInfoCommitDTO {

    private String deviceName;
    private String deviceNo;
    private String deviceType;
    private String deviceBrand;
    private String deviceModel;
    private String deviceSystem;
    private String deviceSystemVersion;
    private String deviceLoginUser;
    private String deviceLoginKey;
    private String deviceImei;
    private String deviceSerialNo;
    private String deviceResolution;
    private String deviceColor;
    private String deviceSize;
    private String deviceAccessory;
    private String deviceRemark;
    private String commitPerson;

    private String loginUser;
    private String loginUserAcct;

}

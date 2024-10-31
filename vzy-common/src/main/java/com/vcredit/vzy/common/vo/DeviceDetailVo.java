package com.vcredit.vzy.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/18
 */
@Data
public class DeviceDetailVo {

    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 设备负责人
     */
    private String deviceCharge;
    /**
     * 设备负责人账号
     */
    private String deviceChargeAcct;
    /**
     * 到期日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate expireDate;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备编号
     */
    private String deviceNo;
    /**
     * 设备机型
     */
    private String deviceType;
    /**
     * 设备品牌
     */
    private String deviceBrand;
    /**
     * 设备型号
     */
    private String deviceModel;
    /**
     * 设备系统
     */
    private String deviceSystem;
    /**
     * 设备系统版本
     */
    private String deviceSystemVersion;
    /**
     * 设备用户名
     */
    private String deviceLoginUser;
    /**
     * 设备密码
     */
    private String deviceLoginKey;
    /**
     * 设备IMEI
     */
    private String deviceImei;
    /**
     * 设备序列号
     */
    private String deviceSerialNo;
    /**
     * 设备分辨率
     */
    private String deviceResolution;
    /**
     * 设备颜色
     */
    private String deviceColor;
    /**
     * 设备尺寸
     */
    private String deviceSize;
    /**
     * 设备配件
     */
    private String deviceAccessory;
    /**
     * 设备备注
     */
    private String deviceRemark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedTime;

}

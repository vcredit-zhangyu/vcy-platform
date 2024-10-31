package com.vcredit.vzy.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/16
 */
@Data
@TableName("t_device_detail")
public class DeviceDetailDO {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 设备ID
     */
    private Long deviceId;
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
    /**
     * 删除标识
     */
    private String delFlag;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 修改人
     */
    private String updatedBy;
    /**
     * 修改时间
     */
    private LocalDateTime updatedTime;
}

package com.vcredit.vzy.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/16
 */
@Data
@TableName("t_device")
public class DeviceDO {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备编号
     */
    private String deviceNo;
    /**
     * 设备状态
     */
    private String deviceStatus;
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
    private LocalDate expireDate;
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

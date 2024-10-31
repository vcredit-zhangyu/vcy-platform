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
@TableName("t_device_log")
public class DeviceLogDO {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备编号
     */
    private String deviceNo;
    /**
     * 操作人
     */
    private String operPerson;
    /**
     * 操作人账号
     */
    private String operPersonAcct;
    /**
     * 操作时间
     */
    private LocalDateTime operTime;
    /**
     * 操作详情
     */
    private String operDetail;

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

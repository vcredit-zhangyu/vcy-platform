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
@TableName("t_device_apply")
public class DeviceApplyDO {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 申请类型
     */
    private String applyType;
    /**
     * 申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 审核状态
     */
    private String applyStatus;
    /**
     * 申请人
     */
    private String applyPerson;
    /**
     * 申请人账号
     */
    private String applyPersonAcct;
    /**
     * 申请周期
     */
    private String applyPeriod;
    /**
     * 审核人
     */
    private String approvePerson;
    /**
     * 审核人账号
     */
    private String approvePersonAcct;
    /**
     * 审核时间
     */
    private LocalDateTime approveTime;
    /**
     * 审核结果
     */
    private String approveResult;
    /**
     * 审核原因
     */
    private String approveReason;
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

    private String applyReason;
}

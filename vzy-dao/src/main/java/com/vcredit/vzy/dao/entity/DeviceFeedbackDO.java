package com.vcredit.vzy.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("t_device_feedback")
@Data
public class DeviceFeedbackDO {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 反馈内容
     */
    private String content;
    /**
     * 处理结果
     */
    private String handleResult;
    /**
     * 删除标识
     */
    private String deletedFlag;
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

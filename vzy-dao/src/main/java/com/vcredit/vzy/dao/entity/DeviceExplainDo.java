package com.vcredit.vzy.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_apply_explain")
public class DeviceExplainDo {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 申请说明
     */
    private String explainContent;

    /**
     * 删除标识
     */
    private Integer deletedFlag;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
    private LocalDateTime updatedTime;
}

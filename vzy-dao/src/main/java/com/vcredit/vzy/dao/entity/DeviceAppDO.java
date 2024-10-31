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
@TableName("t_device_app")
@Data
public class DeviceAppDO {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 最近使用app
     */
    private String lastApp;
    /**
     * 使用app时间
     */
    private LocalDateTime appTime;
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

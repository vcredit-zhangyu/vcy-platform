package com.vcredit.vzy.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/5/27
 */
@TableName("t_device_app_version")
@Data
public class DeviceAppVersionDO {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * app名称
     */
    private String appName;
    /**
     * app平台
     */
    private String appPlatform;
    /**
     * app版本
     */
    private String appVersion;
    /**
     * 版本备注
     */
    private String versionRemark;
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

    private String downloadUrl;

}

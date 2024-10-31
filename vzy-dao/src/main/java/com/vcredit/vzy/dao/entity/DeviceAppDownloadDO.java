package com.vcredit.vzy.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("t_app_download")
@Data
public class DeviceAppDownloadDO {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 机型
     */
    private String type;
    /**
     * 版本
     */
    private String version;
    /**
     * 下载地址
     */
    private String downloadUrl;
    /**
     * 下载地址
     */
    private String environment;
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

package com.vcredit.vzy.common.vo;

import lombok.Data;

@Data
public class DeviceDownLoadVo {
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
}

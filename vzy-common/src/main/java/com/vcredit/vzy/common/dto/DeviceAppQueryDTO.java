package com.vcredit.vzy.common.dto;

import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/18
 */
@Data
public class DeviceAppQueryDTO {

    private Integer currentPage;
    private Integer pageSize;
    private String deviceNo;
    private String deviceType;
    private String app;

}

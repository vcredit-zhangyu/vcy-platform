package com.vcredit.vzy.common.dto;

import java.util.List;
import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/18
 */
@Data
public class DevicePageQueryDTO {
    private Integer currentPage;
    private Integer pageSize;

    private String deviceType;
    private String deviceName;
    private String deviceNo;
    private String deviceCharge;
    private String deviceStatus;

    private List<String> deviceTypeList;
    private List<String> deviceStatusList;
}

package com.vcredit.vzy.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class DeviceReportVo {
    //品牌
    private List<DevcieBrandVo> brandDis;
    //版本
    private DeviceVersionDis versionDis;
    //型号
    private List<DeviceModelVo> modelDis;

}

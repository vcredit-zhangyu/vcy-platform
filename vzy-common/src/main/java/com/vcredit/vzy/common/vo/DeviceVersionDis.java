package com.vcredit.vzy.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class DeviceVersionDis {
    private List<DeviceModelVo> iosModelList;
    private List<DeviceModelVo> androidModelList;
    private List<DeviceModelVo> hmModelList;
}

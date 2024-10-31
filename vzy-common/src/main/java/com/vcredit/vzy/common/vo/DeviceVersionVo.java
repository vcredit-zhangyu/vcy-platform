package com.vcredit.vzy.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class DeviceVersionVo {

    List<DeviceModelVo> iosModelList;
    List<DeviceModelVo> androidModelList;
    List<DeviceModelVo> hmModelList;
}

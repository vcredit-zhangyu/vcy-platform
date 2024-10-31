package com.vcredit.vzy.common.dto;

import com.vcredit.vzy.common.vo.DeviceDownLoadVo;
import lombok.Data;

import java.util.List;

@Data
public class DeviceAppDownloadResDTO {
    List<DeviceDownLoadVo> testEvInfo;
    List<DeviceDownLoadVo> prodEvInfo;
}

package com.vcredit.vzy.service.device;

import com.vcredit.vzy.common.dto.DeviceLogQueryDTO;
import com.vcredit.vzy.common.dto.Pagination;
import com.vcredit.vzy.common.vo.DeviceLogVo;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
public interface DeviceLogService {

    Pagination<DeviceLogVo> deviceLogPageQuery(DeviceLogQueryDTO deviceLogQueryDTO);
}

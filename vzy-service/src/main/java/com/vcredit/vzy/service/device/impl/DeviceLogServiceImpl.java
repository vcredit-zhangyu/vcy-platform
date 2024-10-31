package com.vcredit.vzy.service.device.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vcredit.vzy.common.dto.DeviceLogQueryDTO;
import com.vcredit.vzy.common.dto.Pagination;
import com.vcredit.vzy.common.vo.DeviceLogVo;
import com.vcredit.vzy.common.vo.DevicePageVo;
import com.vcredit.vzy.dao.repository.DeviceLogRepository;
import com.vcredit.vzy.dao.util.PageUtil;
import com.vcredit.vzy.service.device.DeviceLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceLogServiceImpl implements DeviceLogService {

    private final DeviceLogRepository deviceLogRepository;

    @Override
    public Pagination<DeviceLogVo> deviceLogPageQuery(DeviceLogQueryDTO deviceLogQueryDTO) {
        IPage<DeviceLogVo> tempResult = deviceLogRepository.deviceInfoPageQuery(deviceLogQueryDTO);
        return PageUtil.convertPagination(tempResult);
    }


}

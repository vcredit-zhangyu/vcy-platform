package com.vcredit.vzy.job.service.impl;

import com.vcredit.vzy.common.enums.DeviceStatusEnum;
import com.vcredit.vzy.common.enums.OperPersonEnum;
import com.vcredit.vzy.dao.entity.DeviceDO;
import com.vcredit.vzy.dao.repository.DeviceRepository;
import com.vcredit.vzy.job.service.DeviceExpireService;
import com.vcredit.vzy.service.device.DeviceService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/5/14
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceExpireServiceImpl implements DeviceExpireService {

    private final DeviceRepository deviceRepository;

    @Override
    public void executeExpire() {
        //查询使用中的设备，判断设备是否到期，到期则更新设备状态为待归还
        List<DeviceDO> deviceDOList = deviceRepository.queryByStatus(DeviceStatusEnum.USING.getCode());
        if (deviceDOList.isEmpty()) {
            return;
        }
        for (DeviceDO deviceDO : deviceDOList) {
            if (deviceDO.getExpireDate() == null) {
                continue;
            }
            if (deviceDO.getExpireDate().isBefore(LocalDate.now())) {
                deviceRepository.deviceStatusUpdate(deviceDO.getId(), DeviceStatusEnum.WAIT_RETURN.getCode(), OperPersonEnum.SYSTEM.getCode());
            }
        }
    }
}

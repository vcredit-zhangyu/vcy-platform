package com.vcredit.vzy.job.job;

import com.vcredit.vzy.job.service.DeviceUpdateRemindService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * description :设备更新提醒 设备更新时间距离当前时间大于3个月（时间可配置），给负责人、管理员发送钉钉消息，提醒打开app，更新设备信息
 *
 * @author : zhanghui
 * @date : 2024/5/13
 */
@Slf4j
@Service
@JobHandler(value = "DeviceUpdateRemindJob")
@RequiredArgsConstructor
public class DeviceUpdateRemindJob extends IJobHandler {

    private final DeviceUpdateRemindService deviceUpdateRemindService;

    @Override
    public ReturnT<String> execute(String s) {
        deviceUpdateRemindService.executeRemind();
        log.info("DeviceUpdateRemindJob execute success");
        return ReturnT.SUCCESS;
    }
}

package com.vcredit.vzy.job.job;

import com.vcredit.vzy.job.service.DeviceExpireService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * description : 设备到期更新状态为待归还
 *
 * @author : zhanghui
 * @date : 2024/5/14
 */
@Slf4j
@Service
@JobHandler(value = "DeviceExpireJob")
@RequiredArgsConstructor
public class DeviceExpireJob extends IJobHandler {

    private final DeviceExpireService deviceExpireService;

    @Override
    public ReturnT<String> execute(String s) {
        deviceExpireService.executeExpire();
        log.info("DeviceExpireJob execute success");
        return ReturnT.SUCCESS;
    }

}

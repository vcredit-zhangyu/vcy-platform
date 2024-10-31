package com.vcredit.vzy.job.job;

import com.vcredit.vzy.job.service.DeviceBorrowExpireRemindService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * description : 借用时间到期前两日给管理员和负责人（使用人）发送借用即将过期提醒（钉钉消息）
 *
 * @author : zhanghui
 * @date : 2024/5/13
 */
@Slf4j
@Service
@JobHandler(value = "DeviceBorrowExpireRemindJob")
@RequiredArgsConstructor
public class DeviceBorrowExpireRemindJob extends IJobHandler {

    private final DeviceBorrowExpireRemindService deviceBorrowExpireRemindService;

    @Override
    public ReturnT<String> execute(String s) {
        deviceBorrowExpireRemindService.expireRemind();
        log.info("DeviceBorrowExpireRemindJob execute success");
        return ReturnT.SUCCESS;
    }
}

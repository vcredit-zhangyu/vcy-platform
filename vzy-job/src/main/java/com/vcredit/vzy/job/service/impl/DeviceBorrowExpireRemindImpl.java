package com.vcredit.vzy.job.service.impl;

import com.vcredit.genc.dingtalk.message.RobotCustomMessage;
import com.vcredit.genc.dingtalk.utils.DingTalkUtil;
import com.vcredit.vzy.common.enums.DeviceStatusEnum;
import com.vcredit.vzy.dao.entity.DeviceDO;
import com.vcredit.vzy.job.service.DeviceBorrowExpireRemindService;
import com.vcredit.vzy.service.device.DeviceService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * description : 借用时间到期前两日给管理员和负责人（使用人）发送借用即将过期提醒（钉钉消息）
 *
 * @author : zhanghui
 * @date : 2024/5/13
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceBorrowExpireRemindImpl implements DeviceBorrowExpireRemindService {

    private final DeviceService deviceService;

    @Value("${device.remind.borrowUnit:day}")
    private String updateUnit;
    @Value("${device.remind.borrowInterval:2}")
    private Integer updateInterval;
    @Value("${device.remind.token}")
    private String dingToken;
    @Value("${device.adminMobile}")
    private String adminMobile;

    @Override
    public void expireRemind() {
        LocalDate remindDate = LocalDate.now().plusDays(updateInterval);
        if ("year".equals(updateUnit)) {
            remindDate = LocalDate.now().plusYears(updateInterval);
        } else if ("month".equals(updateUnit)) {
            remindDate = LocalDate.now().plusMonths(updateInterval);
        }
        //查询使用中的设备
        List<DeviceDO> deviceDOList = deviceService.queryByStatus(DeviceStatusEnum.USING.getCode());
        if (deviceDOList.isEmpty()) {
            log.info("没有查询到使用中的设备");
            return;
        }
        StringBuilder deviceMsg = new StringBuilder();
        for (DeviceDO deviceDO : deviceDOList) {
            if (deviceDO.getExpireDate() == null) {
                continue;
            }
            if (!deviceDO.getExpireDate().isAfter(remindDate)) {
                deviceMsg.append("设备号：").append(deviceDO.getDeviceNo()).append("，设备名称：").append(deviceDO.getDeviceName()).append("，到期日：")
                    .append(deviceDO.getExpireDate()).append("<br/>");
            }
        }
        if (deviceMsg.length() == 0) {
            log.info("没有查询到即将到期的设备");
            return;
        }
        Set<String> atSet = deviceDOList.stream().map(DeviceDO::getDeviceCharge).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        List<String> notifyMobiles = new ArrayList<>(Arrays.asList(adminMobile.split(",")));
        String atAdmin = notifyMobiles.stream().map(s -> "@" + s).collect(Collectors.joining(" "));
        String atMsg = atSet.stream().map(s -> "@" + s).collect(Collectors.joining(" "));
        String title = "借用即将过期提醒";
        String content = atAdmin+" <br/>"+  atMsg + "<br/>设备借用即将过期提醒<br/>" + deviceMsg;
        // 拼接参数
        RobotCustomMessage message = RobotCustomMessage.builder().accessToken(dingToken).msgType("markdown").notifyMobiles(notifyMobiles)
            .markdown(RobotCustomMessage.Markdown.builder().title(title).text(content).build()).build();

        //自定义机器人发送群消息
        DingTalkUtil.sendCustomRobotMessage(message);
        log.info("设备借用即将过期提醒发送成功");

    }
}

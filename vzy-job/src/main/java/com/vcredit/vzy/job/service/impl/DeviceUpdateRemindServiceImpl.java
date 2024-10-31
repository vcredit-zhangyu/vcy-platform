package com.vcredit.vzy.job.service.impl;

import com.vcredit.genc.dingtalk.message.RobotCustomMessage;
import com.vcredit.genc.dingtalk.utils.DingTalkUtil;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import com.vcredit.vzy.dao.repository.DeviceAppRepository;
import com.vcredit.vzy.job.service.DeviceUpdateRemindService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * description : 设备更新时间距离当前时间大于3个月（时间可配置），给负责人、管理员发送钉钉消息，提醒打开app，更新设备信息
 *
 * @author : zhanghui
 * @date : 2024/5/13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceUpdateRemindServiceImpl implements DeviceUpdateRemindService {

    @Value("${device.remind.updateUnit:month}")
    private String updateUnit;
    @Value("${device.remind.updateInterval:3}")
    private Integer updateInterval;
    @Value("${device.remind.token}")
    private String dingToken;
    @Value("${device.adminMobile}")
    private String adminMobile;

    private final DeviceAppRepository deviceAppRepository;

    @Override
    public void executeRemind() {
        LocalDate updateRemindDate = LocalDate.now().minusMonths(updateInterval);
        String unit = "月";
        if ("year".equals(updateUnit)) {
            updateRemindDate = LocalDate.now().minusYears(updateInterval);
            unit = "年";
        } else if ("day".equals(updateUnit)) {
            updateRemindDate = LocalDate.now().minusDays(updateInterval);
            unit = "天";
        }

        //查询设备更新时间在这之前的，排除abandoned状态的设备 只提醒设备状态为使用中记录
        String updateRemindTime = updateRemindDate.atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<DeviceAppVo> deviceAppVoList = deviceAppRepository.queryUpdateRemindList(updateRemindTime);
        if (CollectionUtils.isEmpty(deviceAppVoList)) {
            log.info("没有需要提醒的设备");
            return;
        }
        StringBuilder deviceMsg = new StringBuilder();
        for (DeviceAppVo deviceAppVo : deviceAppVoList) {
            deviceMsg.append("设备号：").append(deviceAppVo.getDeviceNo()).append("，设备名称：").append(deviceAppVo.getDeviceName()).append("<br/>");
        }
        Set<String> atSet = deviceAppVoList.stream().map(DeviceAppVo::getDeviceCharge).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        List<String> notifyMobiles = new ArrayList<>(Arrays.asList(adminMobile.split(",")));
        String atAdmin = notifyMobiles.stream().map(s -> "@" + s).collect(Collectors.joining(" "));
        String atMsg = atSet.stream().map(s -> "@" + s).collect(Collectors.joining(" "));
        String title = "设备更新提醒";
        String content = atAdmin+" <br/>"+ atMsg + " <br/>以下设备更新时间距离当前时间大于" + updateInterval + unit + "，请及时更新设备信息：<br/>" + deviceMsg;
        // 拼接参数
        RobotCustomMessage message = RobotCustomMessage.builder().accessToken(dingToken).msgType("markdown").notifyMobiles(notifyMobiles)
            .markdown(RobotCustomMessage.Markdown.builder().title(title).text(content).build()).build();
        //自定义机器人发送群消息
        DingTalkUtil.sendCustomRobotMessage(message);
        log.info("已发送设备更新提醒消息");

    }

}

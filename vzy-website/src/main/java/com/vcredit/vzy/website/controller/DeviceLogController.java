package com.vcredit.vzy.website.controller;

import com.vcredit.vzy.common.dto.ApiResponse;
import com.vcredit.vzy.common.dto.DeviceLogQueryDTO;
import com.vcredit.vzy.common.dto.Pagination;
import com.vcredit.vzy.common.vo.DeviceLogVo;
import com.vcredit.vzy.service.device.DeviceLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device")
public class DeviceLogController {

    private final DeviceLogService deviceLogService;

    @GetMapping("/log/pageQuery")
    public ApiResponse<Pagination<DeviceLogVo>> deviceLogPageQuery(DeviceLogQueryDTO deviceLogQueryDTO) {
        return ApiResponse.success(deviceLogService.deviceLogPageQuery(deviceLogQueryDTO));
    }

}

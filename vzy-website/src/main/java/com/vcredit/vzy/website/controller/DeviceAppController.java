package com.vcredit.vzy.website.controller;

import com.vcredit.vzy.common.dto.*;
import com.vcredit.vzy.common.enums.BizErrorEnum;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import com.vcredit.vzy.common.vo.DeviceInfoVo;
import com.vcredit.vzy.service.device.DeviceAppService;
import com.vcredit.vzy.service.device.DeviceService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class DeviceAppController {

    private final DeviceService deviceService;
    private final DeviceAppService deviceAppService;


    /**
     * 设备使用记录提交
     */
    @PostMapping(value = "/app/commit")
    public ApiResponse<String> deviceAppCommit(@RequestBody List<DeviceAppCommitDTO> deviceAppCommitList) {
        deviceAppService.deviceAppCommit(deviceAppCommitList);
        return ApiResponse.success("设备使用记录提交成功");
    }

    /**
     * 设备信息查询
     */
    @GetMapping(value = "/info/query")
    public ApiResponse<DeviceInfoVo> deviceInfoQuery(@RequestParam("deviceNo") String deviceNo) {
        DeviceInfoVo deviceInfoVo = deviceService.queryByDeviceNo(deviceNo);
        if (Objects.nonNull(deviceInfoVo)){
            return ApiResponse.success(deviceInfoVo);
        }else {
            return ApiResponse.error(BizErrorEnum.DEVICE_NOT_EXIST);
        }
    }

    /**
     * 设备app使用记录PageQuery
     */
    @GetMapping(value = "/app/pageQuery")
    public ApiResponse<Pagination<DeviceAppVo>> deviceAppPageQuery(DeviceAppQueryDTO queryDTO) {
        return ApiResponse.success(deviceAppService.deviceAppPageQuery(queryDTO));
    }

    /**
     * 设备app使用记录PageQuery
     */
    @PostMapping(value = "/app/download/query")
    public ApiResponse<DeviceAppDownloadResDTO> deviceAppDownLoadQuery(@RequestBody DeviceAppDownloadReq deviceAppDownloadReq) {
        return ApiResponse.success(deviceAppService.deviceAppDownLoadQuery(deviceAppDownloadReq));
    }

}

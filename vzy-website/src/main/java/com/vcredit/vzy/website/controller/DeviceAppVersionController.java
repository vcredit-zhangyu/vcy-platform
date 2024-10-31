package com.vcredit.vzy.website.controller;

import com.alibaba.fastjson.JSONObject;
import com.vcredit.vzy.common.dto.ApiResponse;
import com.vcredit.vzy.common.dto.AppVersionCommitDTO;
import com.vcredit.vzy.common.dto.AppVersionPageQueryDTO;
import com.vcredit.vzy.common.dto.Pagination;
import com.vcredit.vzy.common.vo.AppVersionPageVo;
import com.vcredit.vzy.dao.entity.DeviceAppVersionDO;
import com.vcredit.vzy.service.device.DeviceAppVersionService;
import com.vcredit.vzy.website.vo.UserContext;
import com.vcredit.vzy.website.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @date : 2024/5/27
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/app")
public class DeviceAppVersionController {

    private final DeviceAppVersionService deviceAppVersionService;

    /**
     * 版本管理新增
     */
    @PostMapping(value = "/version/commit")
    public ApiResponse<String> appVersionCommit(@RequestBody AppVersionCommitDTO versionCommitDTO) {
        try {
            String userInfo = UserContext.getCurrentUserInfo();
            UserVO oauth2UserVO = JSONObject.parseObject(userInfo, UserVO.class);
            versionCommitDTO.setLoginUser(oauth2UserVO.getUserName());
            versionCommitDTO.setLoginUserAcct(oauth2UserVO.getUserAccount());
            String result = deviceAppVersionService.appVersionCommit(versionCommitDTO);
            if (StringUtils.isNotBlank(result)) {
                return ApiResponse.error(result);
            }
            return ApiResponse.success("版本信息提交成功");
        } catch (Exception e) {
            log.error("版本信息提交失败", e);
            return ApiResponse.error("系统异常");
        }
    }

    /**
     * 版本管理新增
     */
    @PostMapping(value = "/version/update")
    public ApiResponse<String> appVersionUpdate(@RequestBody AppVersionCommitDTO versionCommitDTO) {
        String userInfo = UserContext.getCurrentUserInfo();
        UserVO oauth2UserVO = JSONObject.parseObject(userInfo, UserVO.class);
        versionCommitDTO.setLoginUser(oauth2UserVO.getUserName());
        versionCommitDTO.setLoginUserAcct(oauth2UserVO.getUserAccount());
        String result = deviceAppVersionService.appVersionUpdate(versionCommitDTO);
        if (StringUtils.isNotBlank(result)) {
            return ApiResponse.error(result);
        }
        return ApiResponse.success("success");
    }

    @GetMapping("/version/pageQuery")
    public ApiResponse<Pagination<AppVersionPageVo>> appVersionPageQuery(AppVersionPageQueryDTO appVersionPageQueryDTO) {
        return ApiResponse.success(deviceAppVersionService.appVersionPageQuery(appVersionPageQueryDTO));
    }

    @GetMapping("/version/query")
    public ApiResponse<AppVersionPageVo> queryByPlatformAndApp(@RequestParam("appPlatform") String appPlatform,
        @RequestParam(value = "appName", defaultValue = "vzy") String appName) {
        if (StringUtils.isBlank(appPlatform)) {
            return ApiResponse.success();
        }
        return ApiResponse.success(deviceAppVersionService.queryByPlatformAndApp(appPlatform, appName));
    }

    @GetMapping("/version/queryByVersionId")
    public ApiResponse <DeviceAppVersionDO> queryDetailByVersionId(@RequestParam("versionId") String versionId) {
        if (StringUtils.isBlank(versionId)) {
            return ApiResponse.success();
        }
        return ApiResponse.success(deviceAppVersionService.queryDetailByVersionId(versionId));
    }


}

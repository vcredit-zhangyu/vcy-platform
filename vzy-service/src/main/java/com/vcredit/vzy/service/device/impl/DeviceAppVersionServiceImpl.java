package com.vcredit.vzy.service.device.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vcredit.vzy.common.dto.AppVersionCommitDTO;
import com.vcredit.vzy.common.dto.AppVersionPageQueryDTO;
import com.vcredit.vzy.common.dto.Pagination;
import com.vcredit.vzy.common.utils.Constants;
import com.vcredit.vzy.common.vo.AppVersionPageVo;
import com.vcredit.vzy.dao.entity.DeviceAppVersionDO;
import com.vcredit.vzy.dao.repository.DeviceAppVersionRepository;
import com.vcredit.vzy.dao.util.PageUtil;
import com.vcredit.vzy.service.device.DeviceAppVersionService;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/5/27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceAppVersionServiceImpl implements DeviceAppVersionService {

    private final DeviceAppVersionRepository deviceAppVersionRepository;

    @Override
    public String appVersionCommit(AppVersionCommitDTO versionCommitDTO) {
        //平台唯一性校验，已有相同平台，不允许提交
        DeviceAppVersionDO existDO = deviceAppVersionRepository.findOneByAppPlatform(versionCommitDTO.getAppPlatform());
        if (existDO != null) {
            return "该平台已存在版本信息，不允许重复提交";
        }
        DeviceAppVersionDO deviceAppVersionDO = new DeviceAppVersionDO();
        //默认掌御app
        deviceAppVersionDO.setAppName("vzy");
        deviceAppVersionDO.setAppPlatform(versionCommitDTO.getAppPlatform());
        deviceAppVersionDO.setAppVersion(versionCommitDTO.getAppVersion());
        deviceAppVersionDO.setVersionRemark(versionCommitDTO.getVersionRemark());
        deviceAppVersionDO.setDelFlag(Constants.N);
        deviceAppVersionDO.setCreatedTime(LocalDateTime.now());
        deviceAppVersionDO.setCreatedBy(versionCommitDTO.getLoginUserAcct());
        deviceAppVersionDO.setUpdatedBy(versionCommitDTO.getLoginUserAcct());
        deviceAppVersionDO.setUpdatedTime(LocalDateTime.now());
        deviceAppVersionDO.setDownloadUrl(versionCommitDTO.getDownloadUrl());
        deviceAppVersionRepository.save(deviceAppVersionDO);
        return StringUtils.EMPTY;
    }

    @Override
    public String appVersionUpdate(AppVersionCommitDTO versionCommitDTO) {
        //平台唯一性校验，已有相同平台，不允许提交
        DeviceAppVersionDO existDO = deviceAppVersionRepository.findOneByAppPlatform(versionCommitDTO.getAppPlatform());
        if (existDO != null && !Objects.equals(existDO.getId(), versionCommitDTO.getVersionId())) {
            return "该平台已存在版本信息，不允许重复提交";
        }
        DeviceAppVersionDO deviceAppVersionDO = new DeviceAppVersionDO();
        deviceAppVersionDO.setId(versionCommitDTO.getVersionId());
        deviceAppVersionDO.setAppPlatform(versionCommitDTO.getAppPlatform());
        deviceAppVersionDO.setAppVersion(versionCommitDTO.getAppVersion());
        deviceAppVersionDO.setVersionRemark(versionCommitDTO.getVersionRemark());
        deviceAppVersionDO.setUpdatedBy(versionCommitDTO.getLoginUserAcct());
        deviceAppVersionDO.setDownloadUrl(versionCommitDTO.getDownloadUrl());
        deviceAppVersionRepository.updateByVersionId(deviceAppVersionDO);
        return StringUtils.EMPTY;
    }

    @Override
    public Pagination<AppVersionPageVo> appVersionPageQuery(AppVersionPageQueryDTO queryDTO) {
        IPage<AppVersionPageVo> tempResult = deviceAppVersionRepository.appVersionPageQuery(queryDTO);
        return PageUtil.convertPagination(tempResult);
    }

    @Override
    public AppVersionPageVo queryByPlatformAndApp(String appPlatform, String appName) {
        return deviceAppVersionRepository.queryByPlatformAndApp(appPlatform, appName);
    }

    @Override
    public DeviceAppVersionDO queryDetailByVersionId(String versionId) {
        return deviceAppVersionRepository.getById(Long.valueOf(versionId));
    }
}

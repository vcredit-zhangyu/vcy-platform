package com.vcredit.vzy.service.device;

import com.vcredit.vzy.common.dto.AppVersionCommitDTO;
import com.vcredit.vzy.common.dto.AppVersionPageQueryDTO;
import com.vcredit.vzy.common.dto.Pagination;
import com.vcredit.vzy.common.vo.AppVersionPageVo;
import com.vcredit.vzy.dao.entity.DeviceAppVersionDO;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/5/27
 */
public interface DeviceAppVersionService {

    String appVersionCommit(AppVersionCommitDTO versionCommitDTO);

    String appVersionUpdate(AppVersionCommitDTO versionCommitDTO);

    Pagination<AppVersionPageVo> appVersionPageQuery(AppVersionPageQueryDTO appVersionPageQueryDTO);

    AppVersionPageVo queryByPlatformAndApp(String appPlatform, String appName);

    DeviceAppVersionDO queryDetailByVersionId(String versionId);
}

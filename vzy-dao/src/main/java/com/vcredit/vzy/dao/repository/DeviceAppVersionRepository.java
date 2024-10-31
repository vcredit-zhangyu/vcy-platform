package com.vcredit.vzy.dao.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vcredit.pluginconfig.bean.base.BaseRepository;
import com.vcredit.vzy.common.dto.AppVersionPageQueryDTO;
import com.vcredit.vzy.common.vo.AppVersionPageVo;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import com.vcredit.vzy.dao.entity.DeviceAppVersionDO;
import com.vcredit.vzy.dao.mapper.DeviceAppVersionMapper;
import org.springframework.stereotype.Repository;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/5/27
 */
@Repository
public class DeviceAppVersionRepository extends BaseRepository<DeviceAppVersionMapper, DeviceAppVersionDO> {

    public DeviceAppVersionDO findOneByAppPlatform(String appPlatform) {
        return baseMapper.findOneByAppPlatform(appPlatform);
    }

    public void updateByVersionId(DeviceAppVersionDO deviceAppVersionDO) {
        baseMapper.updateByVersionId(deviceAppVersionDO);
    }

    public IPage<AppVersionPageVo> appVersionPageQuery(AppVersionPageQueryDTO queryDTO) {
        Page<DeviceAppVo> page = new Page<>(queryDTO.getCurrentPage(), queryDTO.getPageSize());
        return baseMapper.appVersionPageQuery(page,queryDTO);
    }

    public AppVersionPageVo queryByPlatformAndApp(String appPlatform, String appName) {
        return baseMapper.queryByPlatformAndApp(appPlatform, appName);
    }
}

package com.vcredit.vzy.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vcredit.pluginconfig.bean.base.BaseMapper;
import com.vcredit.vzy.common.dto.AppVersionPageQueryDTO;
import com.vcredit.vzy.common.vo.AppVersionPageVo;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import com.vcredit.vzy.dao.entity.DeviceAppVersionDO;
import org.apache.ibatis.annotations.Param;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/5/27
 */
public interface DeviceAppVersionMapper extends BaseMapper<DeviceAppVersionDO> {

    DeviceAppVersionDO findOneByAppPlatform(@Param("appPlatform") String appPlatform);

    void updateByVersionId(DeviceAppVersionDO deviceAppVersionDO);

    IPage<AppVersionPageVo> appVersionPageQuery(Page<DeviceAppVo> page, @Param("queryDTO") AppVersionPageQueryDTO queryDTO);

    AppVersionPageVo queryByPlatformAndApp(@Param("appPlatform") String appPlatform, @Param("appName") String appName);
}

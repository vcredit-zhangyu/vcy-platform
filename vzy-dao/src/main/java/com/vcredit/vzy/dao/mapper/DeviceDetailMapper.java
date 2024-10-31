package com.vcredit.vzy.dao.mapper;

import com.vcredit.pluginconfig.bean.base.BaseMapper;
import com.vcredit.vzy.dao.entity.DeviceDetailDO;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
public interface DeviceDetailMapper extends BaseMapper<DeviceDetailDO> {

    DeviceDetailDO queryByDeviceId(Long deviceId);

    void updateByDeviceId(DeviceDetailDO deviceDetailDO);
}

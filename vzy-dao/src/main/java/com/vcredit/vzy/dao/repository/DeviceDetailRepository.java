package com.vcredit.vzy.dao.repository;

import com.vcredit.pluginconfig.bean.base.BaseRepository;
import com.vcredit.vzy.dao.entity.DeviceDetailDO;
import com.vcredit.vzy.dao.mapper.DeviceDetailMapper;
import org.springframework.stereotype.Repository;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
@Repository
public class DeviceDetailRepository extends BaseRepository<DeviceDetailMapper, DeviceDetailDO> {

    public DeviceDetailDO queryByDeviceId(Long id) {
        return baseMapper.queryByDeviceId(id);
    }

    public void updateByDeviceId(DeviceDetailDO deviceDetailDO) {
        baseMapper.updateByDeviceId(deviceDetailDO);
    }
}

package com.vcredit.vzy.dao.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vcredit.pluginconfig.bean.base.BaseRepository;
import com.vcredit.vzy.common.dto.DeviceAppDownloadReq;
import com.vcredit.vzy.common.dto.DeviceAppQueryDTO;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import com.vcredit.vzy.dao.entity.DeviceAppDO;
import com.vcredit.vzy.dao.entity.DeviceAppDownloadDO;
import com.vcredit.vzy.dao.mapper.DeviceAppMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
@Repository
public class DeviceAppRepository extends BaseRepository<DeviceAppMapper, DeviceAppDO> {

    public DeviceAppDO queryByDeviceIdAndApp(Long deviceId, String app) {
        return baseMapper.queryByDeviceIdAndApp(deviceId, app);
    }

    public IPage<DeviceAppVo> deviceAppPageQuery(DeviceAppQueryDTO queryDTO) {
        Page<DeviceAppVo> page = new Page<>(queryDTO.getCurrentPage(), queryDTO.getPageSize());
        return baseMapper.deviceAppPageQuery(page, queryDTO);
    }

    public List<DeviceAppVo> queryUpdateRemindList(String updateRemindTime) {
        return baseMapper.queryUpdateRemindList(updateRemindTime);
    }

    public List<DeviceAppDownloadDO> deviceAppDownLoadQuery(DeviceAppDownloadReq deviceAppDownloadReq) {
        return baseMapper.deviceAppDownLoadQuery(deviceAppDownloadReq);
    }
}

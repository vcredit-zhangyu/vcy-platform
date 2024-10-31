package com.vcredit.vzy.dao.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vcredit.pluginconfig.bean.base.BaseRepository;
import com.vcredit.vzy.common.dto.DeviceLogQueryDTO;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import com.vcredit.vzy.common.vo.DeviceLogVo;
import com.vcredit.vzy.dao.entity.DeviceLogDO;
import com.vcredit.vzy.dao.mapper.DeviceLogMapper;
import org.springframework.stereotype.Repository;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
@Repository
public class DeviceLogRepository extends BaseRepository<DeviceLogMapper, DeviceLogDO> {

    public IPage<DeviceLogVo> deviceInfoPageQuery(DeviceLogQueryDTO queryDTO) {
        Page<DeviceAppVo> page = new Page<>(queryDTO.getCurrentPage(), queryDTO.getPageSize());
        return baseMapper.deviceInfoPageQuery(page,queryDTO);
    }
}

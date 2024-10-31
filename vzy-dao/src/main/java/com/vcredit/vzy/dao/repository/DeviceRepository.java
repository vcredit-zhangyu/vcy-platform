package com.vcredit.vzy.dao.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vcredit.pluginconfig.bean.base.BaseRepository;
import com.vcredit.vzy.common.dto.DeviceFeedbackQueryDTO;
import com.vcredit.vzy.common.dto.DevicePageQueryDTO;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import com.vcredit.vzy.common.vo.DeviceDetailVo;
import com.vcredit.vzy.common.vo.DeviceFeedbackVo;
import com.vcredit.vzy.common.vo.DevicePageVo;
import com.vcredit.vzy.dao.entity.DeviceDO;
import com.vcredit.vzy.dao.mapper.DeviceMapper;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
@Repository
public class DeviceRepository extends BaseRepository<DeviceMapper, DeviceDO> {

    public DeviceDO queryByDeviceNo(String deviceNo) {
        return baseMapper.queryByDeviceNo(deviceNo);
    }


    public IPage<DevicePageVo> deviceInfoPageQuery(DevicePageQueryDTO queryDTO) {
        Page<DeviceAppVo> page = new Page<>(queryDTO.getCurrentPage(), queryDTO.getPageSize());
        return baseMapper.deviceInfoPageQuery(page, queryDTO);
    }

    public DeviceDetailVo deviceInfoDetail(Long deviceId) {
        return baseMapper.deviceInfoDetail(deviceId);
    }

    public void deviceStatusUpdate(Long deviceId, String deviceStatus, String updatedBy) {
        baseMapper.deviceStatusUpdate(deviceId, deviceStatus, updatedBy);
    }

    public void deviceApprove(DeviceDO deviceDO) {
        baseMapper.deviceApprove(deviceDO);
    }

    public void updateDeviceInfo(DeviceDO deviceDO) {
        baseMapper.updateDeviceInfo(deviceDO);
    }

    public List<DeviceDO> queryUpdateRemindList(String updateRemindTime) {
        return baseMapper.queryUpdateRemindList(updateRemindTime);
    }

    public List<DeviceDO> queryByStatus(String deviceStatus) {
        return baseMapper.selectList(new QueryWrapper<DeviceDO>().eq("device_status", deviceStatus));
    }

    public IPage<DeviceFeedbackVo> deviceFeedbackPageQuery(DeviceFeedbackQueryDTO queryDTO) {
        Page<DeviceFeedbackVo> page = new Page<>(queryDTO.getCurrentPage(), queryDTO.getPageSize());
        return baseMapper.deviceFeedbackPageQuery(page, queryDTO);
    }
}

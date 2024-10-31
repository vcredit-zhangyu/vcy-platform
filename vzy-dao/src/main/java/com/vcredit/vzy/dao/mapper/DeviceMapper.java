package com.vcredit.vzy.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vcredit.pluginconfig.bean.base.BaseMapper;
import com.vcredit.vzy.common.dto.DeviceFeedbackQueryDTO;
import com.vcredit.vzy.common.dto.DevicePageQueryDTO;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import com.vcredit.vzy.common.vo.DeviceDetailVo;
import com.vcredit.vzy.common.vo.DeviceFeedbackVo;
import com.vcredit.vzy.common.vo.DevicePageVo;
import com.vcredit.vzy.dao.entity.DeviceDO;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/16
 */
public interface DeviceMapper extends BaseMapper<DeviceDO> {

    DeviceDO queryByDeviceNo(@Param("deviceNo") String deviceNo);

    IPage<DevicePageVo> deviceInfoPageQuery(Page<DeviceAppVo> page, @Param("queryDTO") DevicePageQueryDTO queryDTO);

    DeviceDetailVo deviceInfoDetail(Long deviceId);

    void deviceStatusUpdate(@Param("deviceId")Long deviceId, @Param("deviceStatus")String deviceStatus,@Param("updatedBy")String updatedBy);

    void deviceApprove(DeviceDO deviceDO);

    void updateDeviceInfo(DeviceDO deviceDO);

    List<DeviceDO> queryUpdateRemindList(@Param("updateRemindTime")String updateRemindTime);

    IPage<DeviceFeedbackVo> deviceFeedbackPageQuery(Page<DeviceFeedbackVo> page, @Param("queryDTO") DeviceFeedbackQueryDTO queryDTO);
}

package com.vcredit.vzy.service.device;

import com.vcredit.vzy.common.dto.*;
import com.vcredit.vzy.common.vo.*;
import com.vcredit.vzy.dao.entity.DeviceDO;
import java.time.LocalDateTime;
import java.util.List;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
public interface DeviceService {

    String deviceInfoCommit(DeviceInfoCommitDTO deviceInfo);

    DeviceInfoVo queryByDeviceNo(String deviceNo);

    List<EnumsResponseVo> enumsQuery();

    Pagination<DevicePageVo> deviceInfoPageQuery(DevicePageQueryDTO devicePageQueryDTO);

    DeviceDetailVo deviceInfoDetail(Long deviceId);

    void deviceStatusUpdate(DeviceInfoDTO deviceInfoDTO);

    void deviceInfoUpdate(DeviceInfoDTO deviceInfoDTO);

    List<DeviceDO> queryUpdateRemindList(String updateRemindTime);

    List<DeviceDO> queryByStatus(String deviceStatus);

    void feedbackCommit(DeviceFeedbackAddDTO deviceFeedbackAddDTO);

    Pagination<DeviceFeedbackVo> deviceFeedbackPageQuery(DeviceFeedbackQueryDTO deviceFeedbackQueryDTO);
}

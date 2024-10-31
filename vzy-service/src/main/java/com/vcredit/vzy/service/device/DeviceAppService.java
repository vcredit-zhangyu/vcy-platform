package com.vcredit.vzy.service.device;

import com.vcredit.vzy.common.dto.*;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import java.util.List;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
public interface DeviceAppService {

    void deviceAppCommit(List<DeviceAppCommitDTO> deviceAppCommitList);

    Pagination<DeviceAppVo> deviceAppPageQuery(DeviceAppQueryDTO queryDTO);

    DeviceAppDownloadResDTO deviceAppDownLoadQuery(DeviceAppDownloadReq deviceAppDownloadReq);
}

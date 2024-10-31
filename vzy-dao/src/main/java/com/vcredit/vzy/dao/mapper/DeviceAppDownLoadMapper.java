package com.vcredit.vzy.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vcredit.vzy.common.dto.DeviceAppDownloadReq;
import com.vcredit.vzy.dao.entity.DeviceAppDownloadDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceAppDownLoadMapper extends BaseMapper<DeviceAppDownloadDO> {
    List<DeviceAppDownloadDO> deviceAppDownLoadQuery(@Param("model") List<String> model,@Param("version")String version);
}

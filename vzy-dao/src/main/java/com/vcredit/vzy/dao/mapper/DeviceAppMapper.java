package com.vcredit.vzy.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vcredit.pluginconfig.bean.base.BaseMapper;
import com.vcredit.vzy.common.dto.DeviceAppDownloadReq;
import com.vcredit.vzy.common.dto.DeviceAppQueryDTO;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import com.vcredit.vzy.dao.entity.DeviceAppDO;
import java.util.List;

import com.vcredit.vzy.dao.entity.DeviceAppDownloadDO;
import org.apache.ibatis.annotations.Param;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
public interface DeviceAppMapper extends BaseMapper<DeviceAppDO> {

    DeviceAppDO queryByDeviceIdAndApp(@Param("deviceId") Long deviceId, @Param("app") String app);

    IPage<DeviceAppVo> deviceAppPageQuery(Page<DeviceAppVo> page, @Param("queryDTO") DeviceAppQueryDTO queryDTO);

    List<DeviceAppVo> queryUpdateRemindList(@Param("updateRemindTime")String updateRemindTime);

    List<DeviceAppDownloadDO> deviceAppDownLoadQuery(@Param ("queryDTO") DeviceAppDownloadReq deviceAppDownloadReq);
}

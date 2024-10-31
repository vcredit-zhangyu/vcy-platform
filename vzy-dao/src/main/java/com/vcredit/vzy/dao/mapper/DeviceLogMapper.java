package com.vcredit.vzy.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vcredit.pluginconfig.bean.base.BaseMapper;
import com.vcredit.vzy.common.dto.DeviceLogQueryDTO;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import com.vcredit.vzy.common.vo.DeviceLogVo;
import com.vcredit.vzy.dao.entity.DeviceLogDO;
import org.apache.ibatis.annotations.Param;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
public interface DeviceLogMapper extends BaseMapper<DeviceLogDO> {

    IPage<DeviceLogVo> deviceInfoPageQuery(Page<DeviceAppVo> page, @Param("queryDTO") DeviceLogQueryDTO queryDTO);
}

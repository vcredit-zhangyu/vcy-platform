package com.vcredit.vzy.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vcredit.pluginconfig.bean.base.BaseMapper;
import com.vcredit.vzy.common.dto.DeviceApplyPageQueryDTO;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import com.vcredit.vzy.common.vo.DeviceApplyDetailVo;
import com.vcredit.vzy.common.vo.DeviceApplyVo;
import com.vcredit.vzy.dao.entity.DeviceApplyDO;
import org.apache.ibatis.annotations.Param;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
public interface DeviceApplyMapper extends BaseMapper<DeviceApplyDO> {

    IPage<DeviceApplyVo> applyPageQuery(Page<DeviceAppVo> page, @Param("queryDTO") DeviceApplyPageQueryDTO queryDTO);

    DeviceApplyDetailVo applyDetail(@Param("applyId") Long applyId);
}

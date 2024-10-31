package com.vcredit.vzy.dao.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vcredit.pluginconfig.bean.base.BaseRepository;
import com.vcredit.vzy.common.dto.DeviceApplyPageQueryDTO;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import com.vcredit.vzy.common.vo.DeviceApplyDetailVo;
import com.vcredit.vzy.common.vo.DeviceApplyVo;
import com.vcredit.vzy.dao.entity.DeviceApplyDO;
import com.vcredit.vzy.dao.mapper.DeviceApplyMapper;
import org.springframework.stereotype.Repository;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
@Repository
public class DeviceApplyRepository extends BaseRepository<DeviceApplyMapper, DeviceApplyDO> {

    public IPage<DeviceApplyVo> applyPageQuery(DeviceApplyPageQueryDTO queryDTO) {
        Page<DeviceAppVo> page = new Page<>(queryDTO.getCurrentPage(), queryDTO.getPageSize());
        return baseMapper.applyPageQuery(page,queryDTO);
    }

    public DeviceApplyDetailVo applyDetail(Long applyId) {
        return baseMapper.applyDetail(applyId);
    }
}

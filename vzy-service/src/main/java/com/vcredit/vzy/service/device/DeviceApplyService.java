package com.vcredit.vzy.service.device;

import com.vcredit.vzy.common.dto.*;
import com.vcredit.vzy.common.vo.DeviceApplyDetailVo;
import com.vcredit.vzy.common.vo.DeviceApplyVo;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
public interface DeviceApplyService {

    void applyCommit(DeviceApplyCommitDTO deviceApplyCommitDTO);

    Pagination<DeviceApplyVo> applyPageQuery(DeviceApplyPageQueryDTO deviceApplyPageQueryDTO);

    void applyApprove(DeviceApplyApproveDTO deviceApplyApproveDTO);

    DeviceApplyDetailVo applyDetail(Long applyId);

    String applyExplain();

    void applyExplainCommit(DeviceApplyExplainDTO deviceApplyExplainDTO);
}

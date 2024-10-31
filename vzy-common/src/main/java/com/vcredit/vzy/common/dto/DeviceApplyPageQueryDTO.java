package com.vcredit.vzy.common.dto;

import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/18
 */
@Data
public class DeviceApplyPageQueryDTO {

    private Integer currentPage;
    private Integer pageSize;

    private String applyType;
    private String applyStatus;
    private String applyTimeBegin;
    private String applyTimeEnd;
    private String applyPerson;

    private String applyPersonAcct;


}

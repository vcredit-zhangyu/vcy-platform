package com.vcredit.vzy.common.enums;

import com.vcredit.vzy.common.vo.EnumsVo;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/19
 */
@Getter
@AllArgsConstructor
public enum ApproveReasonEnum {
    //设备维护中，借用者终止申请，其他
    DEVICE_MAINTENANCE("device_maintenance", "设备维护中"),
    BORROWER_TERMINATION("borrower_termination", "借用者终止申请"),
    OTHER("other", "其他"),

    ;

    private String code;
    private String name;

    public static List<EnumsVo> getCodeList() {
        List<EnumsVo> result = new ArrayList<>();
        for (ApproveReasonEnum value : values()) {
            result.add(new EnumsVo(value.getCode(), value.getName()));
        }
        return result;
    }


}

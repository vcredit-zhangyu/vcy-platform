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
public enum DeviceStatusEnum {
    //待申请，申请中，使用中，待归还,故障，已回收，已废弃;

    WAIT_APPLY("wait_apply", "待申请"),
    APPLYING("applying", "申请中"),
    USING("using", "使用中"),
    WAIT_RETURN("wait_return", "待归还"),
    FAULT("fault", "故障"),
    RECYCLED("recycled", "已回收"),
    ABANDONED("abandoned", "已废弃")
    ;

    private String code;
    private String name;


    public static List<EnumsVo> getCodeList() {
        List<EnumsVo> result = new ArrayList<>();
        for (DeviceStatusEnum value : values()) {
            result.add(new EnumsVo(value.getCode(), value.getName()));
        }
        return result;
    }

    public static String getNameByCode(String code) {
        for (DeviceStatusEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value.getName();
            }
        }
        return null;
    }



}

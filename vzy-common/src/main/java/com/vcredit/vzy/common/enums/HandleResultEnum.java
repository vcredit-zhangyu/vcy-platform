package com.vcredit.vzy.common.enums;

import com.vcredit.vzy.common.vo.EnumsVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
@AllArgsConstructor
public enum HandleResultEnum {
    DCL("待处理","待处理"),
    YCL("已处理","已处理"),
    PROCESSING("处理中","处理中"),
    WXCL("不予处理","不予处理");

    private String code;
    private String name;

    public static List<EnumsVo> getCodeList() {
        List<EnumsVo> result = new ArrayList<>();
        for (HandleResultEnum value : values()) {
            result.add(new EnumsVo(value.getCode(), value.getName()));
        }
        return result;
    }
}

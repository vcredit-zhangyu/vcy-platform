package com.vcredit.vzy.common.enums;

import com.vcredit.vzy.common.vo.EnumsVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum DeviceTypeSearchEnum {
    BRAND("brand","品牌"),
    MODEL("model","型号"),
    SYSTEM("system","系统"),
    SYSVERSION("systemVersion","系统版本");


    private String code;
    private String name;

    public static List<EnumsVo> getCodeList() {
        List<EnumsVo> result = new ArrayList<>();
        for (DeviceTypeSearchEnum value : values()) {
            result.add(new EnumsVo(value.getCode(), value.getName()));
        }
        return result;
    }
}

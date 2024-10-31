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
public enum DeviceTypeEnum {

    IOS("ios", "苹果"),
    ANDROID("android", "安卓"),

    ;

    private String code;
    private String name;


    public static List<EnumsVo> getCodeList() {
        List<EnumsVo> result = new ArrayList<>();
        for (DeviceTypeEnum value : values()) {
            result.add(new EnumsVo(value.getCode(), value.getName()));
        }
        return result;
    }

    public static String getNameByCode(String code) {
        for (DeviceTypeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value.getName();
            }
        }
        return null;
    }
}

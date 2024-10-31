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
 * @date : 2024/4/25
 */
@Getter
@AllArgsConstructor
public enum AndroidSystemEnum {
    //Android，harmonyos,苹果枚举：iOS
    ANDROID("ANDROID", "安卓"),
    HARMONYOS("HARMONYOS", "鸿蒙"),
    ;

    private String code;
    private String name;

    public static List<EnumsVo> getCodeList() {
        List<EnumsVo> result = new ArrayList<>();
        for (AndroidSystemEnum value : values()) {
            result.add(new EnumsVo(value.getCode(), value.getName()));
        }
        return result;
    }
}

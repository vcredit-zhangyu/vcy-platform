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
public enum AppEnum {
    //豆豆钱，卡卡贷，闪贷,豆乐购
    DOUDOUDAI("ddq", "豆豆钱"),
    KAKADAI("ccl", "卡卡贷"),
    SHANDAI("shandai", "闪贷"),
    DOULEGOU("dlg", "豆乐购"),

    ;

    private String code;
    private String name;


    public static List<EnumsVo> getCodeList() {
        List<EnumsVo> result = new ArrayList<>();
        for (AppEnum value : values()) {
            result.add(new EnumsVo(value.getCode(), value.getName()));
        }
        return result;
    }
}

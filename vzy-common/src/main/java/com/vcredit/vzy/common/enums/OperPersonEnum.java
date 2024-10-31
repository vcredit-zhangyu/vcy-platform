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
public enum OperPersonEnum {
    //管理员，系统，app
    ADMIN("admin", "管理员"),
    SYSTEM("system", "系统"),
    APP("app", "app"),

    ;

    private String code;
    private String name;


    public static List<EnumsVo> getCodeList() {
        List<EnumsVo> result = new ArrayList<>();
        for (OperPersonEnum value : values()) {
            result.add(new EnumsVo(value.getCode(), value.getName()));
        }
        return result;
    }
}

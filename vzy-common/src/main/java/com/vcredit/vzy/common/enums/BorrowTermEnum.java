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
public enum BorrowTermEnum {

    //7天，14天，1个月，3个月
//    SEVEN_DAY("seven_day", "7天", 7, "day"),
//    FOURTEEN_DAY("fourteen_day", "14天", 14, "day"),
    ONE_MONTH("one_month", "1个月", 1, "month"),
    THREE_MONTH("three_month", "3个月", 3, "month"),
    SIX_MONTH("six_month", "6个月", 6, "month"),
    ;

    private String code;
    private String name;
    private Integer num;
    private String unit;


    public static List<EnumsVo> getCodeList() {
        List<EnumsVo> result = new ArrayList<>();
        for (BorrowTermEnum value : values()) {
            result.add(new EnumsVo(value.getCode(), value.getName()));
        }
        return result;
    }

    public static BorrowTermEnum getBorrowTermEnum(String code) {
        for (BorrowTermEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
    public static String getNameByCode(String code) {
        for (BorrowTermEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value.getName();
            }
        }
        return "";
    }


}

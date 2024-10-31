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
public enum BrandEnum {
    //华为（HUAWEI）,小米（MI）,荣耀（HONOR）,vivo ,OPPO ,三星（SAMSUNG）,真我（realme）,魅族（meizu）,努比亚（nubia）,iQOO天语（K-Touch）,诺基亚（NOKIA）,飞利浦（PHILIPS）,摩托罗拉（Motorola）,ROG中兴（ZTE）,金立（Gionee）,索尼（SONY）
    HUAWEI("HUAWEI", "华为"),
    MI("MI", "小米"),
    HONOR("HONOR", "荣耀"),
    VIVO("VIVO", "vivo"),
    OPPO("OPPO", "OPPO"),
    SAMSUNG("SAMSUNG", "三星"),
    REALME("REALME", "真我"),
    MEIZU("MEIZU", "魅族"),
    NUBIA("NUBIA", "努比亚"),
    KTOUCH("K-TOUCH", "iQOO天语"),
    NOKIA("NOKIA", "诺基亚"),
    PHILIPS("PHILIPS", "飞利浦"),
    MOTOROLA("MOTOROLA", "摩托罗拉"),
    ZTE("ZTE", "ROG中兴"),
    GIONEE("GIONEE", "金立"),
    SONY("SONY", "索尼"),


    ;

    private String code;
    private String name;

    public static List<EnumsVo> getCodeList() {
        List<EnumsVo> result = new ArrayList<>();
        for (BrandEnum value : values()) {
            result.add(new EnumsVo(value.getCode(), value.getName()));
        }
        return result;
    }
}

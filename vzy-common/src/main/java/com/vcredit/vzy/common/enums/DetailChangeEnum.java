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
public enum DetailChangeEnum {
    //deviceNo,deviceName,deviceCharge,deviceChargeAcct,expireDate,deviceType,deviceBrand,deviceModel,deviceSystem,deviceSystemVersion,
    // deviceLoginUser,deviceLoginKey,deviceImei,deviceSerialNo,deviceResolution,deviceColor,deviceSize,deviceAccessory,deviceRemark
    DEVICE_NO("deviceNo", "设备号"),
    DEVICE_NAME("deviceName", "设备名称"),
    DEVICE_CHARGE("deviceCharge", "设备负责人"),
    DEVICE_CHARGE_ACCT("deviceChargeAcct", "设备负责人账号"),
    EXPIRE_DATE("expireDate", "到期日期"),
    DEVICE_TYPE("deviceType", "设备机型"),
    DEVICE_BRAND("deviceBrand", "设备品牌"),
    DEVICE_MODEL("deviceModel", "设备型号"),
    DEVICE_SYSTEM("deviceSystem", "设备系统"),
    DEVICE_SYSTEM_VERSION("deviceSystemVersion", "设备系统版本"),
    DEVICE_LOGIN_USER("deviceLoginUser", "设备用户名"),
    DEVICE_LOGIN_KEY("deviceLoginKey", "设备密码"),
    DEVICE_IMEI("deviceImei", "设备IMEI"),
    DEVICE_SERIAL_NO("deviceSerialNo", "设备序列号"),
    DEVICE_RESOLUTION("deviceResolution", "设备分辨率"),
    DEVICE_COLOR("deviceColor", "设备颜色"),
    DEVICE_SIZE("deviceSize", "设备尺寸"),
    DEVICE_ACCESSORY("deviceAccessory", "设备配件"),
    DEVICE_REMARK("deviceRemark", "设备备注");

    private String code;
    private String name;

    public static List<EnumsVo> getCodeList() {
        List<EnumsVo> result = new ArrayList<>();
        for (DetailChangeEnum value : values()) {
            result.add(new EnumsVo(value.getCode(), value.getName()));
        }
        return result;
    }

}

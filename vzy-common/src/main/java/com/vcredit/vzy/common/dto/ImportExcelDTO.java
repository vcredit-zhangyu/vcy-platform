package com.vcredit.vzy.common.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ImportExcelDTO implements Serializable {
    /**
     * 机型
     */
    @ExcelProperty(value = "机型", index = 0)
    private String deviceType;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称", index = 1)
    private String deviceName;

    /**
     * 品牌
     */
    @ExcelProperty(value = "品牌", index = 2)
    private String deviceBrand;

    /**
     * 型号
     */
    @ExcelProperty(value = "型号", index = 3)
    private String deviceModel;

    /**
     * 系统
     */
    @ExcelProperty(value = "系统", index = 4)
    private String deviceSystem;

    /**
     * 系统版本
     */
    @ExcelProperty(value = "系统版本", index = 5)
    private String deviceSystemVersion;

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名", index = 6)
    private String deviceLoginUser;

    /**
     * 密码
     */
    @ExcelProperty(value = "密码", index = 7)
    private String deviceLoginKey;

    /**
     * IMEI（安卓）
     */
    @ExcelProperty(value = "IMEI（安卓）", index = 8)
    private String deviceImei;

    /**
     * 序列号（ios）
     */
    @ExcelProperty(value = "序列号（ios）", index = 9)
    private String deviceSerialNo;

    /**
     * 分辨率
     */
    @ExcelProperty(value = "分辨率", index = 10)
    private String deviceResolution;

    /**
     * 尺寸
     */
    @ExcelProperty(value = "尺寸", index = 11)
    private String deviceSize;

    /**
     * 颜色
     */
    @ExcelProperty(value = "颜色", index = 12)
    private String deviceColor;

    /**
     * 配件
     */
    @ExcelProperty(value = "配件", index = 13)
    private String deviceAccessory;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = 14)
    private String deviceRemark;

}

package com.vcredit.vzy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/3/18
 */
@Getter
@AllArgsConstructor
public enum BizErrorEnum {
    /**
     * 成功
     */
    SUCCESS(0, "SUCCESS"),

    /**
     * 失败
     */
    ERROR(-1, "ERROR"),

    /**
     * 参数错误
     */
    PARAM_ERROR(100000, "参数错误"),

    /**
     * 登录失效
     */
    LOGIN_ERROR(200001, "登录失效，请重新登录"),

    /**
     * 签名错误
     */
    SIGNATURE_ERROR(100016,"签名错误"),

    AUTH_ERROR(100001,"认证失败，请重新登录"),
    USER_ERROR(200003,"用户不存在"),
    NO_PERMISSION_ERROR(200007,"用户无权限，请重新登录"),

    CONFIG_ITEM_ERROR(30002, "配置项错误，请联系管理员"),
    CONFIG_ITEM_REPEAT(30003, "配置项重复"),
    CONFIG_ITEM_NOT_FOUND(30004, "配置项不存在"),


    /**
     * 文件错误码
     */
    MEETING_TASK_COUNT(400001, "其他任务处理中，请稍后再试！"),
    NOT_FOUND_MEETING_TASK(400002, "任务已被删除，请刷新页面重试"),
    MEETING_TASK_STATUS_ERROR(400003, "任务已完成，请勿重新刷新页面"),
    MEETING_TASK_DETAIL_EMPTY(400004, "录音文件上传失败请重新上传"),
    MEETING_TASK_DETAIL_ECM_ID_EMPTY(400005, "录音文件上传失败，失败原因：上传ecm失败"),
    TASK_ALREADY_IN_PROCESS(400006, "任务已在执行中，请刷新页面查看"),


    /**
     * 文件错误码
     */
    NOT_UPLOAD_FILE(600001, "未上传文件"),
    NOT_FOUND_FILE(600002, "上传文件内容为空"),
    FILE_TYPE_ERROR(600003, "文件类型错误，必须上传正确的excel文件"),
    FILE_TEMPLATE_ERROR(600004, "文件模板错误，请上传正确的文件"),
    FILE_FORMAT_ERROR(600005, "解析excel格式错误，请检查文件格式"),
    FILE_TYPE_IMAGE_ERROR(600006, "文件类型错误，必须上传正确的jpg/png/jpeg文件"),
    FILE_TYPE_AUDIO_ERROR(600006, "文件类型错误，必须上传正确的音频文件"),
    FILE_IN_PROCESSING(600007, "当前用户有正在处理的文件"),

    //设备已存在
    DEVICE_EXIST(700001, "设备已存在"),

    //未找到设备
    DEVICE_NOT_EXIST(99, "设备不存在"),

    //设备正在操作中
    DEVICE_IS_CHARGE(700002, "设备正在操作中，请稍后再试"),

    ;

    private final int code;
    private final String msg;


    public int getErrorCode() {
        return this.code;
    }

    public String getErrorMsg() {
        return this.msg;
    }
}

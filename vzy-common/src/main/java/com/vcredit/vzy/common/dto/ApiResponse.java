package com.vcredit.vzy.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vcredit.vzy.common.enums.BizErrorEnum;
import java.io.Serializable;
import lombok.Getter;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/3/18
 */
@Getter
public class ApiResponse<T> implements Serializable {


    private int code;

    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private ApiResponse(int code) {
        this.code = code;
    }

    private ApiResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    private ApiResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ApiResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<T>(BizErrorEnum.SUCCESS.getCode(), BizErrorEnum.SUCCESS.getMsg());
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(BizErrorEnum.SUCCESS.getCode(), BizErrorEnum.SUCCESS.getMsg(), data);
    }

    public static <T> ApiResponse<T> successMsg(String msg) {
        return new ApiResponse<T>(BizErrorEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ApiResponse<T> success(String msg, T data) {
        return new ApiResponse<T>(BizErrorEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> ApiResponse<T> error() {
        return new ApiResponse<T>(BizErrorEnum.ERROR.getCode(), BizErrorEnum.ERROR.getMsg());
    }

    public static <T> ApiResponse<T> error(String errorMessage) {
        return new ApiResponse<T>(BizErrorEnum.ERROR.getCode(), errorMessage);
    }

    public static <T> ApiResponse<T> error(BizErrorEnum errorEnum) {
        return new ApiResponse<T>(errorEnum.getCode(), errorEnum.getErrorMsg());
    }


    public static <T> ApiResponse<T> error(int errorCode, String errorMessage) {
        return new ApiResponse<T>(errorCode, errorMessage);
    }
}

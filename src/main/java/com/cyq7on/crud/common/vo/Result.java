package com.cyq7on.crud.common.vo;

import lombok.Data;


@Data
public class Result<T>{

    private T data;

    /**
     * 错误码
     */
    private int errorCode;

    /**
     * 信息
     */
    private String message;

    private Result() {

    }

    public static <T> Result<T> ok() {
        return ok(null,"");
    }

    public static <T> Result<T> ok(T data) {
        return ok(data,"成功");
    }
    public static <T> Result<T> ok(String message) {
        Result<T> result = new Result<>();
        result.errorCode = 0;
        result.message = message;
        return result;
    }
    public static <T> Result<T> ok(T value,String message) {
        Result<T> result = new Result<>();
        result.errorCode = 0;
        result.data = value;
        result.message = message;
        return result;
    }

    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.errorCode = -1;
        return result;
    }

    public static <T> Result<T> fail(String message) {
        return fail(-1,message);
    }

    public static <T> Result<T> fail(int errorCode) {
        return fail(errorCode,"");
    }

    public static <T> Result<T> fail(int errorCode, String message) {
        Result<T> result = new Result<>();
        result.errorCode = errorCode;
        result.message = message;
        return result;
    }

}

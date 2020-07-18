package com.fyz.spring.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(200, "success", data);
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<>(200, "success", null);
    }

    public static <T> CommonResult<T> failure(T data) {
        return new CommonResult<>(500, "failure", data);
    }

    public static <T> CommonResult<T> failure() {
        return new CommonResult<>(500, "failure", null);
    }
}


package com.zsm.usercenter.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author : ZSM
 * Time :  2024/12/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class R<T> {
    // 状态码
    private int code;

    // 返回消息
    private String msg;

    // 返回数据
    private T data;

    /**
     * 静态方法：返回成功（无数据）
     */
    public static <T> R<T> success() {
        return R.<T>builder()
                .code(200)
                .msg("成功")
                .build();
    }

    /**
     * 静态方法：返回成功（带数据）
     */
    public static <T> R<T> success(T data) {
        return R.<T>builder()
                .code(200)
                .msg("成功")
                .data(data)
                .build();
    }

    /**
     * 静态方法：返回成功（带自定义消息和数据）
     */
    public static <T> R<T> success(String msg, T data) {
        return R.<T>builder()
                .code(200)
                .msg(msg)
                .data(data)
                .build();
    }

    /**
     * 静态方法：返回失败（仅消息）
     */
    public static <T> R<T> error(String msg) {
        return R.<T>builder()
                .code(500)
                .msg(msg)
                .build();
    }

    /**
     * 静态方法：返回失败（仅状态码和消息）
     */
    public static <T> R<T> error(int code, String msg) {
        return R.<T>builder()
                .code(code)
                .msg(msg)
                .build();
    }

    /**
     * 静态方法：自定义返回（状态码、消息和数据）
     */
    public static <T> R<T> request(int code, String msg, T data) {
        return R.<T>builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }
}
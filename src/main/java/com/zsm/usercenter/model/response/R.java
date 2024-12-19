package com.zsm.usercenter.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回结果类，用于统一API接口返回格式
 *
 * @param <T> 返回数据的类型
 *            <p>
 *            Author : ZSM
 *            Time :  2024/12/18
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

    // 详情信息（可选，用于补充错误或成功的详细说明）
    private String detail;

    /**
     * 静态方法：返回成功（无数据）
     *
     * @param <T> 数据类型
     * @return 通用返回结果对象
     */
    public static <T> R<T> success() {
        return R.<T>builder()
                .code(CodeEnum.SUCCESS.getCode())
                .msg(CodeEnum.SUCCESS.getMsg())
                .build();
    }

    /**
     * 静态方法：返回成功（带数据）
     *
     * @param data 返回数据
     * @param <T>  数据类型
     * @return 通用返回结果对象
     */
    public static <T> R<T> success(T data) {
        return R.<T>builder()
                .code(CodeEnum.SUCCESS.getCode())
                .msg(CodeEnum.SUCCESS.getMsg())
                .data(data)
                .build();
    }

    /**
     * 静态方法：返回成功（自定义消息和数据）
     *
     * @param msg  自定义返回消息
     * @param data 返回数据
     * @param <T>  数据类型
     * @return 通用返回结果对象
     */
    public static <T> R<T> success(String msg, T data) {
        return R.<T>builder()
                .code(CodeEnum.SUCCESS.getCode())
                .msg(msg)
                .data(data)
                .build();
    }

    /**
     * 静态方法：返回失败（仅消息）
     *
     * @param msg 错误消息
     * @param <T> 数据类型
     * @return 通用返回结果对象
     */
    public static <T> R<T> error(String msg) {
        return R.<T>builder()
                .code(CodeEnum.ERROR.getCode())
                .msg(msg)
                .build();
    }

    /**
     * 静态方法：返回失败（状态码和消息）
     *
     * @param code 自定义状态码
     * @param msg  错误消息
     * @param <T>  数据类型
     * @return 通用返回结果对象
     */
    public static <T> R<T> error(int code, String msg) {
        return R.<T>builder()
                .code(code)
                .msg(msg)
                .build();
    }

    /**
     * 静态方法：返回失败（状态码和消息）
     *
     * @param code 自定义状态码
     * @param msg  错误消息
     * @param <T>  数据类型
     * @param detail 详细
     * @return 通用返回结果对象
     */
    public static <T> R<T> error(int code, String msg, String detail) {
        return R.<T>builder()
                .code(code)
                .msg(msg)
                .detail(detail)
                .build();
    }

    /**
     * 静态方法：自定义返回（状态码、消息和数据）
     *
     * @param code 自定义状态码
     * @param msg  自定义消息
     * @param data 返回数据
     * @param <T>  数据类型
     * @return 通用返回结果对象
     */
    public static <T> R<T> response(int code, String msg, T data) {
        return R.<T>builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }

    /**
     * 静态方法：自定义返回（状态码、消息、数据和详情）
     *
     * @param code   自定义状态码
     * @param msg    自定义消息
     * @param data   返回数据
     * @param detail 详情信息
     * @param <T>    数据类型
     * @return 通用返回结果对象
     */
    public static <T> R<T> response(int code, String msg, T data, String detail) {
        return R.<T>builder()
                .code(code)
                .msg(msg)
                .data(data)
                .detail(detail)
                .build();
    }

    /**
     * 静态方法：通过枚举生成返回结果（无数据）
     * 使用指定的 {@link CodeEnum} 枚举值来快速生成返回结果对象。
     * 适用于只需要返回状态码和消息的场景。
     *
     * @param codeEnum 返回结果的枚举类型，包含状态码、消息和详情（如有）。
     * @param <T>      数据类型（此方法返回的数据为 null）。
     * @return 通用返回结果对象，包含状态码和消息，无数据。
     */
    public static <T> R<T> response(CodeEnum codeEnum) {
        return R.<T>builder()
                .code(codeEnum.getCode())
                .msg(codeEnum.getMsg())
                .build();
    }

    /**
     * 静态方法：通过枚举生成返回结果（带数据）
     * 使用指定的 {@link CodeEnum} 枚举值来快速生成返回结果对象。
     * 适用于需要返回状态码、消息以及数据的场景。
     *
     * @param codeEnum 返回结果的枚举类型，包含状态码、消息和详情（如有）。
     * @param data     返回的数据，可以是任意类型的对象。
     * @param <T>      数据类型，与返回的数据类型一致。
     * @return 通用返回结果对象，包含状态码、消息和数据。
     */
    public static <T> R<T> response(CodeEnum codeEnum, T data) {
        return R.<T>builder()
                .code(codeEnum.getCode())
                .msg(codeEnum.getMsg())
                .data(data)
                .build();
    }

}

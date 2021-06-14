package com.common.exception;

/**
 * @author 念着倒才子傻
 */
public interface BaseErrorInfoInterface {
    /**
     * 错误码
     *
     * @return 错误码
     */
    String getResultCode();

    /**
     * 错误描述
     *
     * @return 错误描述
     */
    String getResultMsg();
}

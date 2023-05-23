package cn.edu.cdtu.contactmanagement.exception;

import cn.edu.cdtu.contactmanagement.type.ResponseType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author makun
 * @project spring
 * @description 业务层异常
 * @date 2023/05/33 17:42:33
 * version 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    // 状态码
    private ResponseType code;

    /**
     * 有参构造
     * @param message  消息
     * @param code 状态码
     */
    public BusinessException(String message, ResponseType code) {
        super(message);
        this.code = code;
    }

    /**
     * 有参构造
     * @param message 消息
     * @param cause
     * @param code 状态码
     */
    public BusinessException(String message, Throwable cause, ResponseType code) {
        super(message, cause);
        this.code = code;
    }

    /**
     * 有参构造
     * @param cause
     * @param code
     */
    public BusinessException(Throwable cause, ResponseType code) {
        super(cause);
        this.code = code;
    }

    /**
     * 有参构造
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     * @param code
     */
    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ResponseType code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

}

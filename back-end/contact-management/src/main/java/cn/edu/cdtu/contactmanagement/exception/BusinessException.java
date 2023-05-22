package cn.edu.cdtu.contactmanagement.exception;

import cn.edu.cdtu.contactmanagement.type.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author makun
 * @project spring
 * @description 业务层异常
 * @date 2023/05/33 17:42:33
 * version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    private ResponseType code;

    public BusinessException(String message, ResponseType code) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause, ResponseType code) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(Throwable cause, ResponseType code) {
        super(cause);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ResponseType code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

}

package cn.edu.cdtu.contactmanagement.handler;

import cn.edu.cdtu.contactmanagement.exception.BusinessException;
import cn.edu.cdtu.contactmanagement.exception.SystemException;
import cn.edu.cdtu.contactmanagement.model.info.JsonResult;
import cn.edu.cdtu.contactmanagement.type.ResponseType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description 全局异常处理
 * @Author makun
 * @Date 2023/5/22 17:36
 * @Version 1.0
 */

@RestControllerAdvice
public class ExceptionGlobalHandler {
    @ExceptionHandler(SystemException.class)
    public JsonResult doSystemException(SystemException exception) {
        System.out.println("系统异常你好");
        // 记录日志
        // 发送消息给运维
        // 发送邮件给运维人员
        return new JsonResult(ResponseType.ERROR, "服务器繁忙，请稍后再试", null);
    }

    @ExceptionHandler(BusinessException.class)
    public JsonResult doBusinessException(BusinessException exception) {
        System.out.println("业务异常你好");
        return new JsonResult(ResponseType.ERROR, exception.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public JsonResult doException(Exception exception) {
        System.out.println("未知异常你好");
        return new JsonResult(ResponseType.ERROR, exception.getMessage(), null);
    }
}
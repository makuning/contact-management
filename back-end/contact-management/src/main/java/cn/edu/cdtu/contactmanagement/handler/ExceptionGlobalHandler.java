package cn.edu.cdtu.contactmanagement.handler;

import cn.edu.cdtu.contactmanagement.exception.BusinessException;
import cn.edu.cdtu.contactmanagement.exception.SystemException;
import cn.edu.cdtu.contactmanagement.model.info.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.servlet.ServletException;

/**
 * @Description 全局异常处理
 * @Author makun
 * @Date 2023/5/22 17:36
 * @Version 1.0
 */

@RestControllerAdvice
public class ExceptionGlobalHandler {
    /**
     * 系统异常
     * @param exception
     * @return
     */
    @ExceptionHandler(SystemException.class)
    public JsonResult doSystemException(SystemException exception) {
        System.out.println("系统异常你好");
        // 记录日志
        // 发送消息给运维
        // 发送邮件给运维人员
        return JsonResult.error(exception.getMessage(), "服务器繁忙，请稍后再试");
    }

    /**
     * 业务异常
     * @param exception
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public JsonResult doBusinessException(BusinessException exception) {
        System.out.println("业务异常你好");
        return JsonResult.error(exception.getMessage(), "服务器繁忙，请稍后再试");
    }

    /**
     * servlet异常
     * @return
     */
    @ExceptionHandler(ServletException.class)
    public JsonResult doNoHandlerFoundException(ServletException exception) {
        String message = "服务异常";
        if (exception instanceof NoHandlerFoundException) {
            message = "404，服务未找到";
        }
        return JsonResult.error(exception.getMessage(), message);
    }

    /**
     * 全局异常
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public JsonResult doException(Exception exception) {
        System.out.println("未知异常你好");
        return JsonResult.error(exception.getMessage(), "未知错误");
    }
}
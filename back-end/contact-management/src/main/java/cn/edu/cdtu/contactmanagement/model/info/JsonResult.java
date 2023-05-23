package cn.edu.cdtu.contactmanagement.model.info;

import cn.edu.cdtu.contactmanagement.type.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 前后端数据交换格式
 * @Author makun
 * @Date 2023/5/22 13:47
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    private ResponseType code;      // 自定义状态码
    private Object data;            // 需要返回的数据
    private String message;         // 提示消息

    /**
     * 快捷创建成功消息对象
     * @param data 返回数据
     * @param message 消息
     * @return
     */
    public static JsonResult success(Object data, String message) {
        return new JsonResult(ResponseType.SUCCESS, data, message);
    }

    /**
     * 快捷创建警告消息对象
     * @param data 返回数据
     * @param message 消息
     * @return
     */
    public static JsonResult warn(Object data, String message) {
        return new JsonResult(ResponseType.WORN, data, message);
    }

    /**
     * 快捷创建失败消息对象
     * @param data 返回数据
     * @param message 消息
     * @return
     */
    public static JsonResult fail(Object data, String message) {
        return new JsonResult(ResponseType.FAIL, data, message);
    }

    /**
     * 快捷创建错误消息对象
     * @param data 返回数据
     * @param message 消息
     * @return
     */
    public static JsonResult error(Object data, String message) {
        return new JsonResult(ResponseType.ERROR, data, message);
    }
}

package cn.edu.cdtu.contactmanagement.model.info;

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
    // 消息类型
    public enum ResponseCode {
        SUCCESS(0),
        WORN(1),
        FAIL(2),
        ERROR(-1);

        private final int code;

        ResponseCode(int code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return String.valueOf(code);
        }
    }

    private ResponseCode code;
    private Object data;
    private String message;

    /**
     * 快捷创建成功消息对象
     * @param data
     * @param message
     * @return
     */
    public static JsonResult success(Object data, String message) {
        return new JsonResult(ResponseCode.SUCCESS, data, message);
    }

    /**
     * 快捷创建警告消息对象
     * @param data
     * @param message
     * @return
     */
    public static JsonResult warn(Object data, String message) {
        return new JsonResult(ResponseCode.WORN, data, message);
    }

    /**
     * 快捷创建失败消息对象
     * @param data
     * @param message
     * @return
     */
    public static JsonResult fail(Object data, String message) {
        return new JsonResult(ResponseCode.FAIL, data, message);
    }

    /**
     * 快捷创建错误消息对象
     * @param data
     * @param message
     * @return
     */
    public static JsonResult error(Object data, String message) {
        return new JsonResult(ResponseCode.ERROR, data, message);
    }
}

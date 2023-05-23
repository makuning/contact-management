package cn.edu.cdtu.contactmanagement.type;

/**
 * @Description 消息类型
 * @Author makun
 * @Date 2023/5/22 16:58
 * @Version 1.0
 */
public enum ResponseType {
    SUCCESS(0), // 成功状态码
    WORN(1),    // 警告状态码
    FAIL(2),    // 失败状态码
    ERROR(-1);  // 错误状态码

    private final int code;

    ResponseType(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}

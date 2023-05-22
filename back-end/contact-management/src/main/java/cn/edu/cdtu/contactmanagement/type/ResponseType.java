package cn.edu.cdtu.contactmanagement.type;

/**
 * @Description 消息类型
 * @Author makun
 * @Date 2023/5/22 16:58
 * @Version 1.0
 */
public enum ResponseType {
    SUCCESS(0),
    WORN(1),
    FAIL(2),
    ERROR(-1);

    private final int code;

    ResponseType(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}

package cn.edu.cdtu.contactmanagement.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description 对象转JSON字符串
 * @Author makun
 * @Date 2023/5/22 15:24
 * @Version 1.0
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

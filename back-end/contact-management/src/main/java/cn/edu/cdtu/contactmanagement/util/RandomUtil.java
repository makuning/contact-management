package cn.edu.cdtu.contactmanagement.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @Description 随机数工具
 * @Author makun
 * @Date 2023/5/24 10:58
 * @Version 1.0
 */
public class RandomUtil {
    private static final Random RANDOM = new SecureRandom();

    /**
     * 生成指定范围内的随机整数
     *
     * @param min 随机整数的最小值
     * @param max 随机整数的最大值
     * @return 生成的随机整数
     */
    public static int randomInt(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }

    /**
     * 生成指定长度的随机字符串
     *
     * @param length 随机字符串的长度
     * @return 生成的随机字符串
     */
    public static String randomString(int length) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    /**
     * 生成指定范围内的随机浮点数
     *
     * @param min 随机浮点数的最小值
     * @param max 随机浮点数的最大值
     * @param scale 小数精度
     * @return 生成的随机浮点数
     */
    public static double randomDouble(double min, double max, int scale) {
        double value = min + (max - min) * RANDOM.nextDouble();
        return new BigDecimal(value).setScale(scale, RoundingMode.DOWN).doubleValue();
    }

    /**
     * 生成一个指定长度的随机字节数组
     *
     * @param length 随机字节数组的长度
     * @return 生成的随机字节数组
     */
    public static byte[] randomBytes(int length) {
        byte[] bytes = new byte[length];
        RANDOM.nextBytes(bytes);
        return bytes;
    }
}

package cn.edu.cdtu.contactmanagement.util;

/**
 * @Description 默认头像工具
 * @Author makun
 * @Date 2023/5/23 14:05
 * @Version 1.0
 */
public class DefaultHeadUtils {
    /**
     * 返回随机的头像
     * @return
     */
    public static String randomHead() {
        String pre = "http://makun-ing-image-bed.19marken.top/contact-management/head/default/";
        String fix = ".png";
        String filename = String.valueOf(Math.round((Math.random() * 4) + 1));
        return pre + filename + fix;
    }
}

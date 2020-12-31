package top.waxijiang.rush.utils;

import java.util.Random;

/**
 * @author waxijiang
 */
public class SaltUtils {
    /**
     * 生成salt的静态方法
     *
     * @param n 长度
     * @return 盐字符串
     */
    public static String getSalt(int n) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }
}
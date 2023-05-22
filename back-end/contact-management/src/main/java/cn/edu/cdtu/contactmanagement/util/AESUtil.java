package cn.edu.cdtu.contactmanagement.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * @Description AES加密/解密
 * @Author makun
 * @Date 2023/5/22 15:49
 * @Version 1.0
 */
public class AESUtil {
    public static final String DEFAULT_KEY = "InsectMk";

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    private static final String KEY_ALGORITHM = "AES";

    // 16位随机向量（IV）
    private static final byte[] IV_BYTES = new byte[16];

    /**
     * 加密数据
     *
     * @param data 待加密的数据
     * @param key  密钥
     * @return 加密后的结果
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bytes = doEncrypt(data.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 解密数据
     *
     * @param data 待解密的数据
     * @param key  密钥
     * @return 解密后的结果
     */
    public static String decrypt(String data, String key) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(data);
        return new String(doDecrypt(bytes, key.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    /**
     * AES加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return 加密后的数据
     */
    private static byte[] doEncrypt(byte[] data, byte[] key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(getMD5(key), KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(IV_BYTES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
        return cipher.doFinal(data);
    }

    /**
     * AES解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return 解密后的数据
     */
    private static byte[] doDecrypt(byte[] data, byte[] key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(getMD5(key), KEY_ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(IV_BYTES);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        return cipher.doFinal(data);
    }

    /**
     * 生成MD5哈希值
     *
     * @param data 原始数据
     * @return 哈希值
     */
    private static byte[] getMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] key = md5.digest(data);
        return Arrays.copyOf(key, 16);
    }

}

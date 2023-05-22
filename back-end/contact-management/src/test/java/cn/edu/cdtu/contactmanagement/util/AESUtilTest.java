package cn.edu.cdtu.contactmanagement.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AESUtilTest {

    @Test
    void encryptByAES() throws Exception {
        String str = "这是什么";
        String strEn = AESUtil.encrypt(str,AESUtil.DEFAULT_KEY);
        String strDe = AESUtil.decrypt(strEn,AESUtil.DEFAULT_KEY);
        Assertions.assertEquals(str, strDe);
    }

    @Test
    void decryptByAES() {
    }
}
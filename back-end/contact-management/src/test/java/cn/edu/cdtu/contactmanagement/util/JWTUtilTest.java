package cn.edu.cdtu.contactmanagement.util;

import cn.edu.cdtu.contactmanagement.model.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JWTUtilTest {

    @Test
    void generateJWT() {
        User user = new User();
        user.setId("jsdiofj");
        user.setUsername("jsidfjoj");
        String s = JWTUtil.generateJWT(user);
        System.out.println(s);
    }
}
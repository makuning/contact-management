package cn.edu.cdtu.contactmanagement.service.impl;

import cn.edu.cdtu.contactmanagement.ContactManagementApplication;
import cn.edu.cdtu.contactmanagement.model.entity.User;
import cn.edu.cdtu.contactmanagement.service.UserService;
import cn.edu.cdtu.contactmanagement.type.ResponseType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ContactManagementApplication.class})
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    void getAllUsers() {
        System.out.println(userService.getAllUsers());
    }

    @Test
    void createUser() {
        User user = new User();
        user.setUsername("moenya");
        user.setPassword("123456");
        Assertions.assertEquals(ResponseType.SUCCESS, userService.createUser(user));
    }

    @Test
    void deleteUser() {
        Assertions.assertEquals(1, userService.deleteUser("18f532370c2c88188d8843fc85aaf75f"));
    }

    @Test
    void login() {
    }
}
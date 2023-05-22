package cn.edu.cdtu.contactmanagement.service.impl;

import cn.edu.cdtu.contactmanagement.ContactManagementApplication;
import cn.edu.cdtu.contactmanagement.model.entity.User;
import cn.edu.cdtu.contactmanagement.service.UserService;
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
        user.setUsername("makun");
        user.setPassword("123456");
        Assertions.assertEquals(1, userService.createUser(user));
    }
}
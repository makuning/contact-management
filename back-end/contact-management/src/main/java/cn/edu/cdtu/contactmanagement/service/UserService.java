package cn.edu.cdtu.contactmanagement.service;

import cn.edu.cdtu.contactmanagement.model.entity.User;

import java.util.List;

/**
 * @Description 用户服务接口
 * @Author makun
 * @Date 2023/5/22 11:46
 * @Version 1.0
 */
public interface UserService {
    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllUsers();

    /**
     * 创建一个新用户
     * @param user
     * @return
     */
    int createUser(User user);
}

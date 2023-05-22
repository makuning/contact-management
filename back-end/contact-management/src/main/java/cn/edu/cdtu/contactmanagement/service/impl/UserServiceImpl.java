package cn.edu.cdtu.contactmanagement.service.impl;

import cn.edu.cdtu.contactmanagement.mapper.UserMapper;
import cn.edu.cdtu.contactmanagement.model.entity.User;
import cn.edu.cdtu.contactmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户服务
 * @Author makun
 * @Date 2023/5/22 11:47
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取所有的用户
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }

    /**
     * 创建用户
     * @param user
     * @return
     */
    @Override
    public int createUser(User user) {
        return userMapper.insert(user);
    }
}

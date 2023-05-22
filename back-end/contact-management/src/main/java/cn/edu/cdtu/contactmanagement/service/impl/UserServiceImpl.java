package cn.edu.cdtu.contactmanagement.service.impl;

import cn.edu.cdtu.contactmanagement.mapper.UserMapper;
import cn.edu.cdtu.contactmanagement.model.entity.User;
import cn.edu.cdtu.contactmanagement.model.info.UserInfo;
import cn.edu.cdtu.contactmanagement.service.UserService;
import cn.edu.cdtu.contactmanagement.type.ResponseType;
import cn.edu.cdtu.contactmanagement.util.AESUtil;
import cn.edu.cdtu.contactmanagement.util.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
     * 获取用户的详细信息
     * @param id 用户ID
     * @return
     */
    @Override
    public UserInfo getUserDetailInfo(String id) {
        User user = userMapper.selectById(id);
        UserInfo userInfo = new UserInfo(user.getId(), user.getUsername(), user.getPassword(),user.getContactId(),user.getIsDeleted());
        return userInfo;
    }

    @Override
    public String login(String username, String password) {
        try {
            LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
            userQueryWrapper.eq(User::getUsername, AESUtil.encrypt(username, AESUtil.DEFAULT_KEY));
            userQueryWrapper.eq(User::getPassword, AESUtil.encrypt(password, AESUtil.DEFAULT_KEY));
            User user = userMapper.selectOne(userQueryWrapper);
            if (user != null) {
                return JWTUtil.generateJWT(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

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
    public ResponseType createUser(User user) {
        int result = userMapper.insert(user);
        return result == 1 ? ResponseType.SUCCESS : ResponseType.FAIL;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public ResponseType deleteUser(String id) {
        int result = userMapper.deleteById(id);
        return result == 1 ? ResponseType.SUCCESS : ResponseType.FAIL;
    }
}

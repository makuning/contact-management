package cn.edu.cdtu.contactmanagement.service;

import cn.edu.cdtu.contactmanagement.model.entity.Contact;
import cn.edu.cdtu.contactmanagement.model.entity.User;
import cn.edu.cdtu.contactmanagement.model.info.UserInfo;
import cn.edu.cdtu.contactmanagement.type.ResponseType;
import java.util.List;

/**
 * @Description 用户服务接口
 * @Author makun
 * @Date 2023/5/22 11:46
 * @Version 1.0
 */
public interface UserService {
    /**
     * 更新用户的详细信息
     * @param contact
     * @param id
     * @return
     */
    ResponseType updateUserBaseInfo(Contact contact, String id);

    /**
     * 获取用户的详细信息
     * @param id 用户ID
     * @return
     */
    UserInfo getUserDetailInfo(String id);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

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
    ResponseType createUser(User user);

    /**
     * 删除一个用户
     * @param id
     * @return
     */
    ResponseType deleteUser(String id);
}

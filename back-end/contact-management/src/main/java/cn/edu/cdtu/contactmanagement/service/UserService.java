package cn.edu.cdtu.contactmanagement.service;

import cn.edu.cdtu.contactmanagement.model.entity.Contact;
import cn.edu.cdtu.contactmanagement.model.entity.Mail;
import cn.edu.cdtu.contactmanagement.model.entity.Phone;
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
     * 添加邮箱
     * @param mail 邮箱信息
     * @param id 用户ID
     * @return
     */
    ResponseType addMail(Mail mail, String id);

    /**
     * 添加手机
     * @param phone 电话信息
     * @param id 用户ID
     * @return
     */
    ResponseType addPhone(Phone phone, String id);

    /**
     * 更新用户的详细信息
     * @param contact 联系人信息
     * @param id 用户ID
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
     * @param username 用户名
     * @param password 用户密码
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
     * @param user 用户登录信息
     * @return
     */
    ResponseType createUser(User user);

    /**
     * 删除一个用户
     * @param id 用户ID
     * @return
     */
    ResponseType deleteUser(String id);
}

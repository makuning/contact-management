package cn.edu.cdtu.contactmanagement.service.impl;

import cn.edu.cdtu.contactmanagement.mapper.ContactMapper;
import cn.edu.cdtu.contactmanagement.mapper.MailMapper;
import cn.edu.cdtu.contactmanagement.mapper.PhoneMapper;
import cn.edu.cdtu.contactmanagement.mapper.UserMapper;
import cn.edu.cdtu.contactmanagement.model.entity.Contact;
import cn.edu.cdtu.contactmanagement.model.entity.Mail;
import cn.edu.cdtu.contactmanagement.model.entity.Phone;
import cn.edu.cdtu.contactmanagement.model.entity.User;
import cn.edu.cdtu.contactmanagement.model.info.UserInfo;
import cn.edu.cdtu.contactmanagement.service.UserService;
import cn.edu.cdtu.contactmanagement.type.ResponseType;
import cn.edu.cdtu.contactmanagement.util.AESUtil;
import cn.edu.cdtu.contactmanagement.util.DefaultHeadUtils;
import cn.edu.cdtu.contactmanagement.util.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private MailMapper mailMapper;

    /**
     * 添加邮箱
     * @param mail 邮箱信息
     * @param id 用户ID
     * @return
     */
    @Override
    public ResponseType addMail(Mail mail, String id) {
        User user = userMapper.selectById(id);
        mail.setContactId(user.getContactId());
        mailMapper.insert(mail);
        return ResponseType.SUCCESS;
    }

    /**
     * 添加手机号
     * @param phone 电话信息
     * @param id 用户ID
     * @return
     */
    @Override
    public ResponseType addPhone(Phone phone, String id) {
        User user = userMapper.selectById(id);
        phone.setContactId(user.getContactId());
        phoneMapper.insert(phone);
        return ResponseType.SUCCESS;
    }

    /**
     * 更新用户的基本信息
     * @param contact 联系人信息
     * @param id 用户ID
     * @return
     */
    @Override
    public ResponseType updateUserBaseInfo(Contact contact, String id) {
        LambdaQueryWrapper<Contact> contactLambdaQueryWrapper = new LambdaQueryWrapper<>();
        contactLambdaQueryWrapper.eq(Contact::getId,userMapper.selectById(id).getContactId());
        contactMapper.update(contact, contactLambdaQueryWrapper);

        return ResponseType.SUCCESS;
    }

    /**
     * 获取用户的详细信息
     * @param id 用户ID
     * @return
     */
    @Override
    public UserInfo getUserDetailInfo(String id) {
        // 先找到用户的基本信息
        User user = userMapper.selectById(id);
        Contact contact = null;
        List<Phone> phones = null;
        List<Mail> mails = null;
        // 找到该用户关联的用户信息
        String contactId = user.getContactId();
        if (!StringUtils.isBlank(contactId)) {
            // 获取用户基础信息
            contact = contactMapper.selectById(contactId);
            // 获取用户存的手机号
            LambdaQueryWrapper<Phone> phoneQueryWrapper = new LambdaQueryWrapper<>();
            phoneQueryWrapper.eq(Phone::getContactId, contactId);
            phones = phoneMapper.selectList(phoneQueryWrapper);
            // 获取用户存的邮箱号
            LambdaQueryWrapper<Mail> mailLambdaQueryWrapper = new LambdaQueryWrapper<>();
            mailLambdaQueryWrapper.eq(Mail::getContactId, contactId);
            mails = mailMapper.selectList(mailLambdaQueryWrapper);
        }

        return new UserInfo(user,contact,phones,mails);
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
        // 创建用户时就给用户默认的信息
        Contact contact = new Contact(null, "匿名", DefaultHeadUtils.randomHead(),null,null,null,null,null);
        contactMapper.insert(contact);
        // 给新用户添加ID
        user.setContactId(contact.getId());
        int result = userMapper.insert(user);
        return result == 1 ? ResponseType.SUCCESS : ResponseType.FAIL;
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @return
     */
    @Override
    public ResponseType deleteUser(String id) {
        int result = userMapper.deleteById(id);
        return result == 1 ? ResponseType.SUCCESS : ResponseType.FAIL;
    }
}

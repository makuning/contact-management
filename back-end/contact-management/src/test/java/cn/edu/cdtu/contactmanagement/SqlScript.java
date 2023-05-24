package cn.edu.cdtu.contactmanagement;

import cn.edu.cdtu.contactmanagement.mapper.UserMapper;
import cn.edu.cdtu.contactmanagement.model.entity.*;
import cn.edu.cdtu.contactmanagement.model.info.ContactInfo;
import cn.edu.cdtu.contactmanagement.service.ContactService;
import cn.edu.cdtu.contactmanagement.service.GroupService;
import cn.edu.cdtu.contactmanagement.service.UserService;
import cn.edu.cdtu.contactmanagement.util.DefaultHeadUtils;
import cn.edu.cdtu.contactmanagement.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 数据库脚本
 * @Author makun
 * @Date 2023/5/24 9:44
 * @Version 1.0
 */
@SpringBootTest
public class SqlScript {
    @Autowired
    UserService userService;
    @Autowired
    GroupService groupService;
    @Autowired
    ContactService contactService;

    // @Test
    void generateData() {
        // 创建用户
        User user = new User();
        user.setUsername("moenya2");
        user.setPassword("123456");
        userService.createUser(user);
        String userId = user.getId();
        String userContentId = user.getContactId();
        // 修改用户基本信息
        Contact userContact = new Contact();
        userContact.setName("黄骏龙");
        userContact.setAddress("成都工业学院郫都校区6舍6282");
        userContact.setHead(DefaultHeadUtils.randomHead());
        userService.updateUserBaseInfo(userContact ,userId);
        // 添加用户手机号
        Phone phone = new Phone();
        phone.setValue("19960796666");
        userService.addPhone(phone, userId);
        phone = new Phone();
        phone.setValue("19960798888");
        userService.addPhone(phone, userId);
        // 添加用户邮箱
        Mail mail = new Mail();
        mail.setValue("25143333@qq.com");
        userService.addMail(mail, userId);
        mail = new Mail();
        mail.setValue("30662364@qq.com");
        userService.addMail(mail, userId);
        // 添加分组
        // 分组1
        Group group = new Group();
        group.setName("朋友");
        group.setUserId(userId);
        groupService.createGroup(group, userId);
        // 分组2
        Group group1 = new Group();
        group1.setName("家人");
        group1.setUserId(userId);
        groupService.createGroup(group1, userId);
        // 分组3
        Group group2 = new Group();
        group2.setName("同事");
        group2.setUserId(userId);
        groupService.createGroup(group2, userId);
        // 添加联系人
        ContactInfo contactInfo = null;
        Contact contact = null;
        for (int i = 0; i < 500; i++) {
            contactInfo = new ContactInfo();
            contact = new Contact();
            String groupId = null;
            switch (RandomUtil.randomInt(1, 3)) {
                case 1:
                    groupId = group.getId();
                    break;
                case 2:
                    groupId = group1.getId();
                    break;
                default:
                    groupId = group2.getId();
            }

            String name = null;
            switch (RandomUtil.randomInt(1, 7)) {
                case 1:
                    name = "黄骏龙";
                    break;
                case 2:
                    name = "马昆";
                    break;
                case 3:
                    name = "陈泓如";
                    break;
                case 4:
                    name = "陈飞";
                    break;
                case 5:
                    name = "陈邦辉";
                    break;
                case 6:
                    name = "高进秋";
                    break;
                case 7:
                    name = "雷欢";
                    break;
                default:
                    name = "马强";
                    break;
            }

            contact.setGroupId(groupId);
            contact.setHead(DefaultHeadUtils.randomHead());
            contact.setAddress("成都工业学院" + i);
            contact.setName(name + i);
            contact.setUserId(userId);
            contact.setRemark("认识的人" + i);
            contactInfo.setContact(contact);

            List<Phone> phones = new ArrayList<>();
            phones.add(new Phone(null, "19960796666", null, null, null));
            phones.add(new Phone(null, "19960788888", null, null, null));
            contactInfo.setPhones(phones);

            List<Mail> mails = new ArrayList<>();
            mails.add(new Mail(null, "2514333243@qq.com", null, null));
            mails.add(new Mail(null, "2343432112@qq.com", null, null));
            contactInfo.setMails(mails);

            contactService.createContact(contactInfo, userId);
        }
    }
}

package cn.edu.cdtu.contactmanagement.service.impl;

import cn.edu.cdtu.contactmanagement.mapper.*;
import cn.edu.cdtu.contactmanagement.model.entity.Contact;
import cn.edu.cdtu.contactmanagement.model.entity.Group;
import cn.edu.cdtu.contactmanagement.model.entity.Mail;
import cn.edu.cdtu.contactmanagement.model.entity.Phone;
import cn.edu.cdtu.contactmanagement.model.info.ContactInfo;
import cn.edu.cdtu.contactmanagement.service.ContactService;
import cn.edu.cdtu.contactmanagement.type.ResponseType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 联系人服务接口实现类
 * @Author makun
 * @Date 2023/5/23 14:52
 * @Version 1.0
 */
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private MailMapper mailMapper;

    /**
     * 获取某个组下所有联系人信息
     * @param groupId 群组ID
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<ContactInfo> getContactsByGroup(String groupId, String userId) {
        // 先检查这个组是否和用户匹配
        LambdaQueryWrapper<Group> groupLambdaQueryWrapper = new LambdaQueryWrapper<>();
        groupLambdaQueryWrapper.eq(Group::getId, groupId);
        groupLambdaQueryWrapper.eq(Group::getUserId, userId);
        Group group = groupMapper.selectOne(groupLambdaQueryWrapper);
        if (group == null) return null;

        // 如果匹配就检索
        LambdaQueryWrapper<Contact> contactLambdaQueryWrapper = new LambdaQueryWrapper<>();
        contactLambdaQueryWrapper.eq(Contact::getGroupId, groupId);
        List<Contact> contacts = contactMapper.selectList(contactLambdaQueryWrapper);

        List<ContactInfo> contactInfos = new ArrayList<>();
        // 迭代遍历获取手机号与邮箱号
        if (contacts != null && contacts.size() != 0) {
            String contactId = null;
            ContactInfo contactInfo = null;

            for (Contact contact : contacts) {
                // 创建详细信息对象，并附上基础信息
                contactInfo = new ContactInfo();
                contactInfo.setContact(contact);
                contactId = contact.getId();
                // 找手机号
                LambdaQueryWrapper<Phone> phoneLambdaQueryWrapper = new LambdaQueryWrapper<>();
                phoneLambdaQueryWrapper.eq(Phone::getContactId, contactId);
                contactInfo.setPhones(phoneMapper.selectList(phoneLambdaQueryWrapper));
                // 找邮箱
                LambdaQueryWrapper<Mail> mailLambdaQueryWrapper = new LambdaQueryWrapper<>();
                mailLambdaQueryWrapper.eq(Mail::getContactId, contactId);
                contactInfo.setMails(mailMapper.selectList(mailLambdaQueryWrapper));
                // 将详细信息对象装进返回的集合中
                contactInfos.add(contactInfo);
            }
        }

        return contactInfos;
    }

    /**
     * 获取联系人信息
     * @param contactId 联系人ID
     * @param userId 用户ID
     * @return
     */
    @Override
    public ContactInfo getContact(String contactId, String userId) {
        // 先看此联系人是否与用户匹配
        LambdaQueryWrapper<Contact> contactLambdaQueryWrapper = new LambdaQueryWrapper<>();
        contactLambdaQueryWrapper.eq(Contact::getId, contactId);
        contactLambdaQueryWrapper.eq(Contact::getUserId, userId);
        Contact contact = contactMapper.selectOne(contactLambdaQueryWrapper);
        if (contact == null) return null;   // 如果没有直接返回空

        // 创建详细信息对象，并附上基础信息
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setContact(contact);
        // 找手机号
        LambdaQueryWrapper<Phone> phoneLambdaQueryWrapper = new LambdaQueryWrapper<>();
        phoneLambdaQueryWrapper.eq(Phone::getContactId, contactId);
        contactInfo.setPhones(phoneMapper.selectList(phoneLambdaQueryWrapper));
        // 找邮箱
        LambdaQueryWrapper<Mail> mailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        mailLambdaQueryWrapper.eq(Mail::getContactId, contactId);
        contactInfo.setMails(mailMapper.selectList(mailLambdaQueryWrapper));
        return contactInfo;
    }

    /**
     * 获取所有联系人
     * @param id 用户ID
     * @return
     */
    @Override
    public List<ContactInfo> getAllContacts(String id) {
        // 先获取所有联系的基础信息
        LambdaQueryWrapper<Contact> contactLambdaQueryWrapper = new LambdaQueryWrapper<>();
        contactLambdaQueryWrapper.eq(Contact::getUserId, id);
        List<Contact> contacts = contactMapper.selectList(contactLambdaQueryWrapper);
        List<ContactInfo> contactInfos = new ArrayList<>();

        // 迭代遍历获取手机号与邮箱号
        if (contacts != null && contacts.size() != 0) {
            String contactId = null;
            ContactInfo contactInfo = null;

            for (Contact contact : contacts) {
                // 创建详细信息对象，并附上基础信息
                contactInfo = new ContactInfo();
                contactInfo.setContact(contact);
                contactId = contact.getId();
                // 找手机号
                LambdaQueryWrapper<Phone> phoneLambdaQueryWrapper = new LambdaQueryWrapper<>();
                phoneLambdaQueryWrapper.eq(Phone::getContactId, contactId);
                contactInfo.setPhones(phoneMapper.selectList(phoneLambdaQueryWrapper));
                // 找邮箱
                LambdaQueryWrapper<Mail> mailLambdaQueryWrapper = new LambdaQueryWrapper<>();
                mailLambdaQueryWrapper.eq(Mail::getContactId, contactId);
                contactInfo.setMails(mailMapper.selectList(mailLambdaQueryWrapper));
                // 将详细信息对象装进返回的集合中
                contactInfos.add(contactInfo);
            }
        }

        return contactInfos;
    }

    /**
     * 创建联系人
     * @param contactInfo 联系人详细信息
     * @param id 用户ID
     * @return
     */

    @Override
    public ResponseType createContact(ContactInfo contactInfo, String id) {
        // 先插入联系人基本信息
        Contact contact = contactInfo.getContact();
        contact.setUserId(id);
        contactMapper.insert(contact);

        String contactId = contact.getId();
        // 添加电话号码
        List<Phone> phones = contactInfo.getPhones();
        if (phones != null && phones.size() != 0) {
            for (Phone phone : phones) {
                phone.setContactId(contactId);
                phoneMapper.insert(phone);
            }
        }
        // 添加邮箱
        List<Mail> mails = contactInfo.getMails();
        if (mails != null && mails.size() != 0) {
            for (Mail mail : mails) {
                mail.setContactId(contactId);
                mailMapper.insert(mail);
            }
        }

        return ResponseType.SUCCESS;
    }
}

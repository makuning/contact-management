package cn.edu.cdtu.contactmanagement.service.impl;

import cn.edu.cdtu.contactmanagement.mapper.ContactMapper;
import cn.edu.cdtu.contactmanagement.mapper.MailMapper;
import cn.edu.cdtu.contactmanagement.mapper.PhoneMapper;
import cn.edu.cdtu.contactmanagement.mapper.UserMapper;
import cn.edu.cdtu.contactmanagement.model.entity.Contact;
import cn.edu.cdtu.contactmanagement.model.entity.Mail;
import cn.edu.cdtu.contactmanagement.model.entity.Phone;
import cn.edu.cdtu.contactmanagement.model.info.ContactInfo;
import cn.edu.cdtu.contactmanagement.service.ContactService;
import cn.edu.cdtu.contactmanagement.type.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private UserMapper userMapper;
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private MailMapper mailMapper;

    /**
     * 创建联系人
     * @param contactInfo
     * @param id
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

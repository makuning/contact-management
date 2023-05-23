package cn.edu.cdtu.contactmanagement.model.info;

import cn.edu.cdtu.contactmanagement.model.entity.Contact;
import cn.edu.cdtu.contactmanagement.model.entity.Mail;
import cn.edu.cdtu.contactmanagement.model.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 联系人信息
 * @Author makun
 * @Date 2023/5/23 14:55
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {
    private Contact contact;    // 联系人基础信息
    private List<Phone> phones; // 所有的电话信息
    private List<Mail> mails;   // 所有的邮箱信息
}

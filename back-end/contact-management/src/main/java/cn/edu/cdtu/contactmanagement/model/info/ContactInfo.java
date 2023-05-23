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
    private Contact contact;
    private List<Phone> phones;
    private List<Mail> mails;
}

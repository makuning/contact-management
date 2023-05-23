package cn.edu.cdtu.contactmanagement.model.info;

import cn.edu.cdtu.contactmanagement.model.entity.Contact;
import cn.edu.cdtu.contactmanagement.model.entity.Mail;
import cn.edu.cdtu.contactmanagement.model.entity.Phone;
import cn.edu.cdtu.contactmanagement.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @Description 用户详细信息
 * @Author makun
 * @Date 2023/5/22 13:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private User user;
    private Contact contact;
    private List<Phone> phones;
    private List<Mail> mails;
}

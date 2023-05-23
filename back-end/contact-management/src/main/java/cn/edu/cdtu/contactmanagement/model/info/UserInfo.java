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
    private User user;      // 基本用户信息
    private Contact contact;    // 用户关联的基本联系信息
    private List<Phone> phones; // 用户的手机号
    private List<Mail> mails;   // 用户的邮箱
}

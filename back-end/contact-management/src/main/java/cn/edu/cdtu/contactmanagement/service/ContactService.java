package cn.edu.cdtu.contactmanagement.service;

import cn.edu.cdtu.contactmanagement.model.info.ContactInfo;
import cn.edu.cdtu.contactmanagement.type.ResponseType;

/**
 * @Description 联系人服务接口
 * @Author makun
 * @Date 2023/5/23 14:52
 * @Version 1.0
 */

public interface ContactService {
    /**
     * 添加联系人信息
     * @param contactInfo
     * @param id
     * @return
     */
    ResponseType createContact(ContactInfo contactInfo, String id);
}

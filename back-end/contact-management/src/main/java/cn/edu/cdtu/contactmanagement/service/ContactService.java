package cn.edu.cdtu.contactmanagement.service;

import cn.edu.cdtu.contactmanagement.model.info.ContactInfo;
import cn.edu.cdtu.contactmanagement.type.ResponseType;

import java.util.List;

/**
 * @Description 联系人服务接口
 * @Author makun
 * @Date 2023/5/23 14:52
 * @Version 1.0
 */

public interface ContactService {
    /**
     * 获取某个组下所有的联系人信息
     * @param groupId
     * @param userId
     * @return
     */
    List<ContactInfo> getContactsByGroup(String groupId, String userId);

    /**
     * 获取联系人信息
     * @param contactId
     * @param userId
     * @return
     */
    ContactInfo getContact(String contactId, String userId);

    /**
     * 获取所有联系人
     * @param id
     * @return
     */
    List<ContactInfo> getAllContacts(String id);

    /**
     * 添加联系人信息
     * @param contactInfo
     * @param id
     * @return
     */
    ResponseType createContact(ContactInfo contactInfo, String id);
}

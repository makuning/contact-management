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
     * @param groupId 群组ID
     * @param userId 用户ID
     * @return
     */
    List<ContactInfo> getContactsByGroup(String groupId, String userId);

    /**
     * 获取联系人信息
     * @param contactId 联系人ID
     * @param userId 用户ID
     * @return
     */
    ContactInfo getContact(String contactId, String userId);

    /**
     * 获取所有联系人
     * @param id 用户ID
     * @return
     */
    List<ContactInfo> getAllContacts(String id);

    /**
     * 添加联系人信息
     * @param contactInfo 联系人详细信息
     * @param id 用户ID
     * @return
     */
    ResponseType createContact(ContactInfo contactInfo, String id);
}

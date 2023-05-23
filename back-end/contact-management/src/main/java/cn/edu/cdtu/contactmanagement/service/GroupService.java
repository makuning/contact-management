package cn.edu.cdtu.contactmanagement.service;

import cn.edu.cdtu.contactmanagement.model.entity.Group;
import cn.edu.cdtu.contactmanagement.type.ResponseType;
import java.util.List;

/**
 * @Description 群组模块服务接口
 * @Author makun
 * @Date 2023/5/23 16:03
 * @Version 1.0
 */
public interface GroupService {
    /**
     * 删除群组
     * @param groupId
     * @param userId
     * @return
     */
    ResponseType deleteGroup(String groupId, String userId);

    /**
     * 将联系人加入到组中
     * @param groupId
     * @param contactId
     * @param userId
     * @return
     */
    ResponseType joinGroup(String groupId, String contactId, String userId);

    /**
     * 获取所有群组
     * @param id
     * @return
     */
    List<Group> getAllGroups(String id);

    /**
     * 创建群组
     * @param group
     * @param id
     * @return
     */
    ResponseType createGroup(Group group, String id);
}

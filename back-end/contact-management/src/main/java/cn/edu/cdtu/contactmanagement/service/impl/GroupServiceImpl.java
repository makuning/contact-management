package cn.edu.cdtu.contactmanagement.service.impl;

import cn.edu.cdtu.contactmanagement.mapper.ContactMapper;
import cn.edu.cdtu.contactmanagement.mapper.GroupMapper;
import cn.edu.cdtu.contactmanagement.model.entity.Contact;
import cn.edu.cdtu.contactmanagement.model.entity.Group;
import cn.edu.cdtu.contactmanagement.service.GroupService;
import cn.edu.cdtu.contactmanagement.type.ResponseType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description 群组服务接口实现类
 * @Author makun
 * @Date 2023/5/23 16:04
 * @Version 1.0
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private ContactMapper contactMapper;

    /**
     * 删除群组
     * @param groupId
     * @param userId
     * @return
     */
    @Override
    public ResponseType deleteGroup(String groupId, String userId) {
        // 先判断该组是否是用户的
        LambdaQueryWrapper<Group> groupLambdaQueryWrapper = new LambdaQueryWrapper<>();
        groupLambdaQueryWrapper.eq(Group::getUserId, userId);
        groupLambdaQueryWrapper.eq(Group::getId, groupId);
        Group group = groupMapper.selectOne(groupLambdaQueryWrapper);

        // 如果不为空，说明认证通过
        groupMapper.deleteById(groupId);
        return ResponseType.SUCCESS;
    }

    /**
     * 将联系人加入到组中
     * @param groupId
     * @param contactId
     * @param userId
     * @return
     */
    @Override
    public ResponseType joinGroup(String groupId, String contactId, String userId) {
        // 先查看群组是否是该用户的
        LambdaQueryWrapper<Group> groupLambdaQueryWrapper = new LambdaQueryWrapper<>();
        groupLambdaQueryWrapper.eq(Group::getUserId, userId);
        groupLambdaQueryWrapper.eq(Group::getId, groupId);
        Group group = groupMapper.selectOne(groupLambdaQueryWrapper);
        // 再查看联系人是否是用户的
        LambdaQueryWrapper<Contact> contactLambdaQueryWrapper = new LambdaQueryWrapper<>();
        contactLambdaQueryWrapper.eq(Contact::getId, contactId);
        contactLambdaQueryWrapper.eq(Contact::getUserId, userId);
        Contact contact = contactMapper.selectOne(contactLambdaQueryWrapper);

        // 如果有一个为空就不能执行
        if (group == null || contact == null) return null;

        // 更新联系人
        contact.setGroupId(groupId);
        contactMapper.updateById(contact);

        return ResponseType.SUCCESS;
    }

    /**
     * 获取所有群组
     * @param id
     * @return
     */
    @Override
    public List<Group> getAllGroups(String id) {
        LambdaQueryWrapper<Group> groupLambdaQueryWrapper = new LambdaQueryWrapper<>();
        groupLambdaQueryWrapper.eq(Group::getUserId, id);
        return groupMapper.selectList(groupLambdaQueryWrapper);
    }

    /**
     * 创建群组
     * @param group
     * @param id
     * @return
     */
    @Override
    public ResponseType createGroup(Group group, String id) {
        group.setUserId(id);
        groupMapper.insert(group);
        return ResponseType.SUCCESS;
    }
}

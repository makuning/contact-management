package cn.edu.cdtu.contactmanagement.controller;

import cn.edu.cdtu.contactmanagement.model.entity.Group;
import cn.edu.cdtu.contactmanagement.model.info.JsonResult;
import cn.edu.cdtu.contactmanagement.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 群组相关功能接口
 * @Author makun
 * @Date 2023/5/23 16:02
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/pri/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    /**
     * 删除群组
     * @param request 获取token
     * @param id 群组ID
     * @return
     */
    @DeleteMapping("/{id}")
    public JsonResult deleteGroup(HttpServletRequest request, @PathVariable String id) {
        return new JsonResult(groupService.deleteGroup(id, request.getAttribute("user_id").toString()), null, "删除成功");
    }

    /**
     * 将联系人添加到群组
     * @param request 获取token
     * @param groupId 群组ID
     * @param contactId 联系人ID
     * @return
     */
    @PutMapping("/{groupId}/{contactId}")
    public JsonResult contactJoinGroup(HttpServletRequest request,@PathVariable String groupId, @PathVariable String contactId) {
        return new JsonResult(groupService.joinGroup(groupId, contactId, request.getAttribute("user_id").toString()), null, "添加成功");
    }

    /**
     * 获取所有群组
     * @param request 获取token
     * @return
     */
    @GetMapping
    public JsonResult getGroups(HttpServletRequest request) {
        return JsonResult.success(groupService.getAllGroups(request.getAttribute("user_id").toString()),"获取成功");
    }

    /**
     * 创建群组
     * @param request 获取token
     * @param group 群组信息
     * @return
     */
    @PostMapping
    public JsonResult createGroup(HttpServletRequest request, @RequestBody Group group) {
        groupService.createGroup(group, request.getAttribute("user_id").toString());
        return JsonResult.success(null,"创建成功");
    }
}

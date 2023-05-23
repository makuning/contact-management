package cn.edu.cdtu.contactmanagement.controller;

import cn.edu.cdtu.contactmanagement.model.info.ContactInfo;
import cn.edu.cdtu.contactmanagement.model.info.JsonResult;
import cn.edu.cdtu.contactmanagement.service.ContactService;
import cn.edu.cdtu.contactmanagement.type.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 联系人模块
 * @Author makun
 * @Date 2023/5/23 14:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/pri/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    /**
     * 根据群组获取联系人信息
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/group/{id}")
    public JsonResult getContactsByGroup(HttpServletRequest request, @PathVariable String id) {
        return JsonResult.success(contactService.getContactsByGroup(id, request.getAttribute("user_id").toString()),"获取成功");
    }

    /**
     * 获取某个联系人的详细信息
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/{id}")
    public JsonResult getContact(@PathVariable String id, HttpServletRequest request) {
        return JsonResult.success(contactService.getContact(id, request.getAttribute("user_id").toString()),"获取成功");
    }

    /**
     * 获取所有联系人
     * @param request
     * @return
     */
    @GetMapping
    public JsonResult getAllContacts(HttpServletRequest request) {
        return JsonResult.success(contactService.getAllContacts(request.getAttribute("user_id").toString()),"获取成功");
    }

    /**
     * 创建联系人
     * @param request
     * @param contactInfo
     * @return
     */
    @PostMapping()
    public JsonResult createContact(HttpServletRequest request, @RequestBody ContactInfo contactInfo) {
        ResponseType code = contactService.createContact(contactInfo, request.getAttribute("user_id").toString());
        return new JsonResult(code, null, "创建成功");
    }
}

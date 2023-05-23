package cn.edu.cdtu.contactmanagement.controller;

import cn.edu.cdtu.contactmanagement.model.info.ContactInfo;
import cn.edu.cdtu.contactmanagement.model.info.JsonResult;
import cn.edu.cdtu.contactmanagement.service.ContactService;
import cn.edu.cdtu.contactmanagement.type.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

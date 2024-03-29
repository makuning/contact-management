package cn.edu.cdtu.contactmanagement.controller;

import cn.edu.cdtu.contactmanagement.model.entity.Contact;
import cn.edu.cdtu.contactmanagement.model.entity.Mail;
import cn.edu.cdtu.contactmanagement.model.entity.Phone;
import cn.edu.cdtu.contactmanagement.model.entity.User;
import cn.edu.cdtu.contactmanagement.model.info.JsonResult;
import cn.edu.cdtu.contactmanagement.service.UserService;
import cn.edu.cdtu.contactmanagement.type.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 用户业务相关接口
 * @Author makun
 * @Date 2023/5/22 16:51
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/pri/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注销用户
     * @param request 获取token
     * @return
     */
    @DeleteMapping
    public JsonResult deleteUser(HttpServletRequest request) {
        return new JsonResult(userService.deleteUser(request.getAttribute("user_id").toString()), null, "注销成功");
    }

    /**
     * 添加邮箱
     * @param request 获取token
     * @param mail 邮箱信息
     * @return
     */
    @PostMapping("/mail")
    public JsonResult addMail(HttpServletRequest request, @RequestBody Mail mail) {
        return new JsonResult(userService.addMail(mail, request.getAttribute("user_id").toString()),null,"添加成功");
    }

    /**
     * 添加手机号
     * @param request 获取token
     * @param phone 电话信息
     * @return
     */
    @PostMapping("/phone")
    public JsonResult addPhone(HttpServletRequest request, @RequestBody Phone phone) {
        return new JsonResult(userService.addPhone(phone, request.getAttribute("user_id").toString()),null, "添加成功");
    }

    /**
     * 更新用户基础信息
     * @param request 获取token
     * @param contact 用户基础信息
     * @return
     */
    @RequestMapping(value = "/base",method = RequestMethod.PUT)
    public JsonResult baseInfoUpdate(HttpServletRequest request, @RequestBody Contact contact) {
        return new JsonResult(userService.updateUserBaseInfo(contact, request.getAttribute("user_id").toString()),null,"更新成功");
    }

    /**
     * 注册
     * @param user 用户信息
     * @return
     */
    @PostMapping("/register")
    public JsonResult register(@RequestBody User user) {
        ResponseType code = userService.createUser(user);
        String msg = "未知错误";
        switch (code) {
            case SUCCESS:
                msg = "注册成功，请登录！";
                break;
            case FAIL:
                msg = "注册失败！";
                break;
            case ERROR:
                msg = "系统错误！";
                break;
        }
        return new JsonResult(code,null,msg);
    }

    /**
     * 登录
     * @param user 接收请求体中的登录信息
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody User user) {
        String token = userService.login(user.getUsername(), user.getPassword());
        return token == null ? JsonResult.fail(null, "登录失败") : JsonResult.success(token, "登录成功");
    }

    /**
     * 获取用户的详细信息
     * @param request 用于找到用户ID，存放在token中
     * @return
     */
    @GetMapping()
    public JsonResult userInfo(HttpServletRequest request) {
        return JsonResult.success(userService.getUserDetailInfo(request.getAttribute("user_id").toString()), "查询成功");
    }
}

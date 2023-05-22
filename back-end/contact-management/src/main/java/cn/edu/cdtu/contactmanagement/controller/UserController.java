package cn.edu.cdtu.contactmanagement.controller;

import cn.edu.cdtu.contactmanagement.model.entity.User;
import cn.edu.cdtu.contactmanagement.model.info.JsonResult;
import cn.edu.cdtu.contactmanagement.service.UserService;
import cn.edu.cdtu.contactmanagement.type.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
     * 注册
     * @param user
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

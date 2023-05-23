package cn.edu.cdtu.contactmanagement.intercept;

import cn.edu.cdtu.contactmanagement.model.info.JsonResult;
import cn.edu.cdtu.contactmanagement.util.JWTUtil;
import cn.edu.cdtu.contactmanagement.util.JsonUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description 登录切片
 * @Author makun
 * @Date 2023/5/22 14:50
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 在调用controller之前会调用此方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader("token");
            if (token == null) {
                token = request.getParameter("token");
            }

            if (StringUtils.isNotBlank(token)) {
                Claims claims = JWTUtil.checkJWT(token);
                if (claims == null) {
                    sendJsonMsg(response, JsonResult.fail(null,"登录失效，请重新登录!"));
                    return false;
                } else {
                    String id = claims.get("id").toString();
                    String name = (String) claims.get("name");
                    request.setAttribute("user_id",id);
                    request.setAttribute("user_name",name);
                    return true;
                }
            }
            sendJsonMsg(response, JsonResult.fail(null,"未登录，请先登录!"));
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void sendJsonMsg(HttpServletResponse response, Object object) {
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(JsonUtil.toJson(object));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

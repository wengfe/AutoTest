package com.course.server;

import com.course.bean.User;
import com.sun.deploy.net.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jboss.logging.Param;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.CookieStore;

@RestController
@Api(value = "/", description = "这是 post 方法")
@RequestMapping("/v1") //给以下所有方法添加路径头 /v1
public class MyPostMethod {
    private Cookie cookie;
    private CookieStore store;

    @RequestMapping(path = {"/login"}, method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，获取 cookie", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName") String name,
                        @RequestParam(value = "password") String password){
        if (name.equals("zhangsan") && password.equals("123456")){
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "登陆成功";
        }
        return "账号 or 密码错误";
    }
    
    @RequestMapping(path = {"getUserList"}, method = RequestMethod.POST)
    @ApiOperation(value = "返回 用户列表", httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User user){
        Cookie[] cookies = request.getCookies();
        User user1;
        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals("login")
                    && cookie.getValue().equals("true")
                    && user.getName().equals("zhangsan")
                    && user.getPassword().equals("123456")){
                user1 = new User();
                user1.setName("liSI");
                user1.setAge("19");
                user1.setPassword("456789");
                user1.setSex("M");

                return user1.toString();
            }

        }

        return "参数不合法";
    }
}

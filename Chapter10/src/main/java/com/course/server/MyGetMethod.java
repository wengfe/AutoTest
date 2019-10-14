package com.course.server;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
// swagger 在类名上的注释
@Api(value = "/", description = "这是我全部的 GET 方法")
public class MyGetMethod {
    @RequestMapping(path = {"/getcookie"}, method = RequestMethod.GET)
    @ApiOperation(value = "通过此方法获取 cookie", httpMethod = "GET")
    public String getCookie(HttpServletResponse response){
//        HTTPServletRequest 装请求信息的类
//        HTTPServletResponse 装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "Spring Boot Get For Cookie";
    }

    /**
     * 需携带 cookie 信息，才能访问成功
     */

    @RequestMapping(path = {"/get/with/cookie"})
//    Swagger 对方法的描述，需要添加 http 方法
    @ApiOperation(value = "需携带 cookie 才会返回成功", httpMethod = "GET")
    public String getWithCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "您未登录";
        }
        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")){
                return "登录成功";
            }
        }

        return "未登录成功";
    }

    /**
     * 携带参数的两种 GET 请求方式
     * 第一种实现方式 url: key=value&key=value
     */

    @RequestMapping(path = {"/get/with/param"})
    @ApiOperation(value = "携带参数的 Get 方法一", httpMethod = "GET")
    public Map<String, Integer> getWithParam(@RequestParam Integer start,
                                             @RequestParam Integer end){
        Map<String, Integer> myList = new HashMap<>();
        myList.put("小培培", 10);
        myList.put("小露露", 11);
        myList.put("小莎莎", 12);

        return myList;
    }


    /**
     *
     * 第二种需要携带参数访问的get请求
     * url:ip:port/get/with/param/10/20
     */
    @RequestMapping(path = {"/get/with/param/{start}/{end}"})
    @ApiOperation(value = "携带参数的 GET 方法二", httpMethod = "GET")
    public Map getWithPathParam(@PathVariable Integer start,
                                @PathVariable Integer end){
        Map<String, Integer> myList = new HashMap<>();
        myList.put("小培培", 10);
        myList.put("小露露", 11);
        myList.put("小莎莎", 12);

        return myList;
    }

}

package com.course.usertest.controller;

import com.course.usertest.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Log4j2
@RestController
@Api(value = "v1", description = "用户管理系统")
@RequestMapping("v1")
public class UserController {

    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登录接口", httpMethod = "POST")
    @RequestMapping(path = {"/login"}, method = RequestMethod.POST)
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        int i = template.selectOne("login", user);
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        log.info("查询到的结果是： " + i);
//        log.info("查询到的结果是： " + i);
        if (i==1){
            log.info(("登录的用户是： " + user.getUserName()));
            return true;
        }
        return false;
    }

    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    @RequestMapping(path = {"/addUser"}, method = RequestMethod.POST)
    public Boolean addUser(HttpServletRequest request, @RequestBody User user){
        Boolean isLogin = verifyCookie(request);
        int result = 0;
        if (isLogin != null){
            result = template.insert("addUser", user);
        }
        if (result>0){
            log.info("添加的用户数量为： " + result);
            return true;
        }
        return false;
    }

    private Boolean verifyCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            log.info("cookies 为空");
            return false;
        }
        for (Cookie cookie:cookies){
            if ((cookie.getName().equals("login")) &&cookie.getValue().equals("true")){
                log.info("cookie 验证通过");
                return true;
            }
        }

        return false;
    }


    @ApiOperation(value = "获取用户(列表)信息接口",httpMethod = "POST")
    @RequestMapping(path = {"getUserInfoList"}, method = RequestMethod.POST)
    public List<User> getUserInfoList(HttpServletRequest request, @RequestBody User user){
        Boolean isLogin = verifyCookie(request);
        if(isLogin==true){
            List<User> users = template.selectList("getUserInfo",user);
            log.info("getUserInfo获取到的用户数量是" +users.size());
            return users;
        }else {
            return null;
        }
    }

    @ApiOperation(value = "更新/删除用户接口",httpMethod = "POST")
    @RequestMapping(path = {"updateUserInfo"}, method = RequestMethod.POST)
    public int updateUserInfo(HttpServletRequest request, @RequestBody User user){
        Boolean isLogin = verifyCookie(request);
        int i = 0;
        if (isLogin == true){
            i = template.update("updateUserInfo", user);
        }
        log.info("更新数据的条目数为： " + i);
        return i;
    }
}

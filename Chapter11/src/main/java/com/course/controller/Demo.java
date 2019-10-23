package com.course.controller;

import com.course.model.User;
import com.course.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/v2", description = "通过 MyBatis 获取数据库信息")
public class Demo {
    @Autowired
    UserService userService;

    User user;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ApiOperation(value = "通过用户 id 获取 用户信息", httpMethod = "GET")
    public String getUserById(@RequestParam("id") int id){
        user = userService.getUserById(id);
        if(user != null){
            return user.toString();
        }

        return "未找到该用户";
    }
}

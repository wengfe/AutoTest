package com.course.controller;

import com.course.model.User;
import com.course.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(path = {"/addUser"}, method = RequestMethod.POST)
    @ApiOperation(value = "通过 Post 方法插入用户", httpMethod = "POST")
    public String addUser(@RequestBody User user){
        if (userService.addUser(user) > 0){
            return "插入用户："+ user.getName() + "成功";
        }
        return "用户增加失败";
    }

    @RequestMapping(path = {"updateUserSex"}, method = RequestMethod.POST)
    @ApiOperation(value = "通过 id 更新用户性别", httpMethod = "POST")
    public String updateUserSexById(@RequestParam("sex") String sex,
                                    @RequestParam("id") int id){
        if (userService.updateUserSexById(sex, id) > 0){
            return "修改成功";
        }
        return  "修改失败";
    }

    @RequestMapping(path = {"deleteUser"}, method = RequestMethod.GET)
    @ApiOperation(value = "通过id删除用户", httpMethod = "GET")
    public String deleteById(@RequestParam int id){
        if (userService.deleteUserById(id) > 0 ){
            return "操作成功";
        }
        return "删除失败";
    }
}

package com.course.utils;


import com.course.model.InterfaceName;

import java.util.ResourceBundle;

/**
 * 拼接 url 的工具类
 */
public class ConfigFile {
    private static ResourceBundle bundle = ResourceBundle.getBundle("application");

    public static String getUrl(InterfaceName name) {
        String address = bundle.getString("test.url");
        String uri = "";
        String testUrl;

        if (name == InterfaceName.GETUSERINFO) {
            uri = bundle.getString("getUserInfo.uri");
        }
        if (name == InterfaceName.ADDUSERINFO) {
            uri = bundle.getString("addUser.uri");
        }
        if (name == InterfaceName.GETUSERLIST) {
            uri = bundle.getString("getUserList.uri");
        }
        if (name == InterfaceName.LOGIN) {
            uri = bundle.getString("login.uri");
        }
        if (name == InterfaceName.UPDATEUSERINFO) {
            uri = bundle.getString("updateUserInfo.uri");
        }

        testUrl = address + uri;
        return testUrl;
    }
}

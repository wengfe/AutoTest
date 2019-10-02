package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupOnMethod {
    @Test(groups = "server")
    public void groupOnMethodServer1(){
        System.out.println("这是方法分组 服务端用例 1");
    }

    @Test(groups = "server")
    public void groupOnMethodServer2(){
        System.out.println("这是方法分组 服务端用例 2");
    }

    @Test(groups = "client")
    public void groupOnMethodClient1(){
        System.out.println("这是方法分组 客户端用例 1");
    }

    @Test(groups = "client")
    public void groupOnMethodClient2(){
        System.out.println("这是方法分组 客户端用例 2");
    }

    @BeforeGroups(groups = "server")
    public void beforeGroupServerTest(){
        System.out.println("服务端测试用例运行之前");
    }

    @AfterGroups(groups = "server")
    public void afterGroupServerTest(){
        System.out.println("服务端测试用例运行之后");
    }
}

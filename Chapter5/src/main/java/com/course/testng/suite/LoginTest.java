package com.course.testng.suite;

import org.testng.annotations.*;

public class LoginTest {

    @Test
    public void checkCode(){
        System.out.println("验证码验证通过");
    }

    @Test
    public void login(){
        System.out.println("淘宝网登陆成功");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Login Class ");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After Login Class ");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod login ");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod login ");
    }
}

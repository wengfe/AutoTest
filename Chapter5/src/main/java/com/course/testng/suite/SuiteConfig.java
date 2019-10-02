package com.course.testng.suite;

import org.testng.annotations.*;

public class SuiteConfig {
    @BeforeTest
    public void beforeTest(){
        System.out.println("BEFORE TEST");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("AFTER TEST");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("淘宝购买测试套件启动。。。");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("淘宝购买测试套件清除。。。");
    }
}

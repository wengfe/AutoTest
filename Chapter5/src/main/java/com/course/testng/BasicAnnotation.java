package com.course.testng;


import org.testng.annotations.*;

public class BasicAnnotation {

//    基本的注解，用来把方法标记为测试的一部分
    @Test
    public void testCase1(){
        System.out.println("这是测试用例1");
    }

    @Test
    public void testCase2(){
        System.out.println("这是测试用例2");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod ing");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod ing");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("BeforeClass ing");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("AfterClass ing");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite ing");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite ing");
    }
}

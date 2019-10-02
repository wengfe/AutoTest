package com.course.testng.suite;

import org.testng.annotations.*;

public class PaySuccess {
    @Test
    public void choicePay(){
        System.out.println("确认支付");
    }

    @Test
    public void paySuccess(){
        System.out.println("支付宝支付成功");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Before pay Class ");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After pay Class ");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod pay ");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod pay ");
    }
}

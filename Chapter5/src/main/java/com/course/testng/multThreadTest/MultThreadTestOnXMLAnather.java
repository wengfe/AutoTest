package com.course.testng.multThreadTest;

import org.testng.annotations.Test;

public class MultThreadTestOnXMLAnather {

    @Test
    public void Test1(){
        System.out.printf("当前 Class 2 test1 线程: %s\n", Thread.currentThread().getId());
    }

    @Test
    public void Test2(){
        System.out.printf("当前 Class 2 test2 线程: %s\n", Thread.currentThread().getId());
    }

    @Test
    public void Test3(){
        System.out.printf("当前 Class 2 test3 线程: %s\n", Thread.currentThread().getId());
    }
}

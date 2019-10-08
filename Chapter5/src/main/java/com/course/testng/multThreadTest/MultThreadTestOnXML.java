package com.course.testng.multThreadTest;

import org.testng.annotations.Test;

public class MultThreadTestOnXML {
    @Test
    public void test1(){
        System.out.printf("当前 Class 1 test1 线程: %s\n", Thread.currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.printf("当前 Class 1 test2 线程: %s\n", Thread.currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.printf("当前 Class 1 test3 线程: %s\n", Thread.currentThread().getId());
    }
}

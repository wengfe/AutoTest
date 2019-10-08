package com.course.testng.paramterTest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProvider {
    @Test(dataProvider = "provider")
    public void paramTest1(String name, int age){
        System.out.println("name: " + name + " " + "age: " + age);
    }

    @org.testng.annotations.DataProvider(name = "provider")
    public Object[][] dataProviderTest(){
        Object[][] o = {
                {"zhangsan", 10},
                {"lisi", 10}
        };
        return o;
    }

    @Test(dataProvider = "methodData")
    public void test1(String name, int age){
        System.out.println("name: " + name + " " + "age: " + age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name, int age){
        System.out.println("name: " + name + " " + "age: " + age);
    }

    @org.testng.annotations.DataProvider(name = "methodData")
    // 在方法上加入一个 java.lang.reflect.Method 的形参
    public Object[][] methodTest(Method method){
        Object[][] result = null;
        //通过映射获取当前测试方法名，来进行传参
        if (method.getName().equals("test1")){
            result = new Object[][]{
                    {"test1", 11111},
                    {"test11", 111111}
            };
        }else if (method.getName().equals("test2")){
            result = new Object[][]{
                    {"test2", 2222},
                    {"test22", 222222}
            };
        }
        return result;
    }
}

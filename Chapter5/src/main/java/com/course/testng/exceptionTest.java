package com.course.testng;

import org.testng.annotations.Test;
import sun.tools.java.RuntimeConstants;

public class exceptionTest {
    /**
     * 在期望结果为某一个异常的时候
     * 例： 传入了不合法的参数，程序抛出异常
     * 即测试用例的预期结果是这个异常
     */

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFaild(){
        System.out.println(" 这是一个失败的异常测试");
    }

    public void throwException(){
        throw new RuntimeException();
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExcuptionSuccess(){
        throwException();
        System.out.println(" 这是一个成功的异常测试");

    }
}

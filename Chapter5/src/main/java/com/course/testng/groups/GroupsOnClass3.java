package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnClass3 {
    public void teacher1(){
        System.out.println("GroupOnClass 3 中的 teacher 1");
    }

    public void teacher2(){
        System.out.println("GroupOnClass 3 中的 teacher 2");
    }

}

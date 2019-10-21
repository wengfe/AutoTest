package com.course.bean;

import lombok.Data;

//引用 lombok 自动重写 Bean 中的 Get Set toString 等方法
@Data
public class User {
    private String name;
    private String age;
    private String sex;
    private String password;
}

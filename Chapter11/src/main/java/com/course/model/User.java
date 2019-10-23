package com.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//全参构造
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String name;
    private int age;
    private String sex;

}

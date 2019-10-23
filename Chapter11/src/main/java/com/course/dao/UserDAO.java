package com.course.dao;

import com.course.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface UserDAO {
    User getUserById(int id);
}

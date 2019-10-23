package com.course.service;

import com.course.dao.UserDAO;
import com.course.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public User getUserById(int id){
        return userDAO.getUserById(id);
    }
}

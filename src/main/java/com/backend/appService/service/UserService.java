package com.backend.appService.service;

import com.backend.appService.auth.model.User;
import com.backend.appService.dao.UserDAO;
import com.backend.appService.entity.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO dao;

    public void save(User user) throws SQLException {
        dao.save(user);
    }

    public List<Master> findRoles(){
        return dao.findAllRole();
    }
}

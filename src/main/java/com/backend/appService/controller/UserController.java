package com.backend.appService.controller;

import com.backend.appService.auth.model.User;
import com.backend.appService.entity.Master;
import com.backend.appService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(path = "/save")
    public ResponseEntity<?> save(
            @RequestBody User user
    ){
        try {
            service.save(user);
            return ResponseEntity.ok().body("berhasil");
        } catch (SQLException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/roles")
    public ResponseEntity<?> findRoles(){
        try{
            List<Master> master = service.findRoles();
            return ResponseEntity.ok().body(master);
        } catch (DataAccessException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

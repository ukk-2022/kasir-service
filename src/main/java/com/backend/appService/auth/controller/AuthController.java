package com.backend.appService.auth.controller;

import com.backend.appService.auth.dao.Login;
import com.backend.appService.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping(path = "/api/auth")
public class AuthController {

    @Autowired
    private Login login;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) throws Exception {
        User nasabahResponse = new User();
//        NasabahDTO.DataNasabah dataNasabah = new NasabahDTO.DataNasabah();
        if (user != null) {
            String username = user.getUsername();
            User useradmindb = login.getUserByUsername(username);
//            System.out.println(useradmindb);
            if (useradmindb != null) {
                if (Objects.equals(username, useradmindb.getUsername())){
                    String password = user.getPassword();
                    if (Objects.equals(password, useradmindb.getPassword())) {
                        String token = UUID.randomUUID().toString();
                        nasabahResponse.setResponseMessage("Berhasil Login");
                        nasabahResponse.setId(useradmindb.getId());
                        nasabahResponse.setIdRole(useradmindb.getIdRole());
                        nasabahResponse.setTokenKey(token);
                        nasabahResponse.setIdRole(useradmindb.getIdRole());
                        login.addInfo(nasabahResponse);
                    } else {
                        nasabahResponse.setResponseMessage("Password yang Anda Masukkan Salah");
                    }
                } else {
                    nasabahResponse.setResponseMessage("Username yang Anda Input Tidak Valid");
                }
            } else {
                nasabahResponse.setResponseMessage("User Tidak Ditemukan");
            }
        } else {
            nasabahResponse.setResponseMessage("User Tidak Ditemukan");
        }
        return ResponseEntity.ok().body(nasabahResponse);
    }

    @PostMapping("/checking")
    public ResponseEntity<?> checkingActive(@RequestBody String token){
        try{
            User data = login.getNipByToken(token);
            return ResponseEntity.ok().body(data);
        } catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/logout/{token}")
    public ResponseEntity<?> logout(@PathVariable("token") String token){
        try{
            login.logout(token);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (DataAccessException e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

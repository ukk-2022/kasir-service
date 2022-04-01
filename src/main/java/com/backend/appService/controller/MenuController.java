package com.backend.appService.controller;

import com.backend.appService.entity.Menu;
import com.backend.appService.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(path = "/api/menu")
public class MenuController {

    @Autowired
    private MenuService service;

    @PostMapping(path = "/save")
    public ResponseEntity<?> save(
            @RequestBody Menu menu
    ){
        try{
            service.save(menu);
            return ResponseEntity.ok().body("berhasil");
        } catch (SQLException throwables) {
            return new ResponseEntity<>(throwables.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/tambah-stok")
    public ResponseEntity<?> tambahStok(
            @RequestBody Menu menu
    ){
        try{
            service.tambahStok(menu);
            return ResponseEntity.ok().body("berhasil");
        } catch (SQLException throwables) {
            return new ResponseEntity<>(throwables.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<?> findAll(){
        try{
            List<Menu> data = service.findAll();
            return ResponseEntity.ok().body(data);
        } catch (DataAccessException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = "/findByCategory")
    public ResponseEntity<?> findByCategory(
            @RequestParam Integer idKategori
    ){
        try{
            List<Menu> data = service.findByKategori(idKategori);
            return ResponseEntity.ok().body(data);
        } catch (DataAccessException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

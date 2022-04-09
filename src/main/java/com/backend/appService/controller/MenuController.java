package com.backend.appService.controller;

import com.backend.appService.entity.Menu;
import com.backend.appService.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<?> findById(
            @PathVariable Integer id
    ){
        try{
            Optional<Menu> data = service.findById(id);
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

    @PostMapping("/filesupload")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = null;
        Map<String, Object> pesan = new HashMap<>();
        try {
            String namaFile = service.uploadFile(file);
            pesan.put("file", namaFile);
            return ResponseEntity.ok().body(pesan);
        } catch (Exception exception) {
            pesan.put("pesan", "cannot input file");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(pesan);
        }
    }

    @GetMapping(value = "/file/{id}")
    public ResponseEntity<InputStreamResource>getImage(@PathVariable("id") String id){
        try{
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(
                    new InputStreamResource( service.load(id).getInputStream() ));
        }catch(IOException ex){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }
}

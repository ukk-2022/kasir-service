package com.backend.appService.controller;

import com.backend.appService.entity.Transaksi;
import com.backend.appService.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping(path = "/api/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiService service;

    @PostMapping(path = "/new")
    public ResponseEntity<?> newTransaction(
            @RequestBody Transaksi transaksi
    ){
        Transaksi val = service.newTransaksi(transaksi);
        return ResponseEntity.ok().body(val);
    }
}

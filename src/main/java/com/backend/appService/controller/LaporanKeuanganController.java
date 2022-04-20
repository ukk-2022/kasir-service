package com.backend.appService.controller;

import com.backend.appService.entity.LaporanKeuangan;
import com.backend.appService.service.LaporanKeuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/laporan-keuangan")
public class LaporanKeuanganController {

    @Autowired
    private LaporanKeuanganService service;

    @GetMapping("/get-data")
    public ResponseEntity<?> findData(){
        LaporanKeuangan laporanKeuangan = service.findData();
        return ResponseEntity.ok().body(laporanKeuangan);
    }
}

package com.backend.appService.service;

import com.backend.appService.dao.LaporanKeuanganDAO;
import com.backend.appService.entity.LaporanKeuangan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaporanKeuanganService {

    @Autowired
    private LaporanKeuanganDAO dao;

    public LaporanKeuangan findData(){
        return dao.getData();
    }
}
